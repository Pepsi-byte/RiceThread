package com.ricedemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo01 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);//原子类，定义初始值为5

        Boolean result=atomicInteger.compareAndSet(5,2019);//cas:返回值是boolean类型
        Boolean result1=atomicInteger.compareAndSet(5,2021);//cas:返回值是boolean类型


//        atomicInteger.get():获取当前主物理内存中的值
        System.out.println(result+"---------"+atomicInteger.get());
        System.out.println(result1+"---------"+atomicInteger.get());
    }
}
