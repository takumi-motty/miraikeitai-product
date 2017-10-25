package com.example.motty.mapinandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.util.Log;
import android.os.AsyncTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.example.motty.mapinandroid.adapter.TopListAdapter;
import com.example.motty.mapinandroid.model.Company;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//トップ画面
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
//public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Company> adapter;
    private TopListAdapter topListAdapter;

    private static final String[] scenes = {
            "fun",
            "library",
            "syokudou"

    };

    // リスト表示する画像を設置
    private static final int[] photos = {
            R.drawable.fun,
            R.drawable.library,
            R.drawable.syokudou
    };

    //pullToRefresh
    protected PullToRefreshListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_top);
        //requestWindowFeature(Window.FEATURE_LEFT_ICON);

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

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext());

        final ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(topListAdapter);
        listView.setOnItemClickListener(this);

        //getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.splash);

        //pullToRefresh
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        adapter = new ListViewAdapter(this.getApplicationContext(), R.layout.list, scenes, photos);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
        //ListView listView = (ListView) findViewById(R.id.list_view);

        //adapter = new ListViewAdapter(this.getApplicationContext(), R.layout.list, scenes, photos);

        //listView.setAdapter(adapter);

        //listView.setOnItemClickListener(this);
        getData();
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
        // clickされたpositionのtextとphotoのID
        String selectedText = scenes[position];
        int selectedPhoto = photos[position];
        // インテントにセット
        intent.putExtra("Text", selectedText);
        intent.putExtra("Photo", selectedPhoto);
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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        getData();
//    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://54.238.225.122/")
                .baseUrl("http://54.238.225.122/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        final Company[] company = new Company[1];
        Call<Company> call = service.getData(1);
        try {
            call.enqueue(new Callback<Company>() {
                @Override
                public void onResponse(Call<Company> call, Response<Company> response) {
//                    Log.d("MainActivity", response.body().toString());
                    company[0] = response.body();
                    Log.d("MainActivity", company[0].toString());
                }

                @Override
                public void onFailure(Call<Company> call, Throwable t) {
                    Log.d("MainActivity", t.getMessage());
                    company[0] = new Company(1, "", 1, "", "", "", "", "", "", "", "", "", 1, "", "", "", "", "");
                    Log.d("MainActivity", company[0].toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ArrayList<Company> companies = new ArrayList<>();
        Call<List<Company>> call2 = service.getDatas();
        try {
            call2.enqueue(new Callback<List<Company>>() {
                @Override
                public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
//                    Log.d("MainActivity", response.body().toString());
                    Log.d("MainActivity", "call onResponse");
                    companies.addAll(response.body());
                    Log.d("MainActivity", companies.toString());

                    updateContainer(companies);
                }

                @Override
                public void onFailure(Call<List<Company>> call, Throwable t) {
                    Log.d("MainActivity", "call onFailure");
                    Log.d("MainActivity", t.getMessage());

                    //ダミーデータ設置
                    companies.add(new Company(1, "FUN", 1, "ミライマーケット", "https://imgur.com/8fkVe2B", "", "", "", "", "", "小売店", "", 1, "", "", "", "2017/10/08", ""));
                    companies.add(new Company(1, "FUN", 1, "ミライケータイショップ", "", "", "", "", "", "", "ショップ", "", 1, "", "", "", "2017/10/15", ""));
                    companies.add(new Company(1, "FUN", 1, "ミライコンビニ", "", "", "", "", "", "", "コンビニ", "", 1, "", "", "", "2017/11/11", ""));
                    //companies.add(new Company(1, "FUN", 1, "ミライバーガー", "", "", "", "", "", "", "飲食店", "", 1, "", "", "", "2017/12/31", ""));
                    companies.add(new Company(1, "ラッキーピエロ", 1, "ラッキーピエロ", "", "", "", "", "", "", "飲食店", "", 1, "", "", "", "2017/11/25", ""));
                    companies.add(new Company(1, "ドン・キホーテ", 1, "ドン・キホーテ", "", "", "", "", "", "", "小売店", "", 1, "", "", "", "2018/02/14", ""));

                    Log.d("MainActivity", companies.toString());

                    updateContainer(companies);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateContainer(ArrayList<Company> companies) {

//        TopListAdapter topListAdapter = new TopListAdapter(datas, getApplicationContext());
        for (Company company : companies) {
            adapter.add(company);
        }
        topListAdapter.setDatas(companies);
        topListAdapter.notifyDataSetChanged();
    }

}
