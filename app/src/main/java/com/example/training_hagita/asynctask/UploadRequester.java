package com.example.training_hagita.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadRequester extends AsyncTask<String, Void, String> {

    private static final String TAG = UploadRequester.class.getSimpleName();

    private Map<String, String> mParamList = new HashMap<>();
    private List<String> mFileList = new ArrayList<>();
    private Map<String, String> mHeaderList = new HashMap<>();
    private static final String LINE_FEED = "\r\n";
    private static final String CHARSET = "UTF-8";
    private static final int CONNECT_TIMEOUT = 5 * 1000;
    private static final int READ_TIMEOUT = 5 * 1000;

    // コールバック
    private UploadCallback mCallback;

    // コンストラクタ
    public UploadRequester(UploadCallback callback) {
        mCallback = callback;
    }

    // 前処理
    @Override
    protected void onPreExecute() {
        mCallback.onPreExecute();
    }

    // 進捗表示処理
    @Override
    protected void onProgressUpdate(Void... values) {
        mCallback.onProgressUpdate();
    }

    // メイン処理
    @Override
    protected String doInBackground(String... params) {
        StringBuilder response = new StringBuilder();
        String host = params[0];
        HttpURLConnection connection = null;
        try {
            String boundary = "===" + System.currentTimeMillis() + "===";
            URL url = new URL(host);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            try (OutputStream os = connection.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET);
                 PrintWriter writer = new PrintWriter(osw, true)) {
                // ヘッダ追加
                for(Map.Entry<String, String> header : mHeaderList.entrySet()) {
                    writer.append(header.getKey())
                            .append(": ")
                            .append(header.getValue())
                            .append(LINE_FEED)
                            .flush();
                }
                // パラメータ追加
                for(Map.Entry<String, String> param : mParamList.entrySet()) {
                    writer.append("--")
                            .append(boundary)
                            .append(LINE_FEED)
                            .append("Content-Disposition: form-data; name=\"")
                            .append(param.getKey())
                            .append("\"")
                            .append(LINE_FEED)
                            .append("Content-Type: text/plain; charset=" + CHARSET)
                            .append(LINE_FEED)
                            .append(LINE_FEED)
                            .append(param.getValue())
                            .append(LINE_FEED)
                            .flush();
                }
                // ファイル追加
                for(String path : mFileList) {
                    String name = "uploadfile[]";
                    File uploadFile = new File(path);
                    String fileName = uploadFile.getName();
                    writer.append("--")
                            .append(boundary)
                            .append(LINE_FEED)
                            .append("Content-Disposition: form-data; name=\"")
                            .append(name)
                            .append("\"; filename=\"")
                            .append(fileName)
                            .append("\"")
                            .append(LINE_FEED)
                            .append("Content-Type: ")
                            .append(URLConnection.guessContentTypeFromName(fileName))
                            .append(LINE_FEED)
                            .append("Content-Transfer-Encoding: binary")
                            .append(LINE_FEED)
                            .append(LINE_FEED)
                            .flush();
                    try (FileInputStream inputStream = new FileInputStream(uploadFile)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) > -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                        os.flush();
                        writer.append(LINE_FEED);
                        writer.flush();
                    }
                }
                writer.append(LINE_FEED)
                        .append("--")
                        .append(boundary)
                        .append("--")
                        .append(LINE_FEED)
                        .flush();
                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    try (InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                         BufferedReader reader = new BufferedReader(isr)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                    }
                } else {
                    throw new IOException("Error: " + status);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }

    // 後処理
    public void onPostExecute(String string) {
        mCallback.onPostExecute(string);
    }

    public void addFile(String path, String title) {
        mFileList.add(path);
    }
}
