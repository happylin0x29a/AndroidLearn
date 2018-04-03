package com.fenggood.dagger2scopelearn.component;

import com.fenggood.dagger2scopelearn.beans.User;
import com.fenggood.dagger2scopelearn.modules.AppModule;
import com.fenggood.dagger2scopelearn.modules.UserModuleCustom;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by linfeng on 2018/4/3.
 */
//@Singleton
//@Component(modules = {UserModuleCustom.class, AppModule.class})
public interface combinaComponent {
    User user();//对子组件提供user
}
