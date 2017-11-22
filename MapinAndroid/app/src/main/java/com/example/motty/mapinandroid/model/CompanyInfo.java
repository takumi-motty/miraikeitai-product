package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class CompanyInfo implements Parcelable {

    private int id;

    private String name;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public CompanyInfo(int id, String name, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;

    }
    public CompanyInfo(Parcel in){
        this(in.readInt(), in.readString(), in.readString(), in.readString());
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() { return createdAt; }

    public String getUpdatedAt() { return updatedAt; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
    public static final Parcelable.Creator<CompanyInfo> CREATOR
            = new Parcelable.Creator<CompanyInfo>() {
        public CompanyInfo createFromParcel(Parcel in) {
            return new CompanyInfo(in);
        }

        public CompanyInfo[] newArray(int size) {
            return new CompanyInfo[size];
        }
    };

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
