package com.example.b10.ContentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b10.R;

public class HomeActivity extends AppCompatActivity {


    private TextView name, email;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        name = findViewById(R.id.name);
//        email = findViewById(R.id.email);
//        btn_logout = findViewById(R.id.btn_logout);
//
//        Intent intent = getIntent();
//        String extraName = intent.getStringExtra("name");
//        String extraEmail = intent.getStringExtra("email");
//
//        name.setText(extraName);
//        email.setText(extraEmail);
//
//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}