package com.fenggood.dagger2learn.diproblemexample;

/**
 * Created by linfeng on 2018/3/28.
 */
//A依赖了B，必须借助B的方法才能完成一些功能
public class ClassA {
    ClassB classB;
    //如果我们想在实例化ClassB的时候传入参数，那么不得不改动ClassA的构造方法，不符合开闭原则（对扩展开放，对修改关闭）
    public ClassA() {
        classB=new ClassB();//违反单一职责原则（一个类，只有一个引起它变化的原因）
    }
    public void done(){
        classB.doSomething();
    }
}
