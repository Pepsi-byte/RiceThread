package com.ricedemo.atomic;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class User{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

public class AtomicDemo02 {

    public static void main(String[] args) {

        User zs=new User(20,"zhangsan");
        User lisi=new User(21,"lisi");

        //将User变成原子
        AtomicReference<User> userAtomicReference=new AtomicReference<>();
        userAtomicReference.set(zs);


        Boolean result1=userAtomicReference.compareAndSet(zs,lisi);
        Boolean result2=userAtomicReference.compareAndSet(zs,lisi);
        System.out.println(result1+"---"+userAtomicReference.get());
        System.out.println(result2+"---"+userAtomicReference.get());


    }
}
