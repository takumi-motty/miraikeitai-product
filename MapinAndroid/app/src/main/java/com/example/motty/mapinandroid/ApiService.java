package com.example.motty.mapinandroid;

import com.example.motty.mapinandroid.model.Company;
import com.example.motty.mapinandroid.model.MapinResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {
    @GET("shops/{id}.json")
    //Call<ContactsContract.Contacts.Data> getData(@Path("id") int dataId);
    Call<Company> getData(@Path("id") int dataId);

    @GET("shops.json")
    //Call<List<ContactsContract.Contacts.Data>> getDatas();
    Call<List<Company>> getDatas();

    @GET("wsgi")
    Call<MapinResponse> getMapinResponse();
}
