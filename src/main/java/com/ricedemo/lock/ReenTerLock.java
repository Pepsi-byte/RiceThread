package com.ricedemo.lock;

/**
 * synchronized:表示的是可重入锁，锁住的是整个对象
 */
class Phone{
    public synchronized void sendMessage(){
        System.out.println(Thread.currentThread().getName()+"====发送信息！");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"====发送邮件！");
    }
}
public class ReenTerLock {

    public static void main(String[] args) {
        Phone phone=new Phone();

        //创建线程1
        new Thread("t1"){
            public void run(){
                phone.sendMessage();
            }
        }.start();


        new Thread("t2"){
            public void run(){
                phone.sendMessage();
            }
        }.start();

    }

}
