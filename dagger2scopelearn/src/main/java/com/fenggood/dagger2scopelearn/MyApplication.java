package com.fenggood.dagger2scopelearn;

import android.app.Application;

import com.fenggood.dagger2scopelearn.component.DaggerUserComponentCustom;
import com.fenggood.dagger2scopelearn.component.UserComponentCustom;
import com.fenggood.dagger2scopelearn.modules.UserModuleCustom;


/**
 * Created by linfeng on 2018/4/3.
 */

public class MyApplication extends Application {
    UserComponentCustom userComponentCustom;
    @Override
    public void onCreate() {
        super.onCreate();
        userComponentCustom=DaggerUserComponentCustom.builder().userModuleCustom(new UserModuleCustom()).build();
    }
    UserComponentCustom getUserComponentCustom(){
        return userComponentCustom;
    }
}
