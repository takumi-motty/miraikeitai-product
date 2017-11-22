package com.example.motty.mapinandroid.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Company implements Parcelable {


    @SerializedName("info")
    private CompanyInfo companyInfo;

    //private List<Shop> shops;
    private Shop[] shops;

    public Company(CompanyInfo companyInfo, Shop[] shops) {
        this.companyInfo = companyInfo;
        this.shops = shops;
    }
    public Company(Parcel in){
        this.companyInfo = in.readParcelable(CompanyInfo.class.getClassLoader());
        this.shops = (Shop[]) in.readArray(ShopFile.class.getClassLoader());
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public void setShops() {
        this.shops = shops;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public Shop[] getShops() {
        return shops;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(companyInfo, flags);
        dest.writeArray(shops);
    }

    public static final Parcelable.Creator<Company> CREATOR
            = new Parcelable.Creator<Company>() {
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public String toString() {
        return "Company{" +
                "companyInfo=" + companyInfo +
                ", shops=" + Arrays.toString(shops) +
                '}';
    }
}
