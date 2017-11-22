package com.example.motty.mapinandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.motty.mapinandroid.model.ShopFile;
import com.example.motty.mapinandroid.view.FileListView;

import java.util.ArrayList;


public class FileListAdapter extends BaseAdapter {
    private ArrayList<ShopFile> listFile;
    private Context context;

    public FileListAdapter(Context context){
        this.context = context;
        this.listFile = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return listFile.size();
    }

    @Override
    public Object getItem(int position) {
        return listFile.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !(convertView instanceof FileListView)) {
            FileListView view = new FileListView(context);
            view.setShopFile(listFile.get(position));
            return view;
        } else {
            return convertView;
        }
    }

    public void setFileData(ArrayList<ShopFile> listFile){
        this.listFile = listFile;
    }

}
