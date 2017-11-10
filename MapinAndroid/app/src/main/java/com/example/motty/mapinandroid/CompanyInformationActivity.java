package com.example.motty.mapinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.motty.mapinandroid.model.ApiShops;

import java.util.ArrayList;

//ファイル情報画面&企業情報画面
public class CompanyInformationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> item_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(R.drawable.splash);
        }

        setContentView(R.layout.activity_company_information);

        Intent intent = getIntent();
        String companyName = intent.getStringExtra("CompanyName");
        String shopName = intent.getStringExtra("ShopName");
        String category = intent.getStringExtra("Category");
        String updatedAt = intent.getStringExtra("UpdatedAt");
        String postalCode = intent.getStringExtra("PostNumber");
        String bussinessHours = intent.getStringExtra("BussinessHours");
        String address = intent.getStringExtra("Address");
        String tel = intent.getStringExtra("Tel");
        String homepage = intent.getStringExtra("Homepage");
        String imageUrl = intent.getStringExtra("ImageUrl");

        ArrayList<ApiShops> shopsInfo = intent.getParcelableArrayListExtra("ShopsInfo");

        //String s = companies_information.get(0).getShop_name();

        TextView shopNameText = (TextView) findViewById(R.id.textShopName);
        shopNameText.setText(shopName);
//        shopNameText.setText(shopsInfo.get(0).getName());

        TextView companyNameText = (TextView) findViewById(R.id.textCompanyName);
        companyNameText.setText(companyName);
//        companyNameText.setText(shopsInfo.get(0).getCompanyName());
//
        TextView categoryText = (TextView) findViewById(R.id.textCategory);
        categoryText.setText(category);
//        categoryText.setText(shopsInfo.get(0).getCategoryName());

        TextView updated_atText = (TextView) findViewById(R.id.textUpdated_at);
        updated_atText.setText(updatedAt);
//        updated_atText.setText(shopsInfo.get(0).getUpdatedAt());

        TextView postal_codeText = (TextView) findViewById(R.id.textPostNumber);
        postal_codeText.setText(postalCode);
//        postal_codeText.setText(shopsInfo.get(0).getPostalCode());

        TextView addressText = (TextView) findViewById(R.id.textLocation);
        addressText.setText(address);
//        addressText.setText(shopsInfo.get(0).getAddress());

        TextView telText = (TextView) findViewById(R.id.textPhoneNumber);
        telText.setText(tel);
//        telText.setText(shopsInfo.get(0).getTel());

        TextView homepageText = (TextView) findViewById(R.id.textURL);
        homepageText.setText(homepage);
//        homepageText.setText(shopsInfo.get(0).getHomepage());

        TextView bussiness_hoursText = (TextView) findViewById(R.id.textBussinessHours);
        bussiness_hoursText.setText(bussinessHours);
//        bussiness_hoursText.setText(shopsInfo.get(0).getBussinessHours());

//        TextView hours_endText = (TextView) findViewById(R.id.textEnd);
//        hours_endText.setText(hours_End);




        /*TextView textView = (TextView) findViewById(R.id.selected_text);
        textView.setText(selectedText);*/
        ImageView imageView = (ImageView) findViewById(R.id.selected_photo);
        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
        //imageView.setImageResource(selectedPhoto);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1);
        for (int i = 1; i < 6; i++) {
            adapter.add("設置ファイル" + i);
        }

        final ListView listView1 = (ListView) findViewById(R.id.listView01);

        //ファイル文字列格納用リスト
        item_list = new ArrayList<String>();

        //アダプターの作成
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,item_list);


        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(this);

//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1);
//        for (int i = 1; i < 6; i++) {
//            adapter2.add("店舗情報" + i);
//        }

        //final ListView listView2 = (ListView) findViewById(R.id.listView02);
        //listView2.setAdapter(adapter2);

        final RelativeLayout detail = (RelativeLayout) findViewById(R.id.companyDetailInformation);

        Button btn1 = (Button) this.findViewById(R.id.Button01);
        final Button btn3 = (Button) this.findViewById(R.id.Button03);

        // ファイルを押した時の動作
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button btn1 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button01);
                Button btn3 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button03);
                ListView listView1 = (ListView) CompanyInformationActivity.this.findViewById(R.id.listView01);
                //ListView listView2 = (ListView) CompanyInformationActivity.this.findViewById(R.id.listView02);
                RelativeLayout detail = (RelativeLayout) findViewById(R.id.companyDetailInformation);


                if (btn1.getVisibility() != View.VISIBLE) {
                    btn1.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    listView1.setVisibility(View.VISIBLE);
                    //listView2.setVisibility(View.INVISIBLE);
                    detail.setVisibility(View.INVISIBLE);

                } else {
                    btn1.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    //listView2.setVisibility(View.INVISIBLE);
                    listView1.setVisibility(View.INVISIBLE);
                    detail.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 企業情報を押した時の動作
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button btn1 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button01);
                Button btn3 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button03);

                if (btn3.getVisibility() != View.VISIBLE) {
                    btn3.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    //listView2.setVisibility(View.VISIBLE);
                    listView1.setVisibility(View.INVISIBLE);
                    detail.setVisibility(View.VISIBLE);

                } else {

                    btn3.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    listView1.setVisibility(View.VISIBLE);
                    //listView2.setVisibility(View.INVISIBLE);
                    detail.setVisibility(View.INVISIBLE);
                }
            }
        });
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

    @Override
    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {

        Intent intent = new Intent(
                this.getApplicationContext(), ContentFileActivity.class);
        startActivity(intent);

    }

}
