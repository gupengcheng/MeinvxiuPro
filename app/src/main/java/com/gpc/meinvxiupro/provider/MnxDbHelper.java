package com.gpc.meinvxiupro.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gpc.meinvxiupro.models.ImgsEntity;

/**
 * Created by pcgu on 16-4-20.
 */
public class MnxDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "meinvxiu.db";
    private static final int DB_VERSION = 1;
    private static final String COLLECT_TABLE_CREATE =
            "CREATE TABLE " + ImgsEntity.COLLECT_TABLE_NAME + " ("
                    + ImgsEntity.COLUMN.PRIMARY_ID + " INTEGER PRIMARY KEY, "
                    + ImgsEntity.COLUMN.ID + " TEXT, "
                    + ImgsEntity.COLUMN.DESC + " TEXT, "
                    + ImgsEntity.COLUMN.DATE + " TEXT, "
                    + ImgsEntity.COLUMN.DOWNLOAD_URL + " TEXT, "
                    + ImgsEntity.COLUMN.THUMB_NAIL_URL + " TEXT, "
                    + ImgsEntity.COLUMN.THUMB_LARGE_URL + " TEXT, "
                    + ImgsEntity.COLUMN.THUMB_LARGE_TN_URL + " TEXT, "
                    + ImgsEntity.COLUMN.FROM_URL + " TEXT, "
                    + ImgsEntity.COLUMN.OBJ_URL + " TEXT, "
                    + ImgsEntity.COLUMN.OBJ_TAG + " TEXT, "
                    + ImgsEntity.COLUMN.TITLE + " TEXT);";
    ;

    public MnxDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COLLECT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
