package com.fenggood.dagger2scopelearn.modules;

import com.fenggood.dagger2scopelearn.beans.User;
import com.fenggood.dagger2scopelearn.scopes.UserScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linfeng on 2018/4/3.
 */
@Module
@UserScope
public class UserModuleCustom {
    @Provides
    @UserScope
    User providerUser(){
        return new User();
    }
}
