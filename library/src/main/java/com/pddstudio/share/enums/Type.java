/*
 *
 *     Copyright (c) 2016 Patrick J
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

package com.pddstudio.share.enums;
/*
 * This Class was created by Patrick J
 * on 26.02.16. For more Details and licensing information
 * have a look at the README.md
 */

public enum Type {

    //text mime types
    PLAIN_TEXT("text/plain"),
    HTML_TEXT("text/html"),
    //image mime types
    IMAGE_PNG("image/png"),
    IMAGE_JPG("image/jpeg"),
    IMAGE_BMP("image/bmp"),
    IMAGE_GIF("image/gif"),
    //audio mime types
    AUDIO_MP3("audio/mpeg"),
    AUDIO_MPGA("audio/mpeg"),
    AUDIO_WAV("audio/x-wav"),
    //video mime types
    VIDEO_WAV("video/wav"),
    VIDEO_MP4("video/mp4"),
    //misc other file types
    DOCUMENT_PDF("application/pdf"),
    DOCUMENT_WORD("application/msword"),
    DOCUMENT_EXCEL("application/vnd.ms-excel"),
    DOCUMENT_POWER_POINT("application/mspowerpoint"),
    ZIP_ARCHIVE("application/zip");

    private final String type;

    Type(String mime) {
        this.type = mime;
    }

    public String getType() {
        return type;
    }
}
