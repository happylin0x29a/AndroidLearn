package com.fenggood.dagger2learn.component;

import com.fenggood.dagger2learn.MainActivity;

import dagger.Component;

/**
 * Created by linfeng on 2018/3/28.
 */
@Component
public interface FoodComponent {
    void inject(MainActivity mainActivity);//要注入到那个类就写哪个类
    //创建component后build会生成这个接口的实现类DaggerFoodComponent
}
