package com.fenggood.dagger2scopelearn.component;

import com.fenggood.dagger2scopelearn.BActivity;
import com.fenggood.dagger2scopelearn.MainActivity;
import com.fenggood.dagger2scopelearn.modules.UserModuleCustom;
import com.fenggood.dagger2scopelearn.scopes.UserScope;

import dagger.Component;

/**
 * Created by linfeng on 2018/4/3.
 */
@Component(modules = UserModuleCustom.class)
@UserScope
public interface UserComponentCustom {
    void inject(MainActivity mainActivity);
    void inject(BActivity bActivity);
}
