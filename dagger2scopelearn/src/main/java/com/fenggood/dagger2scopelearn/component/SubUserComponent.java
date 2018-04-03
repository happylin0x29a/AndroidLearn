package com.fenggood.dagger2scopelearn.component;

import com.fenggood.dagger2scopelearn.BActivity;
import com.fenggood.dagger2scopelearn.modules.SubComponentModule;
import com.fenggood.dagger2scopelearn.scopes.UserScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by linfeng on 2018/4/3.
 */
//@UserScope
//@Component(dependencies = combinaComponent.class,modules = SubComponentModule.class)
public interface SubUserComponent {
    void inject(BActivity bActivity);
}
