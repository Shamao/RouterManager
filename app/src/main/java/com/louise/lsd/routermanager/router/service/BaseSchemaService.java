package com.louise.lsd.routermanager.router.service;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * @author lsd
 * @date 2018/3/6.
 */

public abstract class BaseSchemaService implements ISchemaService {

    @Override
    public void onMethodCallBack(@NonNull Context context, @NonNull Method method, @NonNull Uri uri) {
        try {
            method.invoke(this, context, uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
