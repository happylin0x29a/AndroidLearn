package com.fenggood.dagger2learn.injectway.injectAnno;

import javax.inject.Inject;

/**
 * Created by linfeng on 2018/3/29.
 */

public class ClassA {
    //此时并不会完成注入，还需要依赖注入框架的支持，如RoboGuice,Dagger2
    @Inject
    ClassB classB;

    public ClassA(ClassB classB) {
        this.classB = classB;
    }
}
