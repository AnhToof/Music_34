package com.framgia.music_34.utils;

public interface BasePresenter<T> {

    void onStart();

    void onStop();

    void setView(T view);
}
