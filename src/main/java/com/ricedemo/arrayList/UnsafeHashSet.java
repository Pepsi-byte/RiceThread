package com.ricedemo.arrayList;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class UnsafeHashSet {

    public static void main(String[] args) {
//        Set<String> set= Collections.synchronizedSet(new HashSet<>());
        Set<String> set=new  CopyOnWriteArraySet<>();

        for(int i=1;i<=10;i++){
        new Thread(){
            public void run(){
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }
        }.start();
        }

    }

}
