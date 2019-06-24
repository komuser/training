package com.example.training_hagita.db;

import android.provider.BaseColumns;

/**
 * コントラクトクラス
 *
 * @author Hagita
 */
public class PhotoContract {

    /**
     * コンストラクタ
     */
    public PhotoContract() {
    }

    /**
     * DBのテーブル名とカラム名
     *
     * @author Hagita
     */
    public static class Photo implements BaseColumns {
        public static final String TABLE_NAME = "photo_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_FILE_NAME = "file_name";
        public static final String COLUMN_PATH = "path";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
