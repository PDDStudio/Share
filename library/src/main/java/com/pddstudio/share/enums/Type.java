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
