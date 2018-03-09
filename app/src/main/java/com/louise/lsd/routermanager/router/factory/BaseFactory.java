package com.louise.lsd.routermanager.router.factory;

import android.content.Context;
import android.net.Uri;

import java.lang.reflect.Method;

/**
 * @author lsd
 * @date 2018/3/6.
 */

public abstract class BaseFactory implements IFactory {

    @Override
    public void redirectSchema(Context context, Method method, Uri uri) {
        getSchemaService().onMethodCallBack(context, method, uri);
    }
}
