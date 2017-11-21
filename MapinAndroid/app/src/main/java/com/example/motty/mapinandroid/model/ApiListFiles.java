package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ApiListFiles implements Parcelable {
    private ArrayList<ShopFile> listFile;

    public ApiListFiles(ArrayList<ShopFile> listFile) {
        this.listFile = listFile;
    }
    public ApiListFiles(Parcel in) {
        this(in.readArrayList(ShopFile.class.getClassLoader()));
    }

    public ArrayList<ShopFile> getListFile() {
        return listFile;
    }

    public void setListFile(ArrayList<ShopFile> listFile) {
        this.listFile = listFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listFile);
    }

    public static final Parcelable.Creator<ApiListFiles> CREATOR
            = new Parcelable.Creator<ApiListFiles>() {
        public ApiListFiles createFromParcel(Parcel in) { return new ApiListFiles(in);
        }

        public ApiListFiles[] newArray(int size) {
            return new ApiListFiles[size];
        }
    };
}
