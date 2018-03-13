package com.louise.lsd.routermanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.louise.lsd.routermanager.annotation.Router;

@Router("/second")
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
