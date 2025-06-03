package com.paremal.lamda.practice;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProducerConsumerRunner {
    public static void main(String[] args) {


        final ProducerConsumer producerConsumer = new ProducerConsumer();
        Runnable produce = () -> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        Runnable consumer = () -> {
            try {
                producerConsumer.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(produce);
        ex.submit(consumer);


    }


}


 class ProducerConsumer {
    LinkedList<Integer> llist = new LinkedList<>();
    int capacity = 2;


    public void produce() throws InterruptedException {
        Random rand= new Random();
       int val;
        while (true) {
            synchronized (this) {
                while (llist.size() ==capacity)
                    wait();
                val=rand.nextInt(1,100);
                llist.add(val);
                System.out.println("value :"+val+"produced in the queue");
                notify();

                Thread.sleep(1000);
            }
        }

    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (llist.size() == 0) {
                    wait();
                }
                int val = llist.removeFirst();
                System.out.println("Consumed: " + val);
                notify();
                Thread.sleep(1000);

            }
        }

    }
}
