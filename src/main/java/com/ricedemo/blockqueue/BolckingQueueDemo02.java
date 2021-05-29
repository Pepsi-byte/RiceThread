package com.ricedemo.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;

public class BolckingQueueDemo02 {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<>(3);//设置初始化容量为3

        //offer添加元素   添加成功true
        //队列已满  添加失败返回false  不会抛出异常
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println(arrayBlockingQueue.peek());//检查队列中是否存在，并且返回队列中的第一个元素

        //移除元素
        //要移除的元素存在的时候，就显示移除了的元素
        //要移除的元素在列表中不存在的时候，就返回null
        //不会报异常
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());


    }
}
