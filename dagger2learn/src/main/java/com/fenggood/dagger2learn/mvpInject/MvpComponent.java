package com.fenggood.dagger2learn.mvpInject;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by linfeng on 2018/3/29.
 */
@Singleton
@Component(modules = MvpModule.class)
public interface MvpComponent {
    void inject(MvpActivity mvpActivity);
}
