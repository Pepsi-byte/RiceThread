package com.ricedemo.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题定义和解决
 */
public class AtomicStampReference {

    static AtomicReference<Integer> integerAtomicReference=new AtomicReference<>(100);//设置初始值为100
    //两个参数，一个是初始值，一个是自己设置的时间戳，也称为版本号
    static AtomicStampedReference<Integer> integerAtomicStampedReference=
            new AtomicStampedReference<>(100,1);

    public static void main(String[] args) throws InterruptedException {
        //ABA问题演示
        //创建线程t1,先将100变成101    在从101 变回100
        System.out.println("ABA问题的产生");
        new Thread("t1"){
            public void run(){
                //进行第一次比较与交换
                Boolean result1=integerAtomicReference.compareAndSet(100,101);
                //进行第二次cas
                Boolean result2=integerAtomicReference.compareAndSet(101,100);
            }
        }.start();

        //创建线程t2
        /**
         * 此时对于t2线程来说，修改成功，他只看头和尾的值是否一致，而不管中间t1线程执行了什么操作
         */
        new Thread("t2"){
            public void run(){
                //让线程睡1秒，确保t1线程先执行完ABA
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //在t1线程执行完了之后，在惊醒cas
                Boolean result3=integerAtomicReference.compareAndSet(100,2021);

                //t2===是否执行成功:true===当前主物理内存中的值：2021
                System.out.println(Thread.currentThread().getName()+"==="+"是否执行成功:"
                        +result3+"===当前主物理内存中的值："+integerAtomicReference.get());
            }
        }.start();

        //要先让上面的t1  t2线程先执行完
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //=======解决ABA========
        //AtomicStampedReference
        System.out.println("ABA问题的解决");
        new Thread("t3"){
            public void run(){

                int s=integerAtomicStampedReference.getStamp();//首先先获取还没有惊醒比较与交换的时间戳
                System.out.println("第一次获取到的版本号："+s);
                //获取到了之后，让t3线程睡一秒，是t4线程也可以获取到和t3一样的还没有进行操作的事件戳
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //执行比较与交换
                //四个参数分别是：期望值  新值  期望版本号   新的版本号
                integerAtomicStampedReference.compareAndSet(100,101,
                        integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp()+1);
                System.out.println("第二次版本号："+integerAtomicStampedReference.getStamp());

                //第二次比较与交换
                integerAtomicStampedReference.compareAndSet(101,100,
                        integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp()+1);
                System.out.println("第三次版本号："+integerAtomicStampedReference.getStamp());

            }

        }.start();


        //t4线程
        new Thread("t4"){
            public void run(){
              int stamp=integerAtomicStampedReference.getStamp();//获取到还没有操作的时间戳

                //获取到了之后，睡眠，是t3线程继续执行
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //确保上面的t3线程执行完了之后，执行t4线程
               Boolean flag3= integerAtomicStampedReference.compareAndSet(100,2021,stamp,stamp+1);
                System.out.println(Thread.currentThread().getName()+"操作是否成功："+flag3);
                System.out.println("获取到的时间戳:"+integerAtomicStampedReference.getStamp());
                System.out.println("获取到的主物理内存的值："+integerAtomicStampedReference.getReference());
            }
        }.start();

    }

}
