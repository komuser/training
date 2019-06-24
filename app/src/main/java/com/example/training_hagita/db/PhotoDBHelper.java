package com.example.training_hagita.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_DESCRIPTION;
import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_FILE_NAME;
import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_ID;
import static com.example.training_hagita.db.PhotoContract.Photo.COLUMN_PATH;
import static com.example.training_hagita.db.PhotoContract.Photo.TABLE_NAME;

/**
 * DBのヘルパークラス
 *
 * @author Hagita
 */
public class PhotoDBHelper extends SQLiteOpenHelper {

    private static final String TAG = PhotoDBHelper.class.getSimpleName();

    public static final String DB_NAME = "TblPhoto.db";
    public static final int DB_VERSION = 1;

    /**
     * コンストラクタ
     *
     * @param context
     */
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

    /**
     * DBにデータをinsertする
     *
     * @param id
     * @param path
     * @param title
     * @param description
     * @return
     */
    public long insertValues(String id, String path, String title, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_PATH, path);
        contentValues.put(COLUMN_FILE_NAME, title);
        contentValues.put(COLUMN_DESCRIPTION, description);

        long registrationNumber = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return registrationNumber;
    }

    /**
     * DBのデータを削除する
     *
     * @param id
     * @return
     */
    public int deleteValues(String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);

        int deleteNumber = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{id});
        db.close();
        return deleteNumber;
    }

    /**
     * テーブル作成
     *
     * @param db
     */
    private void createTable(SQLiteDatabase db) {
        String Sql =
                "create table " + TABLE_NAME + " (" +
                        COLUMN_ID + " integer primary key autoincrement, " +
                        COLUMN_FILE_NAME + " text, " +
                        COLUMN_PATH + " text, " +
                        COLUMN_DESCRIPTION + " text)";
        db.execSQL(Sql);
    }

    /**
     * テーブル削除
     *
     * @param db
     */
    private void dropTable(SQLiteDatabase db) {
        String Sql =
                "drop table if exists " + TABLE_NAME;
        db.execSQL(Sql);
    }
}
