package com.example.administrator.startactivity_callback;

/**
 * Created by kaiyi.cky on 2015/8/11.
 */
public class AsyThreadHandler {
    private EventBus eventBus;
    AsyThreadHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void post(final Subscription sub, final Object event){
        new Thread(){
            @Override
            public void run() {
                eventBus.invoke(sub,event,sub.SubscriberMethod.m);
            }
        }.start();
    }
}
