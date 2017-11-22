package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ShopInfo implements Parcelable{
    private int id;
    @SerializedName("shop_name")
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("postal_code")
    private String postalCode;

    private String address;
    private String tel;

    @SerializedName("bussiness_hours")
    private String bussinessHours;

    private int category_id;

    private String homepage;

    private int company_id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    private String category;

    public ShopInfo(int id, String name, String imageUrl, String postalCode, String address, String tel, String bussinessHours, int category_id, String homepage, int company_id, String createdAt, String updatedAt, String category) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.postalCode = postalCode;
        this.address = address;
        this.tel = tel;
        this.bussinessHours = bussinessHours;
        this.category_id = category_id;
        this.homepage = homepage;
        this.company_id = company_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }
    public ShopInfo(Parcel in){
        this(in.readInt(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readInt(), in.readString(), in.readInt(), in.readString(), in.readString(), in.readString());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getHoursBegin() {
        return bussinessHours;
    }

    public String getCategory() {
        return category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBussinessHours() { return bussinessHours; }

    public int getCategory_id() { return category_id; }

    public int getCompany_id() { return company_id; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setHoursBegin(String hoursBegin) {
        this.bussinessHours = bussinessHours;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setBussinessHours(String bussinessHours) { this.bussinessHours = bussinessHours; }

    public void setCategory_id(int category_id) { this.category_id = category_id; }

    public void setCompany_id(int company_id) { this.company_id = company_id; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(postalCode);
        dest.writeString(address);
        dest.writeString(tel);
        dest.writeString(bussinessHours);
        dest.writeInt(category_id);
        dest.writeString(homepage);
        dest.writeInt(company_id);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(category);
    }

    public static final Parcelable.Creator<ShopInfo> CREATOR
            = new Parcelable.Creator<ShopInfo>() {
        public ShopInfo createFromParcel(Parcel in) {
            return new ShopInfo(in);
        }

        public ShopInfo[] newArray(int size) {
            return new ShopInfo[size];
        }
    };

    @Override
    public String toString() {
        return "ShopInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", bussinessHours='" + bussinessHours + '\'' +
                ", category_id=" + category_id +
                ", homepage='" + homepage + '\'' +
                ", company_id=" + company_id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
