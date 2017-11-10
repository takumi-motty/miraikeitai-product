package com.example.motty.mapinandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.motty.mapinandroid.model.ApiShops;
import com.example.motty.mapinandroid.view.TopListView;

import java.util.ArrayList;


public class ShopListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ApiShops> listShops;

    public ShopListAdapter(Context context) {
        this.context = context;
        this.listShops = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return listShops.size();
    }

    @Override
    public Object getItem(int position) {
        return listShops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !(convertView instanceof TopListView)) {
            TopListView view = new TopListView(context);
            view.setShops(listShops.get(position));
            return view;
        } else {
            return convertView;
        }
    }

    public void setShopsData(ArrayList<ApiShops> listShops) {
        this.listShops = listShops;
    }
}
