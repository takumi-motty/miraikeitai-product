package com.example.motty.mapinandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.motty.mapinandroid.model.Company;
import com.example.motty.mapinandroid.view.TopListView;

import java.util.ArrayList;

public class TopListAdapter extends BaseAdapter {

//    private ArrayList<TopListView> companies;
//    private ArrayList<Company> topCompany;
    private ArrayList<Company> companies;
    private Context context;

    public TopListAdapter(Context context) {
        this.context = context;
        this.companies = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return companies.size();
    }

    @Override
    public Object getItem(int position) {
        return companies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !(convertView instanceof TopListView)) {
            TopListView view = new TopListView(context);
            view.setCompany(companies.get(position));
            return view;
        } else {
            return convertView;
        }

//        if (convertView == null) {
//            convertView = layoutInflater.inflate(R.layout.list_top_adapter, null);
//        }


        //Glide.with(context).load(topCompany.get(position).getShop_image()).into(view.imageView);
       // Glide.with(context).load("http://i.imgur.com/DvpvklR.png").into(view.imageView);
       // Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(view.imageView);

//        imageView.setImageResource(R.drawable.fun);

    }


    public void setDatas(ArrayList<Company> companies) {
        this.companies = companies;
    }
}
