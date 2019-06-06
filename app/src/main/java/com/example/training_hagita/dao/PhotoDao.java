package com.example.training_hagita.dao;

import android.graphics.Bitmap;

public class PhotoDao {
    // 画像
    private Bitmap image;
    // ID
    private String id;
    // ファイルパス
    private String path;
    // タイトル
    private String title;
    // 説明
    private String mDescription;

    // 画像取得
    public Bitmap getImage() {
        return this.image;
    }

    // 画像設定
    public void setImage(Bitmap image) {
        this.image = image;
    }

    // ID取得
    public String getId() {
        return this.id;
    }

    // ID設定
    public void setId(String id) {
        this.id = id;
    }

    // ファイルパス取得
    public String getPath() {
        return this.path;
    }

    // ファイルパス設定
    public void setPath(String path) {
        this.path = path;
    }

    // タイトル取得
    public String getTitle() {
        return this.title;
    }

    // タイトル設定
    public void setTitle(String title) {
        this.title = title;
    }

    // 説明取得
    public String getDescription() {
        return mDescription;
    }

    // 説明設定
    public void setDescription(String description) {
        this.mDescription = description;
    }
}
