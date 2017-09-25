package com.example.motty.mapinandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

//店舗詳細情報画面
public class DetailedInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_information);

        Intent intent = getIntent();
        int selectedPhoto = intent.getIntExtra("Photo", 0);

        /*TextView textView = (TextView) findViewById(R.id.selected_text);
        textView.setText(selectedText);*/
        ImageView imageView = (ImageView) findViewById(R.id.selected_photo);
        imageView.setImageResource(selectedPhoto);
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
