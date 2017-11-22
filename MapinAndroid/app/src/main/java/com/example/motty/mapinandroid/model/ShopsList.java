package com.example.motty.mapinandroid.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ShopsList implements Parcelable{
    public List<ApiShops> shopsFiles;

    public ShopsList(List<ApiShops> shopsFiles) {
        this.shopsFiles = shopsFiles;
    }
    public ShopsList(Parcel in) {
        this(in.readArrayList(ApiShops.class.getClassLoader()));
    }

    public List<ApiShops> getShopsFiles() {
        return shopsFiles;
    }

    public void setShopsFiles(List<ApiShops> shopsFiles) {
        this.shopsFiles = shopsFiles;
    }

    @Override
    public String toString() {
        return "ShopsList{" +
                "shopsFiles=" + shopsFiles +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(shopsFiles);
    }

    public static final Parcelable.Creator<ShopsList> CREATOR
            = new Parcelable.Creator<ShopsList>() {
        public ShopsList createFromParcel(Parcel in) {
            return new ShopsList(in);
        }

        public ShopsList[] newArray(int size) {
            return new ShopsList[size];
        }
    };
}
