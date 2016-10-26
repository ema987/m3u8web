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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Emanuele on 12/09/2016.
 */
@SpringBootApplication(scanBasePackages = "com.w3ma.m3u8web")
public class M3U8WebApplication {

    public static void main(final String[] args) {
        SpringApplication.run(M3U8WebApplication.class, args);
    }
}
