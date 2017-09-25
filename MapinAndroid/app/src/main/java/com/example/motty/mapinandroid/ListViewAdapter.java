package com.example.motty.mapinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.motty.mapinandroid.R.string.store_name;

public class ListViewAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

    private LayoutInflater inflater;
    private int itemLayoutId;
    private String[] titles;
    private int[] ids;

    public ListViewAdapter(Context context, int itemLayoutId, String[] scenes, int[] photos) {
        super();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayoutId = itemLayoutId;
        this.titles = scenes;
        this.ids = photos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(itemLayoutId, parent, false);

            // ViewHolderを生成
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        // holderのimageViewにセット
        holder.imageView.setImageResource(ids[position]);

        // リストに表示される名前
        holder.textView.setText(store_name);
        //holder.textView.setText("@string/textView12");

        return convertView;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}