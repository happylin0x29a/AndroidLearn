package com.fenggood.dagger2learn.beans;

import javax.inject.Inject;

/**
 * Created by linfeng on 2018/3/28.
 */

public class Person {
    private Food food;
    @Inject
    public Person(Food food) {
        this.food=food;
    }
    public String eat(){
        return food.eated();
    }
}
