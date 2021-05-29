package com.ricedemo.volatile2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不保证JMM的原子性
 *
 * 此时，如果数据量变大，那么每一次运行的结果度不会是20000，会导致数据的丢失
 * 只有加上synchronized关键字之后，才可以解决
 * 但是synchronized是重量级的锁，执行效率低
 */
public class VolatileDemo02 {
    public static void main(String[] args) {
        final Student1 student1=new Student1();

        //创建100个线程
        for(int i=1;i<=20;i++){
            new Thread(() -> {
                for (int i1 = 1; i1 <= 1000; i1++) {
                    student1.incAge();
                    student1.addAtomicInteger();
                }
            }).start();
        }

        //需要等待上面的线程全部计算完，在输出
        //此时如果上面的线程全部计算完，就只剩下main线程和后台的gc线程
        //大于2的话，就说明上面相加的线程还没有计算完
        while(Thread.activeCount()>2) {
          Thread.yield();//把自己CPU执行的时间让掉, 让自己或者其它的线程运行
        }

        System.out.println("线程名称:" + Thread.currentThread().getName() + "\t student Age is " + student1.age);
        System.out.println("线程名称:" + Thread.currentThread().getName() + "\t student atomicInteger is " + student1.atomicInteger);
    }
}

class Student1{
   volatile int age=0;

    public void addAge(){
        age=21;
    }

    /**
     * synchronized:在某一时刻只能有一个线程进来操作
     */
    public  void incAge(){
        //注意，此时的age是加了volatile关键字的，保证了可见性
        age++;
    }

    AtomicInteger atomicInteger=new AtomicInteger();
    public void addAtomicInteger(){
        atomicInteger.getAndIncrement();//atomicInteger++
    }
}
