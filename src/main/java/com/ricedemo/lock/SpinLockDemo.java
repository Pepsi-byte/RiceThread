package com.ricedemo.lock;


import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 手写实现一个自旋锁
 *
 * 首先：当线程t1首先调用lock方法的时候，由于一开始没有给原子类AtomicReference设置初始值
 * 所以:主物理内存中的值为null，threadAtomicReference.compareAndSet(null,thread)：
 * t1进行cas比较与交换成功，加上！就成了false,
 * 所以t1就不会进入到while里面
 *
 * t1线程沉睡3秒
 *
 * 此时在t1睡的时候，t2抢到了线程的执行权，进入riceLock方法里面执行
 *此时的朱物理内存的值已经修改成了t1的名称，不为空，cas失败返回false
 * 在加上！变成true
 *所以t2线程就会进去while循环，并且在里面不断的尝试获取锁
 *
 * 当过了3秒之后，线程1醒过来了，继续执行riceUnlock方法
 * 在里面cas,将朱物理内存的值设置为null
 *
 * 当t1设置完成之后，由于t2一直在while中循环，当察觉到了主物理内存中的值为null 预期值也为null
 * 就跳出循环，执行解锁方法
 */
public class SpinLockDemo {
    AtomicReference<Thread> threadAtomicReference=new AtomicReference<>();

    //加锁
    public void riceLock(){
        Thread thread=Thread.currentThread();//获取当前线程
        System.out.println(thread.getName()+"\t come in!");
        while (!threadAtomicReference.compareAndSet(null,thread)){
            System.out.println(thread.getName()+"正在尝试获得锁");
        }
    }

    //解锁
    public void riceUnLock(){
        Thread thread=Thread.currentThread();
        threadAtomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"\t come out/!");
    }


    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo=new SpinLockDemo();

        //创建线程t1
        new Thread("t1"){
            public void run(){
                //加锁
                spinLockDemo.riceLock();

                //睡3秒
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //解锁
                spinLockDemo.riceUnLock();
            }
        }.start();

       //主线程停一秒，确保t1线程先执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建线程t2
        new Thread("t2"){
            public void run(){
                spinLockDemo.riceLock();
                spinLockDemo.riceUnLock();
            }
        }.start();
    }
}
