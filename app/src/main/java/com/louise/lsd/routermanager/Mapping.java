package com.louise.lsd.routermanager;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author lsd
 * @date 2018/3/12.
 */

public class Mapping {
    private final String url;
    private final Class<? extends Activity> activity;
    private final String mRouterName;

    public Mapping(String url, Class<? extends Activity> activity) {
        this.url = url;
        this.activity = activity;
        this.mRouterName = getRouterName(url);
    }

    public String getRouterName(@NonNull String url) {
        Uri uri = Uri.parse(url);
        String path = uri.getPath();

        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        return path;
    }

    public boolean matchRouterName(String routerName) {
        if (mRouterName == null || routerName == null) {
            return false;
        }

        return mRouterName.equals(routerName);
    }

    public String getUrl() {
        return url;
    }

    public Class<? extends Activity> getActivity() {
        return activity;
    }

    public String getRouterName() {
        return mRouterName;
    }
}
