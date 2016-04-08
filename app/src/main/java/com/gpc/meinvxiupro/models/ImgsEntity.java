package com.gpc.meinvxiupro.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pcgu on 16-3-11.
 */
public class ImgsEntity implements Parcelable {
    private String id;
    private String desc;
    private OwnerEntity owner;
    private String fromPageTitle;
    private String column;
    private String parentTag;
    private String date;
    private String downloadUrl;
    private String imageUrl;
    private int imageWidth;
    private int imageHeight;
    private String thumbnailUrl;
    private int thumbnailWidth;
    private int thumbnailHeight;
    private int thumbLargeWidth;
    private int thumbLargeHeight;
    private String thumbLargeUrl;
    private int thumbLargeTnWidth;
    private int thumbLargeTnHeight;
    private String thumbLargeTnUrl;
    private String siteName;
    private String siteLogo;
    private String siteUrl;
    private String fromUrl;
    private String isBook;
    private String bookId;
    private String objUrl;
    private String shareUrl;
    private String setId;
    private String albumId;
    private int isAlbum;
    private String albumName;
    private int albumNum;
    private String userId;
    private int isVip;
    private int isDapei;
    private String dressId;
    private String dressBuyLink;
    private int dressPrice;
    private int dressDiscount;
    private String dressExtInfo;
    private String dressTag;
    private int dressNum;
    private String objTag;
    private int dressImgNum;
    private String hostName;
    private String pictureId;
    private String pictureSign;
    private String dataSrc;
    private String contentSign;
    private String albumDi;
    private String canAlbumId;
    private String albumObjNum;
    private String appId;
    private String photoId;
    private int fromName;
    private String fashion;
    private String title;
    private List<String> tags;

    public void setId(String id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public void setFromPageTitle(String fromPageTitle) {
        this.fromPageTitle = fromPageTitle;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setParentTag(String parentTag) {
        this.parentTag = parentTag;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public void setThumbLargeWidth(int thumbLargeWidth) {
        this.thumbLargeWidth = thumbLargeWidth;
    }

    public void setThumbLargeHeight(int thumbLargeHeight) {
        this.thumbLargeHeight = thumbLargeHeight;
    }

    public void setThumbLargeUrl(String thumbLargeUrl) {
        this.thumbLargeUrl = thumbLargeUrl;
    }

    public void setThumbLargeTnWidth(int thumbLargeTnWidth) {
        this.thumbLargeTnWidth = thumbLargeTnWidth;
    }

    public void setThumbLargeTnHeight(int thumbLargeTnHeight) {
        this.thumbLargeTnHeight = thumbLargeTnHeight;
    }

    public void setThumbLargeTnUrl(String thumbLargeTnUrl) {
        this.thumbLargeTnUrl = thumbLargeTnUrl;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setSiteLogo(String siteLogo) {
        this.siteLogo = siteLogo;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public void setIsBook(String isBook) {
        this.isBook = isBook;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setObjUrl(String objUrl) {
        this.objUrl = objUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setIsAlbum(int isAlbum) {
        this.isAlbum = isAlbum;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumNum(int albumNum) {
        this.albumNum = albumNum;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public void setIsDapei(int isDapei) {
        this.isDapei = isDapei;
    }

    public void setDressId(String dressId) {
        this.dressId = dressId;
    }

    public void setDressBuyLink(String dressBuyLink) {
        this.dressBuyLink = dressBuyLink;
    }

    public void setDressPrice(int dressPrice) {
        this.dressPrice = dressPrice;
    }

    public void setDressDiscount(int dressDiscount) {
        this.dressDiscount = dressDiscount;
    }

    public void setDressExtInfo(String dressExtInfo) {
        this.dressExtInfo = dressExtInfo;
    }

    public void setDressTag(String dressTag) {
        this.dressTag = dressTag;
    }

    public void setDressNum(int dressNum) {
        this.dressNum = dressNum;
    }

    public void setObjTag(String objTag) {
        this.objTag = objTag;
    }

    public void setDressImgNum(int dressImgNum) {
        this.dressImgNum = dressImgNum;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public void setPictureSign(String pictureSign) {
        this.pictureSign = pictureSign;
    }

    public void setDataSrc(String dataSrc) {
        this.dataSrc = dataSrc;
    }

    public void setContentSign(String contentSign) {
        this.contentSign = contentSign;
    }

    public void setAlbumDi(String albumDi) {
        this.albumDi = albumDi;
    }

    public void setCanAlbumId(String canAlbumId) {
        this.canAlbumId = canAlbumId;
    }

    public void setAlbumObjNum(String albumObjNum) {
        this.albumObjNum = albumObjNum;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public void setFromName(int fromName) {
        this.fromName = fromName;
    }

    public void setFashion(String fashion) {
        this.fashion = fashion;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public String getFromPageTitle() {
        return fromPageTitle;
    }

    public String getColumn() {
        return column;
    }

    public String getParentTag() {
        return parentTag;
    }

    public String getDate() {
        return date;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public int getThumbLargeWidth() {
        return thumbLargeWidth;
    }

    public int getThumbLargeHeight() {
        return thumbLargeHeight;
    }

    public String getThumbLargeUrl() {
        return thumbLargeUrl;
    }

    public int getThumbLargeTnWidth() {
        return thumbLargeTnWidth;
    }

    public int getThumbLargeTnHeight() {
        return thumbLargeTnHeight;
    }

    public String getThumbLargeTnUrl() {
        return thumbLargeTnUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteLogo() {
        return siteLogo;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public String getIsBook() {
        return isBook;
    }

    public String getBookId() {
        return bookId;
    }

    public String getObjUrl() {
        return objUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public String getSetId() {
        return setId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public int getIsAlbum() {
        return isAlbum;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getAlbumNum() {
        return albumNum;
    }

    public String getUserId() {
        return userId;
    }

    public int getIsVip() {
        return isVip;
    }

    public int getIsDapei() {
        return isDapei;
    }

    public String getDressId() {
        return dressId;
    }

    public String getDressBuyLink() {
        return dressBuyLink;
    }

    public int getDressPrice() {
        return dressPrice;
    }

    public int getDressDiscount() {
        return dressDiscount;
    }

    public String getDressExtInfo() {
        return dressExtInfo;
    }

    public String getDressTag() {
        return dressTag;
    }

    public int getDressNum() {
        return dressNum;
    }

    public String getObjTag() {
        return objTag;
    }

    public int getDressImgNum() {
        return dressImgNum;
    }

    public String getHostName() {
        return hostName;
    }

    public String getPictureId() {
        return pictureId;
    }

    public String getPictureSign() {
        return pictureSign;
    }

    public String getDataSrc() {
        return dataSrc;
    }

    public String getContentSign() {
        return contentSign;
    }

    public String getAlbumDi() {
        return albumDi;
    }

    public String getCanAlbumId() {
        return canAlbumId;
    }

    public String getAlbumObjNum() {
        return albumObjNum;
    }

    public String getAppId() {
        return appId;
    }

    public String getPhotoId() {
        return photoId;
    }

    public int getFromName() {
        return fromName;
    }

    public String getFashion() {
        return fashion;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.desc);
        dest.writeParcelable(this.owner, flags);
        dest.writeString(this.fromPageTitle);
        dest.writeString(this.column);
        dest.writeString(this.parentTag);
        dest.writeString(this.date);
        dest.writeString(this.downloadUrl);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.imageWidth);
        dest.writeInt(this.imageHeight);
        dest.writeString(this.thumbnailUrl);
        dest.writeInt(this.thumbnailWidth);
        dest.writeInt(this.thumbnailHeight);
        dest.writeInt(this.thumbLargeWidth);
        dest.writeInt(this.thumbLargeHeight);
        dest.writeString(this.thumbLargeUrl);
        dest.writeInt(this.thumbLargeTnWidth);
        dest.writeInt(this.thumbLargeTnHeight);
        dest.writeString(this.thumbLargeTnUrl);
        dest.writeString(this.siteName);
        dest.writeString(this.siteLogo);
        dest.writeString(this.siteUrl);
        dest.writeString(this.fromUrl);
        dest.writeString(this.isBook);
        dest.writeString(this.bookId);
        dest.writeString(this.objUrl);
        dest.writeString(this.shareUrl);
        dest.writeString(this.setId);
        dest.writeString(this.albumId);
        dest.writeInt(this.isAlbum);
        dest.writeString(this.albumName);
        dest.writeInt(this.albumNum);
        dest.writeString(this.userId);
        dest.writeInt(this.isVip);
        dest.writeInt(this.isDapei);
        dest.writeString(this.dressId);
        dest.writeString(this.dressBuyLink);
        dest.writeInt(this.dressPrice);
        dest.writeInt(this.dressDiscount);
        dest.writeString(this.dressExtInfo);
        dest.writeString(this.dressTag);
        dest.writeInt(this.dressNum);
        dest.writeString(this.objTag);
        dest.writeInt(this.dressImgNum);
        dest.writeString(this.hostName);
        dest.writeString(this.pictureId);
        dest.writeString(this.pictureSign);
        dest.writeString(this.dataSrc);
        dest.writeString(this.contentSign);
        dest.writeString(this.albumDi);
        dest.writeString(this.canAlbumId);
        dest.writeString(this.albumObjNum);
        dest.writeString(this.appId);
        dest.writeString(this.photoId);
        dest.writeInt(this.fromName);
        dest.writeString(this.fashion);
        dest.writeString(this.title);
        dest.writeStringList(this.tags);
    }

    public ImgsEntity() {
    }

    protected ImgsEntity(Parcel in) {
        this.id = in.readString();
        this.desc = in.readString();
        this.owner = in.readParcelable(OwnerEntity.class.getClassLoader());
        this.fromPageTitle = in.readString();
        this.column = in.readString();
        this.parentTag = in.readString();
        this.date = in.readString();
        this.downloadUrl = in.readString();
        this.imageUrl = in.readString();
        this.imageWidth = in.readInt();
        this.imageHeight = in.readInt();
        this.thumbnailUrl = in.readString();
        this.thumbnailWidth = in.readInt();
        this.thumbnailHeight = in.readInt();
        this.thumbLargeWidth = in.readInt();
        this.thumbLargeHeight = in.readInt();
        this.thumbLargeUrl = in.readString();
        this.thumbLargeTnWidth = in.readInt();
        this.thumbLargeTnHeight = in.readInt();
        this.thumbLargeTnUrl = in.readString();
        this.siteName = in.readString();
        this.siteLogo = in.readString();
        this.siteUrl = in.readString();
        this.fromUrl = in.readString();
        this.isBook = in.readString();
        this.bookId = in.readString();
        this.objUrl = in.readString();
        this.shareUrl = in.readString();
        this.setId = in.readString();
        this.albumId = in.readString();
        this.isAlbum = in.readInt();
        this.albumName = in.readString();
        this.albumNum = in.readInt();
        this.userId = in.readString();
        this.isVip = in.readInt();
        this.isDapei = in.readInt();
        this.dressId = in.readString();
        this.dressBuyLink = in.readString();
        this.dressPrice = in.readInt();
        this.dressDiscount = in.readInt();
        this.dressExtInfo = in.readString();
        this.dressTag = in.readString();
        this.dressNum = in.readInt();
        this.objTag = in.readString();
        this.dressImgNum = in.readInt();
        this.hostName = in.readString();
        this.pictureId = in.readString();
        this.pictureSign = in.readString();
        this.dataSrc = in.readString();
        this.contentSign = in.readString();
        this.albumDi = in.readString();
        this.canAlbumId = in.readString();
        this.albumObjNum = in.readString();
        this.appId = in.readString();
        this.photoId = in.readString();
        this.fromName = in.readInt();
        this.fashion = in.readString();
        this.title = in.readString();
        this.tags = in.createStringArrayList();
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
}
