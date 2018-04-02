package com.fenggood.dagger2learn.mvpInject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linfeng on 2018/3/29.
 * Module类主要是为了提供那些没有构造函数的类的依赖，
 * 这些类无法用@Inject标注，比如第三方类库，系统类，View接口
 */
@Module
public class MvpModule {
    private  MvpView mvpView;

    public MvpModule(MvpView mvpView) {
        this.mvpView=mvpView;
    }
    @Provides
    @Singleton
    MvpView provideMvpView(){
        return mvpView;
    }
}
