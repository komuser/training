package com.example.training_hagita.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhotoDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TblPhoto.db";
    public static final int DB_VERSION = 1;

    public PhotoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(PhotoContract.Photo.CREATE_TABLE);
        // データ登録
        db.execSQL(PhotoContract.Photo.INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 古いテーブルを削除
        db.execSQL(PhotoContract.Photo.DROP_TABLE);
        onCreate(db);
    }
}
