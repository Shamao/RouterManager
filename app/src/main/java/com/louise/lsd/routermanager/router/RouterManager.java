package com.louise.lsd.routermanager.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.louise.lsd.routermanager.Mapping;
import com.louise.lsd.routermanager.router.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsd
 * @date 2018/3/5.
 */

public class RouterManager {
    public List<Interceptor> mGlobalInterceptors = new ArrayList<>();
    private List<Mapping> mMapping = new ArrayList<>();

    public static class Holder {
        public static RouterManager mInstance = new RouterManager();
    }

    private RouterManager() {

    }

    public static RouterManager getInstance() {
        return Holder.mInstance;
    }

    public void register(String url, Class<? extends Activity> activity) {
        Mapping mapping = new Mapping(url, activity);
        mMapping.add(mapping);
    }

    public void registerGlonalInteceptor(@NonNull Interceptor interceptor) {
        mGlobalInterceptors.add(interceptor);
    }

    public void redirect(Context context, @Nullable Uri uri, Interceptor interceptor) {
        if (uri == null) {
            return;
        }

        if (checkGlobalInterceptor(context, uri)) {
            return;
        }

        if (checkInterceptor(context, uri, interceptor)) {
            return;
        }

        String routerName = getRouterName(uri);
        for (Mapping mapping : mMapping) {
            if (mapping.matchRouterName(routerName)) {
                redirectActivity(context, mapping);
                break;
            }
        }
    }

    private void redirectActivity(Context context, Mapping mapping) {
        Class activityClz = mapping.getActivity();
        if (activityClz == null) {
            return;
        }

        Intent intent = new Intent(context, mapping.getActivity());
        context.startActivity(intent);
    }


    public boolean checkGlobalInterceptor(Context context, Uri uri) {
        for (int i = 0; i < mGlobalInterceptors.size(); i++) {
            Interceptor interceptor = mGlobalInterceptors.get(i);
            if (interceptor.isIntercept(context, uri)) {
                interceptor.onIntercept(context, uri);
                return true;
            }
        }
        return false;
    }

    private boolean checkInterceptor(Context context, Uri uri, Interceptor interceptor) {
        if (interceptor == null) {
            return false;
        }

        if (interceptor.isIntercept(context, uri)) {
            interceptor.onIntercept(context, uri);
            return true;

        }
        return false;
    }
    // ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

    public String getRouterName(@NonNull Uri uri) {
        String path = uri.getPath();
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        return path;
    }

}
