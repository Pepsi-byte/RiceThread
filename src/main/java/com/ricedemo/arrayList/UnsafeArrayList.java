package com.ricedemo.arrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnsafeArrayList {
    public static void main(String[] args) {

//        List<String> list=new ArrayList<>();

        //使用collections集合工具类，将不安全的list  map  set 变成线程安全的
//        List<String> list= Collections.synchronizedList(new ArrayList<>());

        //不使用vector也不使用collections，怎么解决arrayList的先承认那个不安全问题
        List<String> list=new CopyOnWriteArrayList<>();

        //创建3个线程，每个线程都对list执行写操作
        for(int i=1;i<=20;i++){
            new Thread(){
                public void run(){
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list);
                }
            }.start();

            /**
             * java.util.ConcurrentModificationException
             *
             */
        }
    }
}
