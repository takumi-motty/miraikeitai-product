package com.example.motty.mapinandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.motty.mapinandroid.model.Company;
import com.example.motty.mapinandroid.view.TopListView;

import java.util.ArrayList;

public class TopListAdapter extends BaseAdapter {

    private ArrayList<TopListView> companies;
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
        if (convertView == null) {
            return companies.get(position);
        } else {
            return convertView;
        }
    }

    public void setDatas(ArrayList<Company> companies) {
        for (Company company : companies) {
            TopListView view = new TopListView(context);
            view.setCompany(company);
            this.companies.add(view);
        }
    }
}
