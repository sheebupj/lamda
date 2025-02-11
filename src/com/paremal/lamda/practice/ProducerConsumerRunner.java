package com.paremal.lamda.practice;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        int value = 0;
        while (true) {
            synchronized (this) {
                while (llist.size() == 2)
                    wait();
                System.out.println("produced" + value);
                llist.add(value++);
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
                System.out.println("Consumed" + val);
                notify();
                Thread.sleep(1000);

            }
        }

    }
}
