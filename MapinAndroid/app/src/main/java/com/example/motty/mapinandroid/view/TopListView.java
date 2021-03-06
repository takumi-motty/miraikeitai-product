package com.example.motty.mapinandroid.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.motty.mapinandroid.R;
import com.example.motty.mapinandroid.model.ApiShops;

public class TopListView extends LinearLayout {
    private Context context;
    public ImageView imageView;
    private TextView textViewShopName;
    private TextView textViewCompanyName;
    private TextView textViewCategory;
    private TextView textViewUpdate_at;

    public TopListView(Context context) {
        this(context, null);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.list_top_adapter, this);
        textViewShopName = (TextView) findViewById(R.id.shopNameText);
        textViewCompanyName = (TextView) findViewById(R.id.companyNameText);
        textViewCategory = (TextView) findViewById(R.id.categoryText);
        textViewUpdate_at = (TextView) findViewById(R.id.update_atText);
        imageView = (ImageView) findViewById(R.id.imageViewShop);
    }

    public void setShops(ApiShops listShops){
        textViewCompanyName.setText(listShops.getCompanyName());
        textViewShopName.setText(listShops.getName());
        textViewCategory.setText(listShops.getCategoryName());
        Glide.with(context).load(listShops.getImageUrl()).into(imageView);
        textViewUpdate_at.setText(listShops.getUpdatedAt());

    }
}

