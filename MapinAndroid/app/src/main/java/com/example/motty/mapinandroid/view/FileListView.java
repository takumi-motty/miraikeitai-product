package com.example.motty.mapinandroid.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.motty.mapinandroid.R;
import com.example.motty.mapinandroid.model.ShopFile;

public class FileListView extends LinearLayout{
    private Context context;
    private ImageView fileImage;
    private TextView fileName;

    public FileListView(Context context) {
        this(context, null);
    }

    public FileListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FileListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.list_file_adapter, this);
        fileImage = (ImageView) findViewById(R.id.imageFile);
        fileName = (TextView) findViewById(R.id.textFileName);
    }

    public void setShopFile(ShopFile shopFile){
        fileName.setText(shopFile.getName());

        switch(shopFile.getFileType()){
            case "pdf":
                fileImage.setImageResource(R.drawable.pdf);
                break;
            case "png":
                fileImage.setImageResource(R.drawable.image);
                break;
            case "jpg":
                fileImage.setImageResource(R.drawable.image);
                break;
            case "docx":
                fileImage.setImageResource(R.drawable.word);
                break;
            case "xls":
                fileImage.setImageResource(R.drawable.excel);
                break;
            case "txt":
                fileImage.setImageResource(R.drawable.text);
                break;
            case "pptx":
                fileImage.setImageResource(R.drawable.powerpoint);
                break;
            default:
                fileImage.setImageResource(R.drawable.unknown);
        }

    }
}
