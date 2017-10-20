package com.example.motty.mapinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//ファイル情報画面&企業情報画面
public class CompanyInformationActivity extends AppCompatActivity {

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
        //String selectedText = intent.getStringExtra("Text");
//        int selectedPhoto = intent.getIntExtra("Photo", 0);

        //ArrayList<Company> companies_information = (ArrayList<Company>) intent.getSerializableExtra("Companies");


        //int position = intent.getIntExtra("position", 0);
        String shop_Name = intent.getStringExtra("ShopName");
        String company_Name = intent.getStringExtra("CompanyName");
        String category = intent.getStringExtra("Category");
        String updated_at = intent.getStringExtra("Updated_at");
        String imageUrl = intent.getStringExtra("ImageUrl");
        String postalCode = intent.getStringExtra("PostalCode");
        String address = intent.getStringExtra("Address");
        String tel = intent.getStringExtra("Tel");
        String homepage = intent.getStringExtra("Homepage");
        String hours_Begin = intent.getStringExtra("hoursBegin");
        String hours_End = intent.getStringExtra("hoursEnd");


        //String s = companies_information.get(0).getShop_name();




        TextView shopNameText = (TextView) findViewById(R.id.textShopName);
        shopNameText.setText(shop_Name);
        //shopNameText.setText(companies_information.get(position).getShop_name());

        TextView companyNameText = (TextView) findViewById(R.id.textCompanyName);
        companyNameText.setText(company_Name);

        TextView categoryText = (TextView) findViewById(R.id.textCategory);
        categoryText.setText(category);

        TextView updated_atText = (TextView) findViewById(R.id.textUpdated_at);
        updated_atText.setText(updated_at);

        TextView postal_codeText = (TextView) findViewById(R.id.textPostNumber);
        postal_codeText.setText(postalCode);

        TextView addressText = (TextView) findViewById(R.id.textLocation);
        addressText.setText(address);

        TextView telText = (TextView) findViewById(R.id.textPhoneNumber);
        telText.setText(tel);

        TextView homepageText = (TextView) findViewById(R.id.textURL);
        homepageText.setText(homepage);


        TextView hours_beginText = (TextView) findViewById(R.id.textBegin);
        hours_beginText.setText(hours_Begin);

        TextView hours_endText = (TextView) findViewById(R.id.textEnd);
        hours_endText.setText(hours_End);



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

}
