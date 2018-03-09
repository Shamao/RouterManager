package com.louise.lsd.routermanager.router.factory;

import android.content.Context;
import android.net.Uri;

import com.louise.lsd.routermanager.router.service.ISchemaService;

import java.lang.reflect.Method;

/**
 * @author lsd
 * @date 2018/3/5.
 */

public interface IFactory {

    void redirectSchema(Context context, Method method, Uri uri);

    ISchemaService getSchemaService();

    Class<?> getSchemaServiceClass();
}
