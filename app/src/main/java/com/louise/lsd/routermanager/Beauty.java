package com.louise.lsd.routermanager;

import android.app.Application;

import com.louise.lsd.routermanager.router.RouterManager;

/**
 * @author lsd
 * @date 2018/3/7.
 */

public class Beauty extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RouterManager.getInstance().register("beauty://xxx/second",SecondActivity.class);
    }
}
