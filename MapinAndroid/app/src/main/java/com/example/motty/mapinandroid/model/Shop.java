package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;


public class Shop implements Parcelable{
    // ShopInfo クラスを作っておく
    @SerializedName("info")
    private ShopInfo shopInfo;

    @SerializedName("files")
//    private List<ShopFile> files;
    private ShopFile[] files;

    // constructor
    //public Shop(ShopInfo shopInfo, List<ShopFile> files) {
    public Shop(ShopInfo shopInfo, ShopFile[] files) {
        this.shopInfo = shopInfo;
        this.files = files;
    }
    public Shop(Parcel in){

        this.shopInfo = in.readParcelable(ShopInfo.class.getClassLoader());


        //this.files = in.readArrayList(ShopFile.class.getClassLoader());
        this.files = (ShopFile[]) in.readArray(ShopFile.class.getClassLoader());
//        this((ShopInfo) in.readParcelable(ShopInfo.class.getClassLoader()),
//                /*(ShopFile) in.readParcelableArray(ShopFile.class.getClassLoader())*/
//                in.readArrayList(ShopFile.class.getClassLoader()));
    }


    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public ShopFile[] getFiles() {
        return files;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public void setFiles(ShopFile[] files) {
        this.files = files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(shopInfo, flags);
        dest.writeArray(files);
    }

    public static final Parcelable.Creator<Shop> CREATOR
            = new Parcelable.Creator<Shop>() {
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    @Override
    public String toString() {
        return "Shop{" +
                "shopInfo=" + shopInfo +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
