package com.example.motty.mapinandroid;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class LocationService extends Service implements LocationListener, GpsStatus.Listener {
    public static final String LOG_TAG = LocationService.class.getSimpleName();

    private final LocationServiceBinder binder = new LocationServiceBinder();
    boolean isLocationManagerUpdatingLocation;
    ArrayList<Location> locationList;
    boolean isLogging;

    private NotificationManager mManager;
    private int number = 0;


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


    //This is where we detect the app is being killed, thus stop service.
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
                .setContentText("緯度"+a+",経度"+b)//テキスト
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
        sendNotification(newLocation.getLatitude(),newLocation.getLongitude());

    }

}