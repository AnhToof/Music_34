package com.framgia.music_34.data.source.remote;

public interface OnFetchDataJsonListener<T> {

    void onSuccess(T data);

    void onError(Exception e);
}
