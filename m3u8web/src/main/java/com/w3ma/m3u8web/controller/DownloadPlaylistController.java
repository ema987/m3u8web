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

package com.w3ma.m3u8web.controller;

import com.w3ma.m3u8web.service.PlaylistGeneratorService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Emanuele on 12/09/2016.
 */
@Controller
public class DownloadPlaylistController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private PlaylistGeneratorService playlistGeneratorService;

    @RequestMapping(value = "/playlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> getPlaylist(final HttpServletResponse response) throws IOException {
        logger.debug("getPlaylist called");
        final HttpHeaders headers = new HttpHeaders();
        response.setHeader("Content-Disposition", "attachment; filename=playlist.m3u");
        return new ResponseEntity<>(new ByteArrayResource(playlistGeneratorService.getPlaylistByteArrayOutputStream().toByteArray()), headers, HttpStatus.OK);
    }
}
