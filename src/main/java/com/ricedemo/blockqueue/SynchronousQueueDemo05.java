package com.ricedemo.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynochronousQueue:是一个不存储元素的BlockingQueue
 * 只有等这个队列的元素被取走之后，才可以生产下一个元素
 */
public class SynchronousQueueDemo05 {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new SynchronousQueue<>();

        new Thread(()->{
            //放第一个元素
            try {
                System.out.println(Thread.currentThread().getName()+"\t put a");
                blockingQueue.put("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //放第二个元素
            try {
                System.out.println(Thread.currentThread().getName()+"\t put b");
                blockingQueue.put("b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //放第三个元素
            try {
                System.out.println(Thread.currentThread().getName()+"\t put c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();



        new Thread(()->{
            //睡3秒  获取第一个元素
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //睡3秒  获取第二个元素
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //睡3秒  获取第三个元素
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();



    }
}
