package com.example.motty.mapinandroid;

import com.example.motty.mapinandroid.model.ApiShops;
import com.example.motty.mapinandroid.model.ShopsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {
//    @GET("shops/{id}.json")
//    //Call<ContactsContract.Contacts.Data> getData(@Path("id") int dataId);
//    Call<Company> getData(@Path("id") int dataId);
//
//    @GET("shops.json")
//    //Call<List<ContactsContract.Contacts.Data>> getDatas();
//    Call<List<Company>> getDatas();

//    @GET("wsgi")
//    Call<MapinResponse> getMapinResponse();

//    @GET("api/shops.json")
//    Call<MapinResponse> getMapinResponse();

    //id番目のshopの情報をjsonで取得
//    @GET("/api/shops/{id}.json")
//    Call<ApiShops> getApiShops(@Path("id") int shopId);

    //id番目のshopの情報をjsonで取得
    @GET("api/shops/{id}.json")
    Call<ApiShops> getApiShops(@Path("id") int shopId);

//    @GET("/companies/{id}/shops.json")
//    Call<ApiShopsList> getApiShopsList(@Path("id") int companyId);

    @GET("api/shops.json")
    Call<List<ApiShops>> getApiShopsList();

    //id番目のshopの持ってるfileの一覧をjsonで取得
    @GET("/api/shops/{id}/files.json")
    Call<ShopsList> getApiFilesList(@Path("id") int shopId);




}
