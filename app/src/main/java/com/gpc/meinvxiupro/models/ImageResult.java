package com.gpc.meinvxiupro.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pcgu on 16-3-11.
 */
public class ImageResult implements Parcelable {

    private String col;
    private String tag;
    private String tag3;
    private String sort;
    private int totalNum;
    private int startIndex;
    private int returnNumber;

    private List<ImgsEntity> imgs;

    public void setCol(String col) {
        this.col = col;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setReturnNumber(int returnNumber) {
        this.returnNumber = returnNumber;
    }

    public void setImgs(List<ImgsEntity> imgs) {
        this.imgs = imgs;
    }

    public String getCol() {
        return col;
    }

    public String getTag() {
        return tag;
    }

    public String getTag3() {
        return tag3;
    }

    public String getSort() {
        return sort;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getReturnNumber() {
        return returnNumber;
    }

    public List<ImgsEntity> getImgs() {
        return imgs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.col);
        dest.writeString(this.tag);
        dest.writeString(this.tag3);
        dest.writeString(this.sort);
        dest.writeInt(this.totalNum);
        dest.writeInt(this.startIndex);
        dest.writeInt(this.returnNumber);
        dest.writeTypedList(imgs);
    }

    public ImageResult() {
    }

    protected ImageResult(Parcel in) {
        this.col = in.readString();
        this.tag = in.readString();
        this.tag3 = in.readString();
        this.sort = in.readString();
        this.totalNum = in.readInt();
        this.startIndex = in.readInt();
        this.returnNumber = in.readInt();
        this.imgs = in.createTypedArrayList(ImgsEntity.CREATOR);
    }

    public static final Parcelable.Creator<ImageResult> CREATOR = new Parcelable.Creator<ImageResult>() {
        @Override
        public ImageResult createFromParcel(Parcel source) {
            return new ImageResult(source);
        }

        @Override
        public ImageResult[] newArray(int size) {
            return new ImageResult[size];
        }
    };
}
