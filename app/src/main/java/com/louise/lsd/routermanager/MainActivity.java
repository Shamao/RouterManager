package com.louise.lsd.routermanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.louise.lsd.routermanager.router.RouterManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager.getInstance().redirect(MainActivity.this, "xxx://www.xxx.com/user/second", null);
            }
        });
    }

    public void getActivityInfo(Context context, String className) {

    }
}