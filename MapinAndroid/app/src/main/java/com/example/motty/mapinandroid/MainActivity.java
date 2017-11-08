package com.example.motty.mapinandroid;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.example.motty.mapinandroid.adapter.TopListAdapter;
import com.example.motty.mapinandroid.model.Company;
import com.example.motty.mapinandroid.model.MapinResponse;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import com.example.motty.mapinandroid.model.File;


//トップ画面
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
//public class MainActivity extends AppCompatActivity {

//    private ArrayAdapter<Company> adapter;
    private TopListAdapter topListAdapter;
    private TopListAdapter refreshAdapter;

    final ArrayList<Company> companies = new ArrayList<>();
//    ImageView imageView = (ImageView) findViewById(R.id.imageViewShop);
    //final ArrayList<File> files = new ArrayList<>();


    //pullToRefresh
    protected PullToRefreshListView listView;

    private MapinResponse mapinResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // メニューバーにロゴを表示
        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.drawable.splash);
        }

        // EditTextで自動的にキーボードが出るのを防ぐ処理
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_main);

        //getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.splash);

        //pullToRefresh
        listView = (PullToRefreshListView) findViewById(R.id.listView);
//        refreshAdapter = new TopListAdapter(this.getApplicationContext(), R.layout.list, scenes, photos);
        refreshAdapter = new TopListAdapter(this.getApplicationContext());
        listView.setAdapter(refreshAdapter);
        listView.setOnItemClickListener(this);
        //ListView listView = (ListView) findViewById(R.id.list_view);

        //listView.setOnItemClickListener(this);
//        getData();
    }

    // リスト更新
    private class LoadTask extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... params) {
            // データ取得処理
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            Log.d("onPostExecute", "更新完了");
            listView.onRefreshComplete();
            super.onPostExecute(strings);
        }
    }



    // リストをタップした時の動作
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this.getApplicationContext(), CompanyInformationActivity.class);

//        intent.putExtra("ShopName", companies.get(position).getShop_name());
//        intent.putExtra("CompanyName", companies.get(position).getCompany_name());
//        intent.putExtra("Category", companies.get(position).getCategory());
//        intent.putExtra("Updated_at", companies.get(position).getUpdated_at());
//        intent.putExtra("ImageUrl", companies.get(position).getShop_image());
//        intent.putExtra("PostalCode", companies.get(position).getPostal_code());
//        intent.putExtra("Address", companies.get(position).getAddress());
//        intent.putExtra("Tel", companies.get(position).getTel());
//        intent.putExtra("Homepage", companies.get(position).getHomepage());
//        intent.putExtra("hoursBegin", companies.get(position).getHours_begin());
//        intent.putExtra("hoursEnd", companies.get(position).getHours_end());
//
//        intent.putExtra("FileName", files.get(position).getFile_name());
        intent.putExtra("Response", mapinResponse);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // オプションをタップした時の動作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 設定をタップした時
            case R.id.optionsMenu_01:
                Intent intent1 = new android.content.Intent(this, ConfigurationActivity.class);
                startActivity(intent1);
                return true;
            // お問い合わせをタップした時
            case R.id.optionsMenu_02:
                Intent intent2 = new android.content.Intent(this, InquiryActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://54.199.196.68/")
//                .baseUrl("http://54.238.225.122/")
//                .baseUrl("http://http://ec2-54-199-196-68.ap-northeast-1.compute.amazonaws.com/")
                .baseUrl("http://133.25.196.21/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        final Call<MapinResponse> mapinResponseCall = service.getMapinResponse();

        mapinResponseCall.enqueue(new Callback<MapinResponse>() {
            @Override
            public void onResponse(Call<MapinResponse> call, Response<MapinResponse> response) {
                mapinResponse = response.body();
                companies.addAll(mapinResponse.getCompanies());
                Log.d("MainActivity", mapinResponse.toString());
            }

            @Override
            public void onFailure(Call<MapinResponse> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
        }

    private void updateContainer(ArrayList<Company> companies) {

//        TopListAdapter topListAdapter = new TopListAdapter(datas, getApplicationContext());
//        for (Company company : companies) {
//            adapter.add(company);
//        }
//        topListAdapter.setDatas(companies);
//        topListAdapter.notifyDataSetChanged();
        refreshAdapter.setDatas(companies);
        refreshAdapter.notifyDataSetChanged();
    }

}
