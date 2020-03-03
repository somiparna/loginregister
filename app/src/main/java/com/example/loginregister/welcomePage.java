package com.example.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class welcomePage extends AppCompatActivity {

    TextView Welcome,name,email,mobno;
    ImageView face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Welcome = findViewById(R.id.tvWelcome);
        name = findViewById(R.id.dispName);
        email= findViewById(R.id.dispMob);
        mobno= findViewById(R.id.dispMob);
    }
}
