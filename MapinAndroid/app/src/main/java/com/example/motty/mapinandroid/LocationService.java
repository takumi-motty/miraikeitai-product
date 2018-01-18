package com.example.motty.mapinandroid;



import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.motty.mapinandroid.model.ApiShops;
import com.example.motty.mapinandroid.model.ShopFile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class LocationService extends Service implements LocationListener, GpsStatus.Listener {
    public static final String LOG_TAG = LocationService.class.getSimpleName();

    private final LocationServiceBinder binder = new LocationServiceBinder();
    boolean isLocationManagerUpdatingLocation;
    ArrayList<Location> locationList;
    boolean isLogging;

    private NotificationManager mManager;
    private int number = 0;

    //位置情報のサービス
    public LocationService locationService;

    private double mLatitude;
    private double mLongitude;

    final private ArrayList<ApiShops> listShops = new ArrayList<>();

    final private ArrayList<ShopFile> listFiles = new ArrayList<>();

    private LocationManager locationManager = null;

    private ApiShops apiShops;

    private ShopFile shopFiles;


    public LocationService() {

    }

    @Override
    public void onCreate() {
        isLocationManagerUpdatingLocation = false;
        locationList = new ArrayList<>();
        isLogging = false;
    }

    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        super.onStartCommand(i, flags, startId);
        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }


    @Override
    public void onRebind(Intent intent) {
        Log.d(LOG_TAG, "onRebind ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "onUnbind ");

        return true;
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy ");
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(LOG_TAG, "onTaskRemoved ");

        if(this.isLocationManagerUpdatingLocation == true){
            this.stopUpdatingLocation();
            isLocationManagerUpdatingLocation = false;
        }

        stopSelf();
    }

    public class LocationServiceBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }

    /* LocationListener implemenation */
    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            notifyLocationProviderStatusUpdated(false);
        }

    }

    @Override
    public void onProviderEnabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            notifyLocationProviderStatusUpdated(true);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            if (status == LocationProvider.OUT_OF_SERVICE) {
                notifyLocationProviderStatusUpdated(false);
            } else {
                notifyLocationProviderStatusUpdated(true);
            }
        }
    }

    /* GpsStatus.Listener implementation */
    public void onGpsStatusChanged(int event) {


    }

    private void notifyLocationProviderStatusUpdated(boolean isLocationProviderAvailable) {
        //Broadcast location provider status change here
    }



    public void startUpdatingLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //Exception thrown when GPS or Network provider were not available on the user's device.
        try {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);
            criteria.setAltitudeRequired(false);
            criteria.setSpeedRequired(false);
            criteria.setCostAllowed(true);
            criteria.setBearingRequired(false);

            //API level 9 and up
            criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
            criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
            //criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
            //criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);

            Integer gpsFreqInMillis = 10000;
            Integer gpsFreqInDistance = 50;  // in meters

            locationManager.addGpsStatusListener(this);

            locationManager.requestLocationUpdates(gpsFreqInMillis, gpsFreqInDistance, criteria, this, null);

        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        } catch (SecurityException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        } catch (RuntimeException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }

    }


    public void stopUpdatingLocation(){
        if(this.isLocationManagerUpdatingLocation == true){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.removeUpdates(this);
            isLocationManagerUpdatingLocation = false;
        }
    }

    /*通知に関するメソッド*/
    private void sendNotification(double a,double b) {
        Notification n = new Notification(); // Notificationの生成
        // PendingIntentの生成
        //アクティビティの遷移先
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        // 詳細情報の設定とPendingIntentの設定
        //n.setLatestEventInfo(getApplicationContext(), "TITLE", "TEXT", pi);
        Notification notification = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("まっぴん")//タイトル
                .setContentText("ファイルリストが更新されました")//テキスト
                .setTicker("ticker text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fun))
                .setContentIntent(pi)
                .build();

        //manager.notify(0, notification);
        NotificationManager nm =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, notification); // 生成したNotificationを通知する

    }

    Intent intent = new Intent("MainActivity");

    @Override
    public void onLocationChanged(final Location newLocation) {
        /*位置情報を受け取った際の処理*/
        Log.d(TAG, "(" + newLocation.getLatitude() + "," + newLocation.getLongitude() + ")");

        double latitude = newLocation.getLatitude();
        double longitude = newLocation.getLongitude();

        intent.putExtra("Latitude", latitude);
        intent.putExtra("Longitude", longitude);

        //Toast.makeText(this, newLocation.getLatitude() + "," + newLocation.getLongitude(), Toast.LENGTH_SHORT).show();
//        sendNotification(newLocation.getLatitude(),newLocation.getLongitude());

        if(getFiles() == true){
            sendNotification(newLocation.getLatitude(),newLocation.getLongitude());
        }else{

        }



    }

    //店舗情報単体を取得
    private boolean getFiles() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();

            Log.d("LocationService", String.valueOf(mLatitude) + "," + String.valueOf(mLongitude));

        }else{
            Log.e("GPS", "Can't get last location.");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-199-196-68.ap-northeast-1.compute.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<ShopFile> filesCall = service.getFiles(6);

        filesCall.enqueue(new Callback<ShopFile>() {
            @Override
            public void onResponse(Call<ShopFile> call, Response<ShopFile> response) {
                shopFiles = response.body();
//                    companies.addAll(mapinResponse.getApiShopses());
//                    Log.d("MainActivity", apiShops.toString());
//                    Log.d("MainActivity", String.valueOf(latitude));
            }
            @Override
            public void onFailure(Call<ShopFile> call, Throwable t) {
                Log.d("LocationService", t.getMessage());
            }
        });

//        店舗情報のリストを取得
//            Call<List<ApiShops>> apiShopsListCall = service.getApiShopsList();
        Call<List<ShopFile>> fileListCall = service.getFilesListLocation(mLatitude, mLongitude);
        fileListCall.enqueue(new Callback<List<ShopFile>>() {
            @Override
            public void onResponse(Call<List<ShopFile>> call, Response<List<ShopFile>> response) {
                listFiles.addAll(response.body());
                Log.d("LocationService", listFiles.toString());
            }
            @Override
            public void onFailure(Call<List<ShopFile>> call, Throwable t) {
            }
        });

        if(listFiles == null){
            return false;
        }else{
            return true;
        }
    }

}