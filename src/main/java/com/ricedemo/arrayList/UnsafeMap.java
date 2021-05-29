package com.ricedemo.arrayList;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UnsafeMap {
    public static void main(String[] args) {
        Map<String,String> map=new ConcurrentHashMap<>();

        for(int i=1;i<=10;i++){

            new Thread("Thread-"+i){
                public void run(){
                    map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                    System.out.println(map);
                }
            }.start();
        }

    }

    }

