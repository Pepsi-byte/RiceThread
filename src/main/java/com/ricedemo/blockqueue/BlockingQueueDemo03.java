package com.ricedemo.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo03 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);

        //想队列里面添加元素   没有返回结果
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println("**********");
//当队列满了之后，还是会向队列一直put元素，队列会一直阻塞
        blockingQueue.put("x");//一直阻塞  插入不进去一直等在门口

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();//一直阻塞  没拿到队列中的元素就一直阻塞的等待您




    }
}
