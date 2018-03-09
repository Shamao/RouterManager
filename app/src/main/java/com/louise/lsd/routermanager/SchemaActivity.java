package com.louise.lsd.routermanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.louise.lsd.routermanager.router.RouterManager;

/**
 * @author lsd
 * @date 2018/3/7.
 */

public class SchemaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        if (RouterManager.getInstance().checkGlobalInterceptor(this, uri)) {
            return;
        }

        RouterManager.getInstance().go(this, uri);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}
