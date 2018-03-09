package com.louise.lsd.routermanager.router.service;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.lang.reflect.Method;

/**
 * @author lsd
 * @date 2018/3/5.
 */

public interface ISchemaService {

    void onMethodCallBack(@NonNull Context context, @NonNull Method method, @NonNull Uri uri);


}
