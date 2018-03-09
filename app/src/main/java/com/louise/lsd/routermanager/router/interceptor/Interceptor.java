package com.louise.lsd.routermanager.router.interceptor;

import android.content.Context;
import android.net.Uri;

/**
 * @author lsd
 * @date 2018/3/6.
 */

public interface Interceptor {
    String S_STR_ONE = "1";

    boolean isIntercept(Context context, Uri uri);

    void onIntercept(Context context, Uri uri);
}
