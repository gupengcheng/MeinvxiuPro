package com.gpc.meinvxiupro.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pcgu on 16-3-11.
 */
public class OwnerEntity implements Parcelable {
    private String userName;
    private String userId;
    private String userSign;
    private String isSelf;
    private String portrait;
    private String isVip;
    private String isLanv;
    private String isJiaju;
    private String isHunjia;
    private String orgName;
    private String resUrl;
    private String cert;
    private String budgetNum;
    private String lanvName;
    private String contactName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public void setIsSelf(String isSelf) {
        this.isSelf = isSelf;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public void setIsLanv(String isLanv) {
        this.isLanv = isLanv;
    }

    public void setIsJiaju(String isJiaju) {
        this.isJiaju = isJiaju;
    }

    public void setIsHunjia(String isHunjia) {
        this.isHunjia = isHunjia;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public void setBudgetNum(String budgetNum) {
        this.budgetNum = budgetNum;
    }

    public void setLanvName(String lanvName) {
        this.lanvName = lanvName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserSign() {
        return userSign;
    }

    public String getIsSelf() {
        return isSelf;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getIsVip() {
        return isVip;
    }

    public String getIsLanv() {
        return isLanv;
    }

    public String getIsJiaju() {
        return isJiaju;
    }

    public String getIsHunjia() {
        return isHunjia;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public String getCert() {
        return cert;
    }

    public String getBudgetNum() {
        return budgetNum;
    }

    public String getLanvName() {
        return lanvName;
    }

    public String getContactName() {
        return contactName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.userId);
        dest.writeString(this.userSign);
        dest.writeString(this.isSelf);
        dest.writeString(this.portrait);
        dest.writeString(this.isVip);
        dest.writeString(this.isLanv);
        dest.writeString(this.isJiaju);
        dest.writeString(this.isHunjia);
        dest.writeString(this.orgName);
        dest.writeString(this.resUrl);
        dest.writeString(this.cert);
        dest.writeString(this.budgetNum);
        dest.writeString(this.lanvName);
        dest.writeString(this.contactName);
    }

    public OwnerEntity() {
    }

    protected OwnerEntity(Parcel in) {
        this.userName = in.readString();
        this.userId = in.readString();
        this.userSign = in.readString();
        this.isSelf = in.readString();
        this.portrait = in.readString();
        this.isVip = in.readString();
        this.isLanv = in.readString();
        this.isJiaju = in.readString();
        this.isHunjia = in.readString();
        this.orgName = in.readString();
        this.resUrl = in.readString();
        this.cert = in.readString();
        this.budgetNum = in.readString();
        this.lanvName = in.readString();
        this.contactName = in.readString();
    }

    public static final Parcelable.Creator<OwnerEntity> CREATOR = new Parcelable.Creator<OwnerEntity>() {
        @Override
        public OwnerEntity createFromParcel(Parcel source) {
            return new OwnerEntity(source);
        }

        @Override
        public OwnerEntity[] newArray(int size) {
            return new OwnerEntity[size];
        }
    };
}
