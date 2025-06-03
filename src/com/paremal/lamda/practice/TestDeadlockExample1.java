package com.paremal.lamda.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDeadlockExample1 {
    public static void main(String[] args) {
        String lock1="Lock1";
        String lock2="Lock2";
        Runnable t1= ()->{
                synchronized(lock1){
                    System.out.println("Thread1 Acquired Lock1");
                    synchronized (lock2){
                        System.out.println("Thread1 Acquired Lock2");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Released lock2");
                    }
                    System.out.println("Released lock1");
                }

            };

       Runnable t2= ()->{
                synchronized(lock2){
                    System.out.println("Thread2 Acquired Lock2");
                    synchronized (lock1){
                        System.out.println("Thread2 Acquired Lock1");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Thread2 Released lock1");
                    }
                    System.out.println("Thread2 Released lock2");
                }

            };
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.submit(t1);
        executorService.submit(t2);
    }
}
