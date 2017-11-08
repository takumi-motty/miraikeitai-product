package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class MapinResponse implements Parcelable{
    private String status;
    private List<Company> companies;

    public MapinResponse(String status, List<Company> companies) {
        this.status = status;
        this.companies = companies;
    }
    public MapinResponse(Parcel in){
        this(in.readString(), in.readArrayList(Company.class.getClassLoader()));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "MapinResponse{" +
                "status='" + status + '\'' +
                ", companies=" + companies +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeList(companies);
    }

    public static final Parcelable.Creator<MapinResponse> CREATOR
            = new Parcelable.Creator<MapinResponse>() {
        public MapinResponse createFromParcel(Parcel in) {
            return new MapinResponse(in);
        }

        public MapinResponse[] newArray(int size) {
            return new MapinResponse[size];
        }
    };
}
