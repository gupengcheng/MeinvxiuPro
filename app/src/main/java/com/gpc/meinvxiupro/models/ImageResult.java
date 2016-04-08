package com.gpc.meinvxiupro.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by pcgu on 16-3-11.
 */
public class ImageResult implements Parcelable {

    /**
     * col : 美女
     * tag : 青春
     * tag3 :
     * sort : 0
     * totalNum : 87
     * startIndex : 0
     * returnNumber : 30
     */

    private String col;
    private String tag;
    private String tag3;
    private String sort;
    private int totalNum;
    private int startIndex;
    private int returnNumber;
    /**
     * id : 9552924355
     * desc : 2009后青春期shi
     * tags : ["青春"]
     * owner : {"userName":"juq440","userId":"862713848","userSign":"4143972352 862959327","isSelf":"0","portrait":"f8f76a75713434306b33","isVip":"0","isLanv":"0","isJiaju":"","isHunjia":"","orgName":"","resUrl":"","cert":"","budgetNum":"","lanvName":"","contactName":""}
     * fromPageTitle : 2009后青春期shi
     * column : 美女
     * parentTag :
     * date : 2016-01-26
     * downloadUrl : http://d.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327a1de913a938fa0ec08fac78c.jpg
     * imageUrl : http://d.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327a1de913a938fa0ec08fac78c.jpg
     * imageWidth : 750
     * imageHeight : 499
     * thumbnailUrl : http://imgt9.bdstatic.com/it/u=2,962989763&fm=25&gp=0.jpg
     * thumbnailWidth : 230
     * thumbnailHeight : 153
     * thumbLargeWidth : 310
     * thumbLargeHeight : 206
     * thumbLargeUrl : http://d.hiphotos.baidu.com/image/w%3D310/sign=80a7e41fcdfc1e17fdbf8a307a90f67c/10dfa9ec8a136327a1de913a938fa0ec08fac78c.jpg
     * thumbLargeTnWidth : 400
     * thumbLargeTnHeight : 266
     * thumbLargeTnUrl : http://d.hiphotos.baidu.com/image/w%3D400/sign=4128a9c51c178a82ce3c7ea0c603737f/10dfa9ec8a136327a1de913a938fa0ec08fac78c.jpg
     * siteName :
     * siteLogo :
     * siteUrl :
     * fromUrl : http://www.mm522.net/wangluo/qingchunmm/20/3871_1.html
     * isBook : 0
     * bookId : 0
     * objUrl : http://img.mm522.net/flashAll/20101125/1290624877qQEirG.jpg
     * shareUrl : http://d.hiphotos.baidu.com/image/s%3D550%3Bc%3Dwantu%2C8%2C95/sign=80180b1b4d4a20a4351e3cc2a069fb1f/10dfa9ec8a136327a1de913a938fa0ec08fac78c.jpg?referer=14240660272dd42a061e349b2182
     * setId : 1876
     * albumId : 400121360
     * isAlbum : 0
     * albumName : 2009后青春期shi
     * albumNum : 9
     * userId : 862713848
     * isVip : 0
     * isDapei : 0
     * dressId :
     * dressBuyLink :
     * dressPrice : 0
     * dressDiscount : 0
     * dressExtInfo :
     * dressTag :
     * dressNum : 0
     * objTag : 青春
     * dressImgNum : 0
     * hostName : www.mm522.net
     * pictureId : 9552924355
     * pictureSign : 789f83ea29ab6700a1350d0e962456f26a14f658
     * dataSrc :
     * contentSign : 1762943163,805924364
     * albumDi :
     * canAlbumId :
     * albumObjNum :
     * appId :
     * photoId :
     * fromName : 0
     * fashion : null
     * title : 2009后青春期shi
     */

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
