package com.fenggood.dagger2learn.mvpNormal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by linfeng on 2018/3/29.
 */

public class MvpActivity extends AppCompatActivity implements MvpView {
    private MvpPresenter mvpPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter=new MvpPresenter(this);//当修改presenter时修修改这里的代码
        mvpPresenter.loadData();//处理业务逻辑
    }

    @Override
    public void updateUI() {
        //做更新UI的操作
    }
}
