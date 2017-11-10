package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ApiShopsFiles implements Parcelable {
    private int id;
    private String name;
    private String url;

    @SerializedName("file_type")
    private String fileType;

    private int shop_id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public ApiShopsFiles(int id, String name, String url, String fileType, int shop_id, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.fileType = fileType;
        this.shop_id = shop_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public ApiShopsFiles(Parcel in){
        this(in.readInt(), in.readString(), in.readString(), in.readString(), in.readInt(), in.readString(), in.readString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ApiShopsFiles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", fileType='" + fileType + '\'' +
                ", shop_id=" + shop_id +
                ", createdAr='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(fileType);
        dest.writeInt(shop_id);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);

    }

    public static final Parcelable.Creator<ApiShopsFiles> CREATOR
            = new Parcelable.Creator<ApiShopsFiles>() {
        public ApiShopsFiles createFromParcel(Parcel in) { return new ApiShopsFiles(in);
        }

        public ApiShopsFiles[] newArray(int size) {
            return new ApiShopsFiles[size];
        }
    };
}
