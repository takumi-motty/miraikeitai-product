package com.example.motty.mapinandroid;


import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.motty.mapinandroid.adapter.ShopListAdapter;
import com.example.motty.mapinandroid.model.ApiShops;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//トップ画面
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ShopListAdapter refreshAdapter;

    final ArrayList<ApiShops> companies = new ArrayList<>();

    protected ListView listView;

    private ApiShops apiShops;

    //位置情報のサービス
    public LocationService locationService;

    private double mLatitude;
    private double mLongitude;

    final private ArrayList<ApiShops> listShops = new ArrayList<>();

    private LocationManager locationManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // メニューバーにロゴを表示
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.drawable.splash);
        }

        // EditTextで自動的にキーボードが出るのを防ぐ処理
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_main);

        //位置情報取得
        final Intent serviceStart = new Intent(this.getApplication(), LocationService.class);
        this.getApplication().startService(serviceStart);
        this.getApplication().bindService(serviceStart, serviceConnection, Context.BIND_AUTO_CREATE);

        listView = (ListView) findViewById(R.id.listView);

        refreshAdapter = new ShopListAdapter(this.getApplicationContext());

        listView.setAdapter(refreshAdapter);
        listView.setOnItemClickListener(this);
        getShopData();
    }

    //　位置情報実装
    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {

            String name = className.getClassName();

            if (name.endsWith("LocationService")) {
                locationService = ((LocationService.LocationServiceBinder) service).getService();

                locationService.startUpdatingLocation();
            }
        }

        public void onServiceDisconnected(ComponentName className) {

            if (className.getClassName().equals("LocationService")) {
                locationService = null;
            }
        }
    };

    // リストをタップした時の動作
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this.getApplicationContext(), CompanyInformationActivity.class);

        position = position-1;
//        intent.putParcelableArrayListExtra("ShopsInfo", listShops);
        intent.putExtra("CompanyName", listShops.get(position).getCompanyName());
        intent.putExtra("ShopName", listShops.get(position).getName());
        intent.putExtra("Category", listShops.get(position).getCategoryName());
        intent.putExtra("UpdatedAt", listShops.get(position).getUpdatedAt());
        intent.putExtra("PostNumber", listShops.get(position).getPostalCode());
        intent.putExtra("Address", listShops.get(position).getAddress());
        intent.putExtra("Tel", listShops.get(position).getTel());
        intent.putExtra("Homepage", listShops.get(position).getHomepage());
        intent.putExtra("BussinessHours", listShops.get(position).getBussinessHours());
        intent.putExtra("ImageUrl", listShops.get(position).getImageUrl());
        intent.putExtra("ShopId", listShops.get(position).getId());

        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        listShops.clear();
//        getShopData();
//    }

    //店舗情報単体を取得
        private void getShopData() {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                mLatitude = location.getLatitude();
                mLongitude = location.getLongitude();

                Log.d("MainActivity", String.valueOf(mLatitude) + "," + String.valueOf(mLongitude));

            }else{
                Log.e("GPS", "Can't get last location.");
            }


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ec2-54-199-196-68.ap-northeast-1.compute.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService service = retrofit.create(ApiService.class);
            Call<ApiShops> apiShopsCall = service.getApiShops(3);

            apiShopsCall.enqueue(new Callback<ApiShops>() {
                @Override
                public void onResponse(Call<ApiShops> call, Response<ApiShops> response) {
                    apiShops = response.body();
//                    companies.addAll(mapinResponse.getApiShopses());
//                    Log.d("MainActivity", apiShops.toString());
//                    Log.d("MainActivity", String.valueOf(latitude));
                }
                @Override
                public void onFailure(Call<ApiShops> call, Throwable t) {
                    Log.d("MainActivity", t.getMessage());
                }
            });

//        店舗情報のリストを取得
//            Call<List<ApiShops>> apiShopsListCall = service.getApiShopsList();
            Call<List<ApiShops>> apiShopsListCall = service.getApiShopsListLocation(mLatitude, mLongitude);
            apiShopsListCall.enqueue(new Callback<List<ApiShops>>() {
                @Override
                public void onResponse(Call<List<ApiShops>> call, Response<List<ApiShops>> response) {
                    listShops.addAll(response.body());
                    Log.d("MainActivity", listShops.toString());
                    updateContainer(listShops);
                }

                @Override
                public void onFailure(Call<List<ApiShops>> call, Throwable t) {
                    updateContainer(listShops);
                }
            });
        }

    private void updateContainer(ArrayList<ApiShops> listShops) {
        refreshAdapter.setShopsData(listShops);
        refreshAdapter.notifyDataSetChanged();
    }

}