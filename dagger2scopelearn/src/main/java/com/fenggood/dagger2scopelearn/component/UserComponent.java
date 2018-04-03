package com.fenggood.dagger2scopelearn.component;

import com.fenggood.dagger2scopelearn.MainActivity;
import com.fenggood.dagger2scopelearn.modules.UserModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by linfeng on 2018/4/3.
 */
@Component(modules = UserModule.class)
@Singleton
public interface UserComponent {
    void inject(MainActivity mainActivity);
}
