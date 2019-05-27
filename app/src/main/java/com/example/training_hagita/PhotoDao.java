package com.example.training_hagita;

import android.graphics.Bitmap;

public class PhotoDao {
    private Bitmap image;
    private String id;
    private String path;
    private String title;

    public Bitmap getImage() {
        return this.image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
