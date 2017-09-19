package com.example.motty.mapin_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

// 後で使います
public class TopActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
//public class MainActivity extends AppCompatActivity {

    private BaseAdapter adapter;

    private static final String[] scenes = {
            "fun",
            "library",
            "syokudou"

    };

    private static final int[] photos = {
            R.drawable.fun,
            R.drawable.library,
            R.drawable.syokudou
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_top);

        requestWindowFeature(Window.FEATURE_LEFT_ICON);

        setContentView(R.layout.activity_top);

        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.splash);

        // ListViewのインスタンスを生成
        ListView listView = (ListView) findViewById(R.id.list_view);

        // BaseAdapter を継承したadapterのインスタンスを生成
        // レイアウトファイル list.xml を activity_main.xml に inflate するためにadapterに引数として渡す
        adapter = new ListViewAdapter(this.getApplicationContext(), R.layout.list, scenes, photos);

        // ListViewにadapterをセット
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this.getApplicationContext(), CompanyInformationActivity.class);
        // clickされたpositionのtextとphotoのID
        String selectedText = scenes[position];
        int selectedPhoto = photos[position];
        // インテントにセット
        intent.putExtra("Text", selectedText);
        intent.putExtra("Photo", selectedPhoto);
        // Activity をスイッチする
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionsMenu_01:
                Intent intent1 = new android.content.Intent(this, InquiryActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
