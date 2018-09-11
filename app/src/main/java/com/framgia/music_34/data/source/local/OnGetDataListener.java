package com.framgia.music_34.data.source.local;

import java.util.List;

public interface OnGetDataListener<T> {

    void onSuccess(List<T> list);

    void onError(String error);
}
