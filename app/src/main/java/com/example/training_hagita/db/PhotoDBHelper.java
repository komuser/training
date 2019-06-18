package com.example.training_hagita.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.training_hagita.dao.PhotoDao;

import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_DESCRIPTION;
import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_FILE_NAME;
import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_ID;
import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_PATH;
import static com.example.training_hagita.db.PhotoContract.Photo.TABLE_NAME;

public class PhotoDBHelper extends SQLiteOpenHelper {

    private static final String TAG = PhotoDBHelper.class.getSimpleName();

    public static final String DB_NAME = "TblPhoto.db";
    public static final int DB_VERSION = 1;

    public PhotoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // テーブル削除
        dropTable(db);
        // 新しいテーブルを作成
        createTable(db);
    }

    public long insertValues(String path, String title, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PATH, path);
        contentValues.put(COLUMN_FILE_NAME, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        long ret = db.insert(TABLE_NAME, null, contentValues);
        return ret;
    }

    public int deleteValues(String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        int ret = db.delete(TABLE_NAME, "", new String[]{id});
        return ret;
    }

    private void createTable(SQLiteDatabase db) {
        String Sql =
                "create table " + TABLE_NAME + " (" +
                        COLUMN_ID + " integer primary key autoincrement, " +
                        COLUMN_FILE_NAME + " text, " +
                        COLUMN_PATH + " text, " +
                        COLUMN_DESCRIPTION + " text)";
        db.execSQL(Sql);
    }

    private void dropTable(SQLiteDatabase db) {
        String Sql =
                "drop table if exists " + TABLE_NAME;
        db.execSQL(Sql);
    }
}
