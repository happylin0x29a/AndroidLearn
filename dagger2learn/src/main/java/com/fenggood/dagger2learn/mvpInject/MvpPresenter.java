package com.fenggood.dagger2learn.mvpInject;

import javax.inject.Inject;

/**
 * Created by linfeng on 2018/3/29.
 */
//MVP中的P   处理业务逻辑
public class MvpPresenter {
    private MvpView mvpView;
    @Inject
    public MvpPresenter(MvpView mvpView) {
        this.mvpView = mvpView;
    }
    public void loadData(){
        //请求数据等等。。。
        //执行成功后 回调mvpview的更新UI方法
        mvpView.updateUI();
    }
}
