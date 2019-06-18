package com.example.training_hagita.dao;

import android.graphics.Bitmap;

public class PhotoDao {
    /**
     * 画像
     */
    private Bitmap image;

    /**
     * ID
     */
    private String id;

    /**
     * ファイルパス
     */
    private String path;

    /**
     * タイトル
     */
    private String title;

    /**
     * 説明
     */
    private String mDescription;

    /**
     * 画像取得
     * @return 画像
     */
    public Bitmap getImage() {
        return this.image;
    }

    /**
     * 画像設定
     * @param image
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }

    /**
     * 画像ID取得
     * @return ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * 画像ID設定
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * ファイルパス取得
     * @return ファイルパス
     */
    public String getPath() {
        return this.path;
    }

    /**
     * ファイルパス設定
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * ファイル名取得
     * @return ファイル名
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * ファイル名設定
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * ファイルの説明情報の取得
     * @return ファイルの説明情報
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * ファイルの説明情報設定
     * @param description
     */
    public void setDescription(String description) {
        this.mDescription = description;
    }
}
