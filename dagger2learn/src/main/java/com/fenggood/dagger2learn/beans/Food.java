package com.fenggood.dagger2learn.beans;

import javax.inject.Inject;

/**
 * Created by linfeng on 2018/3/28.
 */

public class Food {
    //Food构造方法不需要传参数，但是我们也用@Inject标注了，这是因为在Person构造方法中需要传入Food
    @Inject
    public Food() {
    }
    public String eated(){
        return "吃东西";
    }
}
