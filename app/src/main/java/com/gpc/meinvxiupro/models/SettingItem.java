package com.gpc.meinvxiupro.models;

/**
 * Created by pcgu on 16-4-22.
 */
public class SettingItem {
    private String mSettingContent;
    private String mSettingKey;
    private int mType;

    public String getSettingContent() {
        return mSettingContent;
    }

    public void setSettingContent(String mSettingContent) {
        this.mSettingContent = mSettingContent;
    }

    public String getSettingKey() {
        return mSettingKey;
    }

    public void setSettingKey(String mSettingKey) {
        this.mSettingKey = mSettingKey;
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }
}
