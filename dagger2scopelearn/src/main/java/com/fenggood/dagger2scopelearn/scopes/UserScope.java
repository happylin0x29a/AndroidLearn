package com.fenggood.dagger2scopelearn.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by linfeng on 2018/4/3.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
