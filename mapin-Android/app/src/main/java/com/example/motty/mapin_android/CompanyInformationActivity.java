package com.example.motty.mapin_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CompanyInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_information);

        Intent intent = getIntent();
        //String selectedText = intent.getStringExtra("Text");
        int selectedPhoto = intent.getIntExtra("Photo", 0);

        /*TextView textView = (TextView) findViewById(R.id.selected_text);
        textView.setText(selectedText);*/
        ImageView imageView = (ImageView) findViewById(R.id.selected_photo);
        imageView.setImageResource(selectedPhoto);

        // リストビューに表示するためのデータを設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1);
        adapter.add("listview item 1");
        adapter.add("listview item 2");
        adapter.add("listview item 3");

        // リストビューにデータを設定
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        // 指定したIDのラジオボタンをチェック
        radioGroup.check(R.id.radioButtonFile);
        // チェックされているラジオボタンのIDを取得
        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        // ラジオグループのチェック状態が変更された時に呼び出されるコールバックリスナーを登録
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            // ラジオグループのチェック状態が変更された時に呼び出す
            // チェック状態が変更されたラジオボタンのIDが渡される
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                Toast.makeText(CompanyInformationActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

                /*switch(checkedId){
                    case R.id.radioButtonFile:

                        break;
                    case R.id.radioButtonInfo:

                        break;
                }*/

            }

        });
    }


}
