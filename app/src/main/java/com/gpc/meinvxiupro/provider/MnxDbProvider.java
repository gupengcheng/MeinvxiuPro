package com.gpc.meinvxiupro.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gpc.meinvxiupro.MeinvxiuApplication;
import com.gpc.meinvxiupro.models.ImgsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcgu on 16-4-20.
 */
public class MnxDbProvider {
    private static MnxDbProvider mInstance;
    private static MnxDbHelper mDbHelper;
    private static SQLiteDatabase mReadableDatabase;
    private static SQLiteDatabase mWritableDatabase;

    public static MnxDbProvider getInstance() {
        if (mInstance == null) {
            mInstance = new MnxDbProvider();
            mDbHelper = new MnxDbHelper(MeinvxiuApplication.getInstance().getApplicationContext());
            mReadableDatabase = mDbHelper.getReadableDatabase();
            mWritableDatabase = mDbHelper.getWritableDatabase();
        }
        return mInstance;
    }

    public boolean isExistsInCollectTable(String id) {
        String[] columns = {
                ImgsEntity.COLUMN.ID
        };
        String[] selectionArgs = {
                id
        };
        boolean exists = false;
        mReadableDatabase = mDbHelper.getReadableDatabase();
        if (mReadableDatabase == null) {
        }
        Cursor cursor = mReadableDatabase.query(ImgsEntity.COLLECT_TABLE_NAME, columns,
                ImgsEntity.COLUMN.ID + "=?", selectionArgs, null, null, null);
        if (cursor.moveToNext()) {
            exists = true;
        }
        cursor.close();
        return exists;
    }

    public boolean insertImgEntity(ImgsEntity imgsEntity) {
        if (mWritableDatabase == null) {
            mWritableDatabase = mDbHelper.getWritableDatabase();
        }
        if (!isExistsInCollectTable(imgsEntity.getId())) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ImgsEntity.COLUMN.ID, imgsEntity.getId());
                contentValues.put(ImgsEntity.COLUMN.DESC, imgsEntity.getDesc());
                contentValues.put(ImgsEntity.COLUMN.DATE, imgsEntity.getDate());
                contentValues.put(ImgsEntity.COLUMN.DOWNLOAD_URL, imgsEntity.getDownloadUrl());
                contentValues.put(ImgsEntity.COLUMN.THUMB_NAIL_URL, imgsEntity.getThumbnailUrl());
                contentValues.put(ImgsEntity.COLUMN.THUMB_LARGE_URL, imgsEntity.getThumbLargeUrl());
                contentValues.put(ImgsEntity.COLUMN.THUMB_LARGE_TN_URL, imgsEntity.getThumbLargeTnUrl());
                contentValues.put(ImgsEntity.COLUMN.FROM_URL, imgsEntity.getFromUrl());
                contentValues.put(ImgsEntity.COLUMN.OBJ_URL, imgsEntity.getObjUrl());
                contentValues.put(ImgsEntity.COLUMN.OBJ_TAG, imgsEntity.getObjTag());
                contentValues.put(ImgsEntity.COLUMN.TITLE, imgsEntity.getTitle());
                long id = mWritableDatabase.insert(ImgsEntity.COLLECT_TABLE_NAME, null, contentValues);
                return id != -1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * ASC 为升序，DESC 为降序
     * 获取所有的收藏数据
     *
     * @return
     */
    public List<ImgsEntity> getCollectDatas() {
        if (mReadableDatabase == null) {
            mReadableDatabase = mDbHelper.getReadableDatabase();
        }
        List<ImgsEntity> imgResult = new ArrayList<>();
        Cursor cursor = mReadableDatabase.query(ImgsEntity.COLLECT_TABLE_NAME, null, null, null
                , null, null, ImgsEntity.COLUMN.PRIMARY_ID + " DESC");
        if (cursor != null) {
            listFromCursor(cursor, imgResult);
        }
        return imgResult;
    }

    public boolean deleteImgEntity(String id) {
        String[] whereAgs = {
                id
        };
        if (mWritableDatabase == null) {
            mWritableDatabase = mDbHelper.getWritableDatabase();
        }
        if (isExistsInCollectTable(id)) {
            int row = mWritableDatabase.delete(ImgsEntity.COLLECT_TABLE_NAME, ImgsEntity.COLUMN.ID + "=?", whereAgs);
            if (row > 0) {
                return true;
            }
        }
        return false;
    }

    private void listFromCursor(Cursor cursor, List<ImgsEntity> imgsEntities) {
        try {
            while (cursor.moveToNext()) {
                imgsEntities.add(imgEntityFromCursor(cursor));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }

    private ImgsEntity imgEntityFromCursor(Cursor cursor) {
        ImgsEntity imgsEntity = new ImgsEntity();
        imgsEntity.setId(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.ID)));
        imgsEntity.setDesc(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.DESC)));
        imgsEntity.setDate(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.DATE)));
        imgsEntity.setDownloadUrl(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.DOWNLOAD_URL)));
        imgsEntity.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.THUMB_NAIL_URL)));
        imgsEntity.setThumbLargeUrl(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.THUMB_LARGE_URL)));
        imgsEntity.setThumbLargeTnUrl(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.THUMB_LARGE_TN_URL)));
        imgsEntity.setFromUrl(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.FROM_URL)));
        imgsEntity.setObjUrl(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.OBJ_URL)));
        imgsEntity.setObjTag(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.OBJ_TAG)));
        imgsEntity.setTitle(cursor.getString(cursor.getColumnIndex(ImgsEntity.COLUMN.TITLE)));
        return imgsEntity;
    }
}
