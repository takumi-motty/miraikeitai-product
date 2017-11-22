package com.example.motty.mapinandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ApiShops implements Parcelable {
//public class ApiShops{
    private int id;
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

    private double longitude;
    private double latitude;

    @SerializedName("company_name")
    private String companyName;

    @SerializedName("category_name")
    private String categoryName;

    public ApiShops(int id, String name, String imageUrl, String postalCode, String address, String tel, String bussinessHours, int category_id, String homepage, int company_id, String createdAt, String updatedAt, double longitude, double latitude, String companyName, String categoryName) {
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
        this.longitude = longitude;
        this.latitude = latitude;
        this.companyName = companyName;
        this.categoryName = categoryName;
    }
    public ApiShops(Parcel in){
        this(in.readInt(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readInt(), in.readString(), in.readInt(), in.readString(), in.readString(),in.readDouble(), in.readDouble(), in.readString(), in.readString());

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBussinessHours() {
        return bussinessHours;
    }

    public void setBussinessHours(String bussinessHours) {
        this.bussinessHours = bussinessHours;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Creator<ApiShops> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "ApiShops{" +
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
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", companyName='" + companyName + '\'' +
                ", categoryName='" + categoryName + '\'' +
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
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(companyName);
        dest.writeString(categoryName);

    }

    public static final Parcelable.Creator<ApiShops> CREATOR
            = new Parcelable.Creator<ApiShops>() {
        public ApiShops createFromParcel(Parcel in) { return new ApiShops(in);
        }

        public ApiShops[] newArray(int size) {
            return new ApiShops[size];
        }
    };
}
