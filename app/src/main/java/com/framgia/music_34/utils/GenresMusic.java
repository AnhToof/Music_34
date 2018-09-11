package com.framgia.music_34.utils;

import android.support.annotation.StringDef;

@StringDef({
        GenresMusic.ALL_MUSIC, GenresMusic.ALL_AUDIO, GenresMusic.ALTERNATIVE_ROCK,
        GenresMusic.AMBIENT, GenresMusic.CLASSICAL, GenresMusic.COUNTRY
})
public @interface GenresMusic {
    String ALL_MUSIC = "all-music";
    String ALL_AUDIO = "all-audio";
    String ALTERNATIVE_ROCK = "alternativerock";
    String AMBIENT = "ambient";
    String CLASSICAL = "classical";
    String COUNTRY = "country";
}
