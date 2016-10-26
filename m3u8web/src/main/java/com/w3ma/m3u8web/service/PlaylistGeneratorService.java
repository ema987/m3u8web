/*
 *
 * Copyright 2016 Emanuele Papa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.w3ma.m3u8web.service;

import com.w3ma.m3u8parser.data.Playlist;
import com.w3ma.m3u8parser.exception.PlaylistParseException;
import com.w3ma.m3u8parser.merger.PlaylistMerger;
import com.w3ma.m3u8parser.parser.M3U8Parser;
import com.w3ma.m3u8parser.scanner.M3U8ItemScanner;
import com.w3ma.m3u8parser.writer.M3U8PlaylistWriter;
import com.w3ma.m3u8web.config.ConfigManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by epapa on 21/09/2016.
 */
@Service
public class PlaylistGeneratorService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private ConfigManager configManager;

    public ByteArrayOutputStream getPlaylistByteArrayOutputStream() {
        final List<Playlist> playlistList = new LinkedList<>();
        for (final String playlistUrl : configManager.getPlaylistToAddList()) {
            try (InputStream inputStream = new URL(playlistUrl).openStream()) {
                playlistList.add(new M3U8Parser(inputStream, M3U8ItemScanner.Encoding.UTF_8).parse());
            } catch (IOException | ParseException | PlaylistParseException e) {
                logger.error("Unable to parse or to get the playlist", e);
            }
        }

        final PlaylistMerger playlistMerger = new PlaylistMerger();
        final Playlist mergedPlaylist = playlistMerger.mergePlaylist(playlistList.toArray(new Playlist[playlistList.size()]));

        for (final String category : configManager.getCategoryToRemoveList()) {
            mergedPlaylist.getTrackSetMap().remove(category);
        }

        final M3U8PlaylistWriter m3U8PlaylistWriter = new M3U8PlaylistWriter();
        return m3U8PlaylistWriter.getByteArrayOutputStream(mergedPlaylist);
    }
}
