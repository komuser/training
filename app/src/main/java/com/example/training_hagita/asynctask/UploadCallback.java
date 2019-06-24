package com.example.training_hagita.asynctask;

/**
 * コールバックインターフェース
 *
 * @author Hagita
 */
public interface UploadCallback {
    void onPreExecute();
    void onProgressUpdate();
    void onPostExecute(String string);
    void onCancelled();
}
