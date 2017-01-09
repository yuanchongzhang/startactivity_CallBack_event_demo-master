package com.example.administrator.startactivity_callback;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by kaiyi.cky on 2015/8/11.
 */
public class MainThreadHandler extends Handler {
    private final EventBus eventBus;

    MainThreadHandler(EventBus eventBus, Looper looper) {
        super(looper);
        this.eventBus = eventBus;
    }

    @Override
    public void handleMessage(Message msg) {
        EventBus.getInstance().invoke(sub,event,sub.SubscriberMethod.m);
    }

    Subscription sub;
    Object event;
    public void post(Subscription sub, Object event){
        this.sub = sub;
        this.event = event;
        sendEmptyMessage(0);
    }
}
