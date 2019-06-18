package com.example.training_hagita.dao;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultDao {

    private static final String TAG = ResultDao.class.getName();

    private boolean mResult = false;
    private List<String> mMessageList = new ArrayList<>();

    public ResultDao(String text) {
        load(text);
    }

    public boolean isResult() {
        return mResult;
    }

    public void setResult(boolean result) {
        this.mResult = result;
    }

    public void addMessage(String message) {
        this.mMessageList.add(message);
    }

    public void load(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            setResult(jsonObject.getBoolean("result"));

            Object type = jsonObject.get("message");
            if (type instanceof JSONArray) {
                JSONArray list = jsonObject.getJSONArray("message");
                for (int cnt=0; cnt < list.length(); cnt++) {
                    addMessage(list.getString(cnt));
                }
            } else {
                addMessage(jsonObject.getString("message"));
            }
        } catch (JSONException e) {
            Log.d(TAG, "エラー：" + e);
        }
    }
}
