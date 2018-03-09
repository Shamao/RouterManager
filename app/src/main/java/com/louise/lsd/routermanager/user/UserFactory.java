package com.louise.lsd.routermanager.user;


import com.louise.lsd.routermanager.router.factory.BaseFactory;
import com.louise.lsd.routermanager.router.service.ISchemaService;

/**
 * @author lsd
 * @date 2018/3/5.
 */

public class UserFactory extends BaseFactory {

    public static class Holder {
        public static UserFactory instance = new UserFactory();
    }

    public static UserFactory getInstance() {
        return Holder.instance;
    }

    @Override
    public ISchemaService getSchemaService() {
        return UserSchemaService.getInstance();
    }

    @Override
    public Class<?> getSchemaServiceClass() {
        return UserSchemaService.class;
    }

}
