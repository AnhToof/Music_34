package com.framgia.music_34.data.source.remote;

public interface OnFetchDataTemp {

    void onSuccess(String data);

    void onError(Exception e);
}
