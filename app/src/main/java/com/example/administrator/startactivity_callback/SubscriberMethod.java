package com.example.administrator.startactivity_callback;

import java.lang.reflect.Method;

/**
 * Created by kaiyi.cky on 2015/8/11.
 */
public class SubscriberMethod {
    Method m;
    int type;
    public SubscriberMethod(Method m, Class<?> param, int type) {
        this.m = m;
        this.type = type;
    }
}
