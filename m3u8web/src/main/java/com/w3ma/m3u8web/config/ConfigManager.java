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

package com.w3ma.m3u8web.config;

import com.google.gson.Gson;
import com.w3ma.m3u8web.config.data.AppConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by Emanuele on 12/09/2016.
 */
@Component
public class ConfigManager {

    private final AppConfiguration appConfiguration;

    public ConfigManager() throws IOException {
        final Resource resource = new ClassPathResource("configuration.json");
        final InputStream is = resource.getInputStream();
        final Reader reader = new InputStreamReader(is);
        appConfiguration = new Gson().fromJson(reader, AppConfiguration.class);
    }

    public List<String> getCategoryToRemoveList() {
        return appConfiguration.getCategoryToRemoveList();
    }

    public List<String> getPlaylistToAddList() {
        return appConfiguration.getPlaylistToAddList();
    }
}
