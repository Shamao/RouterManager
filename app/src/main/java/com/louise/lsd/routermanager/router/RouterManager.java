package com.louise.lsd.routermanager.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.louise.lsd.routermanager.router.factory.IFactory;
import com.louise.lsd.routermanager.router.interceptor.Interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author lsd
 * @date 2018/3/5.
 */

public class RouterManager {
    public HashMap<String, IFactory> mRelatedMap;
    public List<Interceptor> mGlobalInterceptors;

    private String mScheme;
    private String mHost;

    public static class Holder {
        public static RouterManager instance = new RouterManager();
    }

    private RouterManager() {
        mRelatedMap = new HashMap<>();
        mGlobalInterceptors = new ArrayList<>();
    }

    public static RouterManager getInstance() {
        return Holder.instance;
    }

    /**
     * 注册 host 和 factory
     *
     * @param iFactory
     */
    public void register(@NonNull String group, @NonNull IFactory iFactory) {
        mRelatedMap.put(group, iFactory);
    }

    public void registerGlonalInteceptor(@NonNull Interceptor interceptor) {
        mGlobalInterceptors.add(interceptor);
    }

    public void redirect(Context context, @Nullable String scheme, Interceptor interceptor) {
        if (TextUtils.isEmpty(scheme)) {
            return;
        }

        redirect(context, Uri.parse(scheme), interceptor);
    }

    /**
     * 外部调用方法
     *
     * @param context
     * @param interceptor
     */
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


        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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

    /**
     * Schema Activity中调用
     *
     * @param context
     * @param uri
     */
    public void go(Context context, Uri uri) {
        if (uri == null) {
            return;
        }

        try {
            String path = uri.getPath();
            String[] paths = splitPath(path);

            IFactory factory = mRelatedMap.get(paths[0]);
            if (factory == null) {
                throw new RuntimeException("没有注册" + paths[0] + "对应的factory");
            }

            Method method = factory.getSchemaServiceClass().getMethod(getMethodName(path), Context.class, Uri.class);
            factory.redirectSchema(context, method, uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] splitPath(String path) {
        String[] tempPaths = path.split("/");
        try {
            return new String[]{tempPaths[1], tempPaths[2]};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[2];
    }

    public String getMethodName(String path) {
        StringBuilder builder = new StringBuilder();
        builder.append("redirect");

        String[] itemString = path.split("/");
        for (int i = 0; i < itemString.length; i++) {
            if (TextUtils.isEmpty(itemString[i])) {
                continue;
            }

            builder.append(toUpperCase(itemString[i]));
        }
        return builder.toString();
    }

    public String toUpperCase(String content) {
        char[] cs = content.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }


}
