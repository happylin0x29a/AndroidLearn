package com.fenggood.dagger2learn.injectway.injectConsWay;

/**
 * Created by linfeng on 2018/3/29.
 */

public class ClassA {
    ClassB classB;

    public ClassA(ClassB classB) {
        this.classB = classB;
    }
}
