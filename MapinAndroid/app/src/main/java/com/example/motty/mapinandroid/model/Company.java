package com.example.motty.mapinandroid.model;



public class Company {
    private int company_id;
    private String company_name;
    private int shop_id;
    private String shop_name;
    private String shop_image;
    private String postal_code;
    private String address;
    private String tel;
    private String hours_begin;
    private String hours_end;
    private String category;
    private String homepage;
    private int file_id;
    private String file_name;
    private String url;
    private String created_at;
    private String updated_at;
    private String file_type;

    public Company(int company_id, String company_name,
                     int shop_id, String shop_name, String shop_image, String postal_code, String address, String tel, String hours_begin, String hours_end, String category, String homepage,
                     int file_id, String file_name, String url, String created_at, String updated_at, String file_type) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_image = shop_image;
        this.postal_code = postal_code;
        this.address = address;
        this.tel = tel;
        this.hours_begin = hours_begin;
        this.hours_end = hours_end;
        this.category = category;
        this.homepage = homepage;
        this.file_id = file_id;
        this.file_name = file_name;
        this.url = url;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.file_type = file_type;
    }

    public int getCompany_id() {
        return company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public int getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getShop_image() {
        return shop_image;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getHours_begin() {
        return hours_begin;
    }

    public String getHours_end() {
        return hours_end;
    }

    public String getCategory() {
        return category;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getFile_id() {
        return file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getFile_type() {
        return file_type;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                ", shop_id=" + shop_id +
                ", shop_name='" + shop_name + '\'' +
                ", shop_image='" + shop_image + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", hours_begin='" + hours_begin + '\'' +
                ", hours_end='" + hours_end + '\'' +
                ", category='" + category + '\'' +
                ", homepage='" + homepage + '\'' +
                ", file_id=" + file_id +
                ", file_name='" + file_name + '\'' +
                ", url='" + url + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", file_type='" + file_type + '\'' +
                '}';
    }
}
