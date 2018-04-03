package com.fenggood.dagger2scopelearn.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linfeng on 2018/4/3.
 */
@Module
public class AppModule {
    private Application app;
    public AppModule(Application application) {
        this.app=application;
    }
    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}
