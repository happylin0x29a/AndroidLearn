package com.fenggood.dagger2learn.mvpInject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fenggood.dagger2learn.R;

import javax.inject.Inject;

/**
 * Created by linfeng on 2018/3/29.
 */

public class MvpActivity extends AppCompatActivity implements MvpView {
    @Inject
     MvpPresenter mvpPresenter;//不能用private修饰符修饰类的成员属性
    // 当看到某个类被@Inject标记时，就会到他的构造方法中，
    // 如果这个构造方法也被@Inject标记的话，就会自动初始化这个类，从而完成依赖注入

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMvpComponent.builder().mvpModule(new MvpModule(this))
                .build()
                .inject(this);
        mvpPresenter.loadData();//处理业务逻辑
    }

    @Override
    public void updateUI() {
        //做更新UI的操作
        Log.i("updateUI","gogogo");
    }
}
