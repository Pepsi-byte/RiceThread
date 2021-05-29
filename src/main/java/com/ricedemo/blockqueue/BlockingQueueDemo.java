package com.ricedemo.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<>(3);//设置这个阻塞队列的初始容量为3

        //add方法，想这个阻塞队列中添加元素，返回的是boolean值，
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));

        //容量已满时添加   插入不进去就会直接报异常
        //Exception in thread "main" java.lang.IllegalStateException: Queue full
        //System.out.println(arrayBlockingQueue.add("d"));

        //校验
        System.out.println(arrayBlockingQueue.element());

        //队列：先进先出
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());

        //移除不存在的元素的时候，报异常
        //Exception in thread "main" java.util.NoSuchElementException
        System.out.println(arrayBlockingQueue.remove());


//        System.out.println(arrayBlockingQueue.remove("a"));


    }
}
