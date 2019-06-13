package com.example.training_hagita.asynctask;

public interface UploadCallback {
    // コールバックインターフェース
    void onPreExecute();
    void onProgressUpdate();
    void onPostExecute(String string);
}
