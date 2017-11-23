package com.example.motty.mapinandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.motty.mapinandroid.adapter.FileListAdapter;
import com.example.motty.mapinandroid.model.ShopFile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.motty.mapinandroid.R.id.selected_photo;

//ファイル情報画面&企業情報画面
public class CompanyInformationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

//    private ArrayList<String> item_list;
    final private ArrayList<ShopFile> listFiles = new ArrayList<>();

    private FileListAdapter fileListAdapter;

    protected ListView listView;

    private ShopFile apiFile;

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

        TextView shopNameText = (TextView) findViewById(R.id.textShopName);
        shopNameText.setText(shopName);

        TextView companyNameText = (TextView) findViewById(R.id.textCompanyName);
        companyNameText.setText(companyName);

        TextView categoryText = (TextView) findViewById(R.id.textCategory);
        categoryText.setText(category);

        TextView updated_atText = (TextView) findViewById(R.id.textUpdated_at);
        updated_atText.setText(updatedAt);

        TextView postal_codeText = (TextView) findViewById(R.id.textPostNumber);
        postal_codeText.setText(postalCode);

        TextView addressText = (TextView) findViewById(R.id.textLocation);
        addressText.setText(address);

        TextView telText = (TextView) findViewById(R.id.textPhoneNumber);
        telText.setText(tel);

        TextView homepageText = (TextView) findViewById(R.id.textURL);
        homepageText.setText(homepage);

        TextView bussiness_hoursText = (TextView) findViewById(R.id.textBussinessHours);
        bussiness_hoursText.setText(bussinessHours);

        ImageView imageView = (ImageView) findViewById(selected_photo);
        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);

        fileListAdapter = new FileListAdapter(this.getApplicationContext());
        final ListView fileListView = (ListView) findViewById(R.id.fileListView);



        final RelativeLayout detail = (RelativeLayout) findViewById(R.id.companyDetailInformation);

        Button btn1 = (Button) this.findViewById(R.id.Button01);
        final Button btn3 = (Button) this.findViewById(R.id.Button03);

        fileListAdapter.setFileData(listFiles);
        fileListView.setAdapter(fileListAdapter);

        getFileData();

        // ファイルを押した時の動作
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button btn1 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button01);
                Button btn3 = (Button) CompanyInformationActivity.this.findViewById(R.id.Button03);
                RelativeLayout detail = (RelativeLayout) findViewById(R.id.companyDetailInformation);

                if (btn1.getVisibility() != View.VISIBLE) {
                    btn1.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.INVISIBLE);
                    fileListView.setVisibility(View.VISIBLE);
                    detail.setVisibility(View.INVISIBLE);

                } else {
                    btn1.setVisibility(View.INVISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    fileListView.setVisibility(View.INVISIBLE);
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
                    fileListView.setVisibility(View.INVISIBLE);
                    detail.setVisibility(View.VISIBLE);

                } else {
                    btn3.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    fileListView.setVisibility(View.VISIBLE);
                    detail.setVisibility(View.INVISIBLE);
                }
            }
        });

        fileListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub, menu);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {

        Intent intent = new Intent(
                this.getApplicationContext(), ImageFileViewActivity.class);

        Intent intentDrive = new Intent(Intent.ACTION_VIEW);
        String url = listFiles.get(position).getUrl();
        intentDrive.setDataAndType(Uri.parse("http://docs.google.com/viewer?url=" + url), "text/html");

        switch(listFiles.get(position).getFileType()){
            case "pdf":
                startActivity(intentDrive);
                break;
            case "png":
                intent.putExtra("FileUrl", listFiles.get(position).getUrl());
                startActivity(intent);
                break;
            case "jpg":
                intent.putExtra("FileUrl", listFiles.get(position).getUrl());
                startActivity(intent);
                break;
            case "docx":
                startActivity(intentDrive);
                break;
            case "xls":
                startActivity(intentDrive);
                break;
            case "txt":
                break;
            case "pptx":
                startActivity(intentDrive);
                break;
            default:
                break;

        }

    }

    private void getFileData() {
        Intent intent = getIntent();
        int shopId = intent.getIntExtra("ShopId", 0);

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ec2-54-199-196-68.ap-northeast-1.compute.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ShopFile> shopFileCall = service.getApiShopFile(1,2);
        shopFileCall.enqueue(new Callback<ShopFile>() {
            @Override
            public void onResponse(Call<ShopFile> call, Response<ShopFile> response) {
                apiFile = response.body();
            }

            @Override
            public void onFailure(Call<ShopFile> call, Throwable t) {

            }
        });

        Call<List<ShopFile>> fileListCall = service.getApiFilesList(shopId);
        fileListCall.enqueue(new Callback<List<ShopFile>>() {
            @Override
            public void onResponse(Call<List<ShopFile>> call, Response<List<ShopFile>> response) {
                listFiles.addAll(response.body());
                Log.d("CompanyInformationActivity", listFiles.toString());
                fileContainer(listFiles);
            }
            @Override
            public void onFailure(Call<List<ShopFile>> call, Throwable t) {
                fileContainer(listFiles);
            }
        });
    }

    private void fileContainer(ArrayList<ShopFile> listFiles) {
        fileListAdapter.setFileData(listFiles);
        fileListAdapter.notifyDataSetChanged();
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        getFileData();
//    }
}
