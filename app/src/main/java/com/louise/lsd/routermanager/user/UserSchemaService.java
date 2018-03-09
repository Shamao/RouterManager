package com.louise.lsd.routermanager.user;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.louise.lsd.routermanager.SecondActivity;
import com.louise.lsd.routermanager.router.service.BaseSchemaService;

/**
 * @author lsd
 * @date 2018/3/5.
 */

public class UserSchemaService extends BaseSchemaService {

    public static class Holder {
        public static UserSchemaService instance = new UserSchemaService();
    }

    public static UserSchemaService getInstance() {
        return Holder.instance;
    }

    // ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

    public void redirectSecond(Context context, Uri uri) {
        Intent intent = new Intent();
        intent.setClass(context, SecondActivity.class);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
