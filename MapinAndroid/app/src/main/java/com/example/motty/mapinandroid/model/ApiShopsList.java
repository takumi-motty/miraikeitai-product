package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ApiShopsList implements Parcelable{
    public ArrayList<ApiShops> listShops;

    public ApiShopsList(ArrayList<ApiShops> listShops) {
        this.listShops = listShops;
    }
    public ApiShopsList(Parcel in){
        this(in.readArrayList(ApiShops.class.getClassLoader()));
    }

    public ArrayList<ApiShops> getListShops() {
        return listShops;
    }

    public void setListShops(ArrayList<ApiShops> listShops) {
        this.listShops = listShops;
    }

    @Override
    public String toString() {
        return "ApiShopsList{" +
                "listShops=" + listShops +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listShops);
    }

    public static final Parcelable.Creator<ApiShopsList> CREATOR
            = new Parcelable.Creator<ApiShopsList>() {
        public ApiShopsList createFromParcel(Parcel in) { return new ApiShopsList(in);
        }

        public ApiShopsList[] newArray(int size) {
            return new ApiShopsList[size];
        }
    };
}
