package com.ricedemo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：
 *场景1：不介入任何锁，用5个线程进行写操作   5个线程进行读操作
 *        此时多线程并发，就会导致写还没有写完，就被其他的线程抢走执行权
 *场景2：加入锁：Locl lock=new TeenTrantLock();
 *      此时只能保证数据的一致性，但是不能保证读操作的并发性
 *
 * 解决：使用可重入的读写锁，保证数据的一致性和并发性
 *       实现了读写分离
 *
 */
class MyCache{
    private volatile Map<Object,Object> map=new HashMap<>();

    //加入可重入的读写锁
    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    //写操作
    public void  put(Object key,Object value){
        reentrantReadWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在写入");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        }catch (Exception e){
         e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    //读操作
    public void get(Object key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在读取");
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成："+value);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

}

public class ReentranrReadWriteLock {

    MyCache  myCache=new MyCache();

    public static void main(String[] args) {
        MyCache  myCache=new MyCache();

        //假设有5个线程在读  有5个线程在写
        //写
        for(int i=1;i<=5;i++){
            final int tempInt=i;
            new Thread(String.valueOf(tempInt)){
                public void run(){
                    myCache.put(tempInt,tempInt);
                }
            }.start();
        }

        //读
        for(int i=1;i<=5;i++){
            final int tempInt=i;
            new Thread(String.valueOf(tempInt)){
                public void run(){
                    myCache.get(tempInt);
                }
            }.start();
        }
    }



}
