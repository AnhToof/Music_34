package com.framgia.music_34.data.source.remote.fetchjson;

import android.os.AsyncTask;
import com.framgia.music_34.data.source.remote.OnFetchDataTemp;

public class GetJsonFromUrl extends AsyncTask<String, Void, String> {

    private OnFetchDataTemp mListener;

    public GetJsonFromUrl(OnFetchDataTemp listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        try {
            ParseDataWithJson parseDataWithJson = new ParseDataWithJson();
            data = parseDataWithJson.getJsonFromUrl(strings[0]);
        } catch (Exception e) {
            mListener.onError(e);
        }
        return data;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);
        if (data != null) {
            mListener.onSuccess(data);
        }
    }
}
