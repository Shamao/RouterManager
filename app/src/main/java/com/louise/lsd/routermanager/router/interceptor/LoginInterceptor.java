package com.louise.lsd.routermanager.router.interceptor;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;


/**
 * @author lsd
 * @date 2018/3/6.
 */

public class LoginInterceptor implements Interceptor {

    @Override
    public boolean isIntercept(Context context, Uri uri) {
        String needLogin = uri.getQueryParameter("needlogin");
        String need_Login = uri.getQueryParameter("need_login");

        if ("2".equals(need_Login)) {
            return true;
        }

        return false;
    }

    @Override
    public void onIntercept(Context context, Uri uri) {
        // 进行降级
        Toast.makeText(context, "请先进行登录", Toast.LENGTH_SHORT).show();
    }
}
