package com.example.b10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        //ẩn thanh tác vụ mặc định
        getSupportActionBar().hide();

        String name = getIntent().getExtras().getString("animal_tensv");
        String gia  = getIntent().getExtras().getString("animal_gia");
        String noidung = getIntent().getExtras().getString("animal_noidung");
        String hinh = getIntent().getExtras().getString("animal_hinhanh");


        //Nhận dữ liệu
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_rating = findViewById(R.id.aa_rating);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_content = findViewById(R.id.aa_description);
        ImageView imageView = findViewById(R.id.aa_thumbnail);

        collapsingToolbarLayout.setTitle(name);

        //thiết lập giá trị cho mỗi chế độ xem
        tv_rating.setText(name);
        tv_studio.setText(gia);
        tv_content.setText(noidung);
        //thiết lập hình ảnh bằng cách sử dụng Glide
        Glide.with(this).load(hinh).into(imageView);
    }
}