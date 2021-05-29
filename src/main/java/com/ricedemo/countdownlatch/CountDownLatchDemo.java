package com.ricedemo.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch=new CountDownLatch(6);

        for(int i=1;i<=6;i++){
            new Thread(CountryEnum.forEach_CountryEnum(i).getRetMessage()){
                public void run(){
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName()+"国，被灭");
                }
            }.start();
        }

        countDownLatch.await();
        System.out.println("*****秦统天下，华夏统一");
        //枚举的使用
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());


    }


    /**
     * 此时没有使用任何锁或者其他的机制，线程并发
     * 如何使让前面6个线程都执行完成之后，主线程再执行
     *
     */
    public void unLock(){
        for(int i=1;i<=6;i++){
            new Thread("t"+i){
                public void run(){
                    System.out.println(Thread.currentThread().getName()+"离开");
                }
            }.start();
        }


        System.out.println(Thread.currentThread().getName()+"班长离开走人");
    }

    }

