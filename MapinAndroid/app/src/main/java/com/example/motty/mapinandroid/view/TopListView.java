package com.example.motty.mapinandroid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.motty.mapinandroid.R;
import com.example.motty.mapinandroid.model.Company;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class TopListView extends LinearLayout {
    private ImageView imageView;
    private TextView textViewShopName;
    private TextView textViewCompanyName;
    private TextView textViewCategory;
    private TextView textViewUpdate_at;

    URL url;
    InputStream istream;

    public TopListView(Context context) {
        this(context, null);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.list_top_adapter, this);
        textViewShopName = (TextView) findViewById(R.id.shopNameText);
        textViewCompanyName = (TextView) findViewById(R.id.companyNameText);
        textViewCategory = (TextView) findViewById(R.id.categoryText);
        textViewUpdate_at = (TextView) findViewById(R.id.update_atText);
        imageView = (ImageView) findViewById(R.id.imageViewShop);
    }


    public void setCompany(final Company company) {
        textViewShopName.setText(company.getShop_name());
        textViewCompanyName.setText(company.getCompany_name());
        textViewCategory.setText(company.getCategory());
        textViewUpdate_at.setText(company.getUpdated_at());

//        String url = company.getShop_image();
//
//        new AsyncTask<String, Void, Drawable>() {
//            @Override
//            protected Drawable doInBackground(String... params) {
//                Drawable img_data = null;
//
//                try {
//                    URL url = new URL(params[0]);
//                    InputStream is = (InputStream) url.getContent();
//                    img_data = Drawable.createFromStream(is, "");
//                } catch (Exception e) {
//                }
//                return img_data;
//            }
//
//            @Override
//            protected void onPostExecute(Drawable result) {
//                if ( result != null ) {
//                    imageView.setImageDrawable(result);
//                }
//            }
//        }.execute(url);



//        try {
//            url = new URL(company.getShop_image());
//            //インプットストリームで画像を読み込む
//            istream = url.openStream();
//            //読み込んだファイルをビットマップに変換
//            Bitmap oBmp = BitmapFactory.decodeStream(istream);
//            //ビットマップをImageViewに設定
//            imageView.setImageBitmap(oBmp);
//            //インプットストリームを閉じる
//            istream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        class ImageGetTask extends AsyncTask<String,Void,Bitmap> {
            private ImageView image;

            public ImageGetTask(ImageView _image) {
                image = _image;
            }
            @Override
            protected Bitmap doInBackground(String... params) {
                Bitmap image;
                try {
                    URL imageUrl = new URL(company.getShop_image());
                    InputStream imageIs;
                    imageIs = imageUrl.openStream();
                    image = BitmapFactory.decodeStream(imageIs);
                    return image;
                } catch (MalformedURLException e) {
                    return null;
                } catch (IOException e) {
                    return null;
                }
            }
            @Override
            protected void onPostExecute(Bitmap result) {
                // 取得した画像をImageViewに設定します。
                imageView.setImageBitmap(result);
            }
        }

    }
}

