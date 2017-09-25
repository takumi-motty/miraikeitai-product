package com.example.motty.mapinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

//ファイル情報画面&企業情報画面
public class CompanyInformationActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        for(int i = 1; i<6; i++) {
            adapter.add("設置ファイル" + i);
        }

        ListView listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

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


    // ボタンをタップした時の動作
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DetailedInformationActivity.class);
        startActivity(intent);

    }
}
