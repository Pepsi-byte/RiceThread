package com.ricedemo.volatile2;

/**
 * 此时线程AAA和主线程操作的都是同一个student对象
 * 如果不使用valatile:那么，当现线程AAA执行调用run方法，就会盛水3秒之后，将主物理内存中的数据改成21，
 * 但是此时main线程不知道主物理内存中的数据发生了改变，只知道创建student对象初始化时，age的值为0
 * 所以就一直在while处死循环
 *
 * 但是如果使用了volatile关键字，那么只要有一个线程操作修改了主物理内存中的值，
 * 就会通知其他线程朱物理内存中的值已修改，其他的线程就会重新读物主物理内存中的值
 *
 * 保证了数据的可见性
 *
 */
public class VolatileDemo01 {
    public static void main(String[] args) {

        final Student student=new Student();

        //线程AAA
        new Thread("AAA"){
            public void run(){
                System.out.println("线程名称"+Thread.currentThread().getName()+"\t"+"thread come in");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                student.addAge();
                System.out.println("线程名称"+Thread.currentThread().getName()+"\t"+"student ade="+student.age);
            }
        }.start();

        //主线程，此时主线程和线程AA操作的都是同一个student对象
        while(student.age==0){

        }
        System.out.println("线程名称"+Thread.currentThread().getName()+"\t"+"student ade="+student.age);
    }
}


class Student{
       volatile int age;

    public void addAge(){
        age=21;
    }
}

