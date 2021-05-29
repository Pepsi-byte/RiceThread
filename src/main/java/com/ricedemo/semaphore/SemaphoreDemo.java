package com.ricedemo.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);//模拟变成三个车位

        for(int i=1;i<=6;i++){//模拟6辆车
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"->获得车位");
                //获得车位之后停留3秒
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"停留3秒之后释放车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//停留之后将车位释放
                }



            },String.valueOf(i)).start();
        }
    }
}
