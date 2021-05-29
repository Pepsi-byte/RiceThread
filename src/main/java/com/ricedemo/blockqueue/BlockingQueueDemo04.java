package com.ricedemo.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo04 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));

        //上面，插入成功，就返回true
        //下面，一直尝试的进行插入  尝试时间为2秒  如果两秒之内还没有插入进去，就返回false
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));

    }
}
