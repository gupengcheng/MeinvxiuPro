package com.gpc.meinvxiupro.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pcgu on 16-3-11.
 */
public class ImgsEntity implements Parcelable {
    public static final String COLLECT_TABLE_NAME = "collect";

    private String id;
    private String desc;
    private String date;
    private String downloadUrl;
    private String thumbnailUrl;
    private String thumbLargeUrl;
    private String thumbLargeTnUrl;
    private String fromUrl;
    private String objUrl;
    private String objTag;
    private String title;

    public void setId(String id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }


    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setThumbLargeUrl(String thumbLargeUrl) {
        this.thumbLargeUrl = thumbLargeUrl;
    }

    public void setThumbLargeTnUrl(String thumbLargeTnUrl) {
        this.thumbLargeTnUrl = thumbLargeTnUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public void setObjUrl(String objUrl) {
        this.objUrl = objUrl;
    }


    public void setObjTag(String objTag) {
        this.objTag = objTag;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }


    public String getDate() {
        return date;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }


    public String getThumbLargeUrl() {
        return thumbLargeUrl;
    }


    public String getThumbLargeTnUrl() {
        return thumbLargeTnUrl;
    }


    public String getFromUrl() {
        return fromUrl;
    }


    public String getObjUrl() {
        return objUrl;
    }


    public String getObjTag() {
        return objTag;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.desc);
        dest.writeString(this.date);
        dest.writeString(this.downloadUrl);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.thumbLargeUrl);
        dest.writeString(this.thumbLargeTnUrl);
        dest.writeString(this.fromUrl);
        dest.writeString(this.objUrl);
        dest.writeString(this.objTag);
        dest.writeString(this.title);
    }

    public ImgsEntity() {
    }

    protected ImgsEntity(Parcel in) {
        this.id = in.readString();
        this.desc = in.readString();
        this.date = in.readString();
        this.downloadUrl = in.readString();
        this.thumbnailUrl = in.readString();
        this.thumbLargeUrl = in.readString();
        this.thumbLargeTnUrl = in.readString();
        this.fromUrl = in.readString();
        this.objUrl = in.readString();
        this.objTag = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<ImgsEntity> CREATOR = new Parcelable.Creator<ImgsEntity>() {
        @Override
        public ImgsEntity createFromParcel(Parcel source) {
            return new ImgsEntity(source);
        }

        @Override
        public ImgsEntity[] newArray(int size) {
            return new ImgsEntity[size];
        }
    };

    public static final class COLUMN {
        public static final String PRIMARY_ID = "primary_id";
        public static final String ID = "id";
        public static final String DESC = "desc";
        public static final String DATE = "date";
        public static final String DOWNLOAD_URL = "downloadUrl";
        public static final String THUMB_NAIL_URL = "thumbnailUrl";
        public static final String THUMB_LARGE_URL = "thumbLargeUrl";
        public static final String THUMB_LARGE_TN_URL = "thumbLargeTnUrl";
        public static final String FROM_URL = "fromUrl";
        public static final String OBJ_URL = "objUrl";
        public static final String OBJ_TAG = "objTag";
        public static final String TITLE = "title";
    }
}
