package com.example.administrator.startactivity_callback;

/**
 * Created by kaiyi.cky on 2015/8/11.
 */
public class Subscription {
    Object subscriber;
    SubscriberMethod SubscriberMethod;
    public Subscription(Object subscriber, SubscriberMethod SubscriberMethod) {
        this.subscriber = subscriber;
        this.SubscriberMethod = SubscriberMethod;
    }
}
