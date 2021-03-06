package com.fenggood.dagger2scopelearn.modules;

import com.fenggood.dagger2scopelearn.beans.User;
import com.fenggood.dagger2scopelearn.scopes.UserScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linfeng on 2018/4/3.
 */
@Module
public class SubComponentModule {
    @Provides
    @UserScope
    User providerUser(){
        return  new User();
    }
}
