package com.ricedemo.volatile2;

public class VolatileDemo03{

    public static volatile VolatileDemo03 instance=null;

    public VolatileDemo03(){
        System.out.println(Thread.currentThread().getName()+"===="+"我是当前线程的构造方法");
    }

    /**
     * synchronized:加上了之后，那么对象在第一个线程争夺了资源之后，
     * 就会去创建一个对象，此时创建对象之后，instance就不会为空，其他线程每一次去调用这个方法的时候
     * 返回的都是之前创建的那个对象
     *
     * 但是使用synchronized：太重，效率多低
     * @return
     */
//    public static synchronized VolatileDemo03 getInstance(){
//        if(instance==null){
//            instance=new VolatileDemo03();
//        }
//        return instance;
//    }


    /**
     * DCL:double click lock:双端检索机制
     *在内部使用synchronized:并且判断两次
     * @return
     */
    public static  VolatileDemo03 getInstance(){
        if(instance==null) {
            synchronized (VolatileDemo03.class) {
                if (instance == null) {
                    instance = new VolatileDemo03();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        //此时在单机的环境下面，对象值创建一次，
//        System.out.println(VolatileDemo03.getInstance()==VolatileDemo03.getInstance());
//        System.out.println(VolatileDemo03.getInstance()==VolatileDemo03.getInstance());
//        System.out.println(VolatileDemo03.getInstance()==VolatileDemo03.getInstance());


        //此时在多线程的环境下面，创建10个线程，但是并不会创建10次对象每一个运行的结果度不一样，
        //线程创建的对象难以得到保证

        //此时给getInstance方法加上重量级的锁synchronized   可以得到保证

        for(int i=1;i<=10;i++) {
            new Thread(() -> {
                VolatileDemo03.getInstance();
            },String.valueOf(i)).start();
        }

    }
}
