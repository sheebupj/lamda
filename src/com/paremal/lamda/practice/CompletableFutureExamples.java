package com.paremal.lamda.practice;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureExamples {
    public static void main(String[] args) {

        CompletableFuture.runAsync(() -> System.out.print("Hello")).thenRun(() -> System.out.println(" World"));
        CompletableFuture.supplyAsync(() -> 30).thenApply(x -> x * 10).thenAccept(System.out::println);
        Random random = new Random();
        CompletableFuture.supplyAsync(() -> IntStream.generate(() -> random.nextInt(10, 100)).boxed().limit(10).collect(Collectors.toList())).thenApply(n -> n.stream().map(x -> x * 10).collect(Collectors.toList())).thenAccept(x -> x.forEach(System.out::println));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> "Hello ");
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> "World");
        hello.thenCombine(world, (h, w) -> h + w).thenAccept(System.out::println);
        Runnable runnable = () -> {
            IntStream.iterate(1, i -> i <= 10, i -> i + 1).forEach(i -> {
                System.out.println(i);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        };
        Runnable runnable2 = () -> {
            IntStream.iterate(100, i -> i <= 1000, i -> i + 100).forEach(i -> {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        };
        CompletableFuture.runAsync(runnable).thenRun(runnable2);


          //  Thread.sleep(2500);


        CompletableFuture.runAsync(runnable).thenRun(runnable2);
        try {

            Thread.sleep(5000);
            System.out.println("5000#");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
