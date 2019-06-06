package com.example.training_hagita.db;

import android.provider.BaseColumns;

import com.example.training_hagita.dao.PhotoDao;

public class PhotoContract {

    public PhotoContract() {
    }

    public static abstract class Photo implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PATH = "path";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String CREATE_TABLE =
                "create table " + TABLE_NAME + " (" +
                        COLUMN_ID + " integer primary key autoincrement, " +
                        COLUMN_TITLE + " text, " +
                        COLUMN_PATH + " text, " +
                        COLUMN_DESCRIPTION + " text)";
        public static final String INSERT =
                "insert into users (title, path, description) values " +
                        "(" + new PhotoDao().getTitle() + ", " + new PhotoDao().getPath() + ", " + new PhotoDao().getDescription() + ")";
        public static final String DROP_TABLE =
                "drop table if exists " + TABLE_NAME;
    }
}
