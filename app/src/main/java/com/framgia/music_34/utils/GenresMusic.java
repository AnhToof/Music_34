package com.framgia.music_34.utils;

import android.support.annotation.StringDef;

@StringDef({
        GenresMusic.ALL_MUSIC, GenresMusic.ALL_AUDIO, GenresMusic.ALTERNATIVE_ROCK,
        GenresMusic.AMBIENT, GenresMusic.CLASSICAL, GenresMusic.COUNTRY
})
public @interface GenresMusic {
    String BASE_GENRES = "soundcloud:genres:";
    String ALL_MUSIC = BASE_GENRES + "all-music&";
    String ALL_AUDIO = BASE_GENRES + "all-audio&";
    String ALTERNATIVE_ROCK = BASE_GENRES + "alternativerock&";
    String AMBIENT = BASE_GENRES + "ambient&";
    String CLASSICAL = BASE_GENRES + "classical&";
    String COUNTRY = BASE_GENRES + "country&";
}
