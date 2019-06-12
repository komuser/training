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

    private static final String CREATE_TABLE =
            "create table " + TABLE_NAME + " (" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_FILE_NAME + " text, " +
                    COLUMN_PATH + " text, " +
                    COLUMN_DESCRIPTION + " text)";

    private static final String DROP_TABLE =
            "drop table if exists " + TABLE_NAME;

    public PhotoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // テーブル作成
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Log.d(TAG, "エラー：" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertValues(String id, String path, String title, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_PATH, path);
        contentValues.put(COLUMN_FILE_NAME, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteValues(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        PhotoDao photoDao = new PhotoDao();
        String id = photoDao.getId();
        String path = photoDao.getPath();
        String title = photoDao.getTitle();
        String description = photoDao.getDescription();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_PATH, path);
        contentValues.put(COLUMN_FILE_NAME, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        db.delete(TABLE_NAME, "", new String[]{id, path, title, description});
    }
}
