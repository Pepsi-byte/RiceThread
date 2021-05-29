package com.ricedemo.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 这个是做加法的，是等到所有的线程都到了，在执行main里面的
         *
         */
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{System.out.println("召唤神龙");});

        for(int i=1;i<=7;i++){
            final int tempI=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第"+tempI+"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
