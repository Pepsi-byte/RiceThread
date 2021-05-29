package com.ricedemo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock:也是可重入锁
 * 主要的作用就是为了避免死锁
 */
class Phone1 implements Runnable{

    @Override
    public void run() {
        get();
    }

    Lock lock=new ReentrantLock();//不写默认就是false;表示的是非公平锁

    public void get(){
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"******发送信息！");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"*****发送邮件！");
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
}
public class ReentrantlockDemo {
    public static void main(String[] args) {
        Phone1 phone1=new Phone1();
        Thread t3=new Thread(phone1);
        t3.setName("t3");
        Thread t4=new Thread(phone1);
        t4.setName("t4");









































































































































































        t3.start();
        t4.start();

    }



}
