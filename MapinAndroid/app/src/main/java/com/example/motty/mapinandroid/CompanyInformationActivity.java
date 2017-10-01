package com.example.motty.mapinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

//ファイル情報画面&企業情報画面
public class CompanyInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.drawable.splash);
        }

        setContentView(R.layout.activity_company_information);

        Intent intent = getIntent();
        //String selectedText = intent.getStringExtra("Text");
        int selectedPhoto = intent.getIntExtra("Photo", 0);

        /*TextView textView = (TextView) findViewById(R.id.selected_text);
        textView.setText(selectedText);*/
        ImageView imageView = (ImageView) findViewById(R.id.selected_photo);
        imageView.setImageResource(selectedPhoto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1);
        for (int i = 1; i < 6; i++) {
            adapter.add("設置ファイル" + i);
        }

        final ListView listView1 = (ListView) findViewById(R.id.listView01);
        listView1.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1);
        for (int i = 1; i < 6; i++) {
            adapter2.add("店舗情報" + i);
        }

        final ListView listView2 = (ListView) findViewById(R.id.listView02);
        listView2.setAdapter(adapter2);

        Button btn1 = (Button) this.findViewById(R.id.Button01);
        final Button btn3 = (Button) this.findViewById(R.id.Button03);

        // ファイルを押した時の動作
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button btn1 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button01);
                Button btn3 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button03);
                ListView listView1 = (ListView) CompanyInformationActivity.this.findViewById(R.id.listView01);
                ListView listView2 = (ListView) CompanyInformationActivity.this.findViewById(R.id.listView02);


                if (btn1.getVisibility() != View.VISIBLE) {
                    btn1.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    listView1.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.INVISIBLE);

                } else {
                    btn1.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.INVISIBLE);
                    listView1.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 企業情報を押した時の動作
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button btn1 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button01);
                Button btn3 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button03);

                if (btn3.getVisibility() != View.VISIBLE) {
                    btn3.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    listView2.setVisibility(View.VISIBLE);
                    listView1.setVisibility(View.INVISIBLE);

                } else {

                    btn3.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    listView1.setVisibility(View.VISIBLE);
                    listView2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionsMenu_01:
                Intent intent1 = new android.content.Intent(this, ConfigurationActivity.class);
                startActivity(intent1);
                return true;
            case R.id.optionsMenu_02:
                Intent intent2 = new android.content.Intent(this, InquiryActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
