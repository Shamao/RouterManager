package com.louise.lsd.routermanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.louise.lsd.routermanager.annotation.Router;
import com.louise.lsd.routermanager.router.RouterManager;

@Router("main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager.getInstance().redirect(MainActivity.this, Uri.parse("beauty://xxx/second"), null);
            }
        });
    }

    public void getActivityInfo(Context context, String className) {

    }
}