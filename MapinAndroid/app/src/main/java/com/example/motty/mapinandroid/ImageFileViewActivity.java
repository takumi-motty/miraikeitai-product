package com.example.motty.mapinandroid;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageFileViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_file_view);

        // メニューバーにロゴを表示
        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.drawable.splash);
        }

        Intent intent = getIntent();

        String url = intent.getStringExtra("FileUrl");

        ImageView imageView = (ImageView) findViewById(R.id.fileImageView);
        Glide.with(getApplicationContext()).load(url).into(imageView);


    }
}
