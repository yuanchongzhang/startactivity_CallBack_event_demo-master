package com.example.administrator.startactivity_callback;

import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kaiyi.cky on 2015/8/11.
 */
public class EventBus {
    HashMap<Class<?>,ArrayList<Subscription>> subscriptionsByEventType =
            new HashMap<Class<?>,ArrayList<Subscription>>();
    MainThreadHandler mainThreadHandler = new MainThreadHandler(this, Looper.getMainLooper());
    AsyThreadHandler asyThreadHandler = new AsyThreadHandler(this);

    private final static EventBus instance = new EventBus();
    public static EventBus getInstance(){
           return instance;
    }
    private EventBus(){};

    public void register(Object subscriber){
        Class<?> clazz = subscriber.getClass();
        Method[] methods = clazz.getMethods();
        for(Method m:methods){
            String name = m.getName();
            if(name.startsWith("onEvent")){
                Class<?>[] params = m.getParameterTypes();
                ArrayList<Subscription> arr;
                if(subscriptionsByEventType.containsKey(params[0])){
                    arr = subscriptionsByEventType.get(params[0]);
                }else{
                    arr = new ArrayList<Subscription>();
                }
                int len = name.substring("onEvent".length()).length();
                Log.i("cky",len+"");
                Subscription sub;
                if(len==0){
                    sub =  new Subscription(subscriber,new SubscriberMethod(m,params[0],0));
                }else{
                    sub =  new Subscription(subscriber,new SubscriberMethod(m,params[0],1));
                }
                arr.add(sub);
                subscriptionsByEventType.put(params[0],arr);
            }
        }
    }

    public void post(Object event){
        Class<?> clazz = event.getClass();
        ArrayList<Subscription> arr = subscriptionsByEventType.get(clazz);
        for(Subscription sub:arr){
            if(sub.SubscriberMethod.type==0){
                asyThreadHandler.post(sub,event);
            }else{
                mainThreadHandler.post(sub,event);
            }
        }
    }

    public void invoke(final Subscription sub, final Object event, Method m){
        try {
            m.invoke(sub.subscriber,event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
