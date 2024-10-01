package com.paremal.lamda.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionalProgs {
    public static void main(String[] args) {

        String sr1="Hello";
        String sr4="Hello";
        String sr5=new String("Hello");


        System.out.println(sr1==sr5);
        System.out.println(sr4.equals(sr5));
        String sheebu="sheebu";
        String uniqueString=Arrays.stream(sheebu.split("")).distinct().collect(Collectors.joining());
        System.out.println(uniqueString);
        Stream<Integer> s = Stream.iterate(1, n -> n + 3);
        s.skip(10)
                .limit(2)
                .forEach(System.out::println);

//        Stream.generate(() -> "Elsa")
//                .filter(n -> n.length() == 4)
//                .limit(2)
//                .sorted()
//                .forEach(System.out::println);
        Stream.generate(() -> "Olaf Lazisson")

                .filter(n -> n.length() > 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);
        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::println);
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map = ohMy.collect(
                Collectors.partitioningBy(str -> str.length() <= 5));
        System.out.println(map);    // {false=[tigers], true=[lions, bears]}

        var ohMy1 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map1 = ohMy1.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                str1 -> str1.charAt(0),
                                Collectors.minBy((a, b) -> a -b))));
        System.out.println(map1);    // {5=Optional[b], 6=Optional[t]}
        Consumer<String> c1 = x -> System.out.print("1: " + x);
        Consumer<String> c2 = x -> System.out.println(",2: " + x);

        Consumer<String> combined = c1.andThen(c2);
        combined.accept("Annie");
        Function<Integer, Integer> before = x -> x + 1;
        Function<Integer, Integer> after = x -> x * 2;

        Function<Integer, Integer> combined1 = after.compose(before);
        System.out.println(combined1.apply(3));

        Function<Integer,Integer> twoTimes= x-> x+x;
        Function<Integer,Integer> squire= x->x*x;

        //compose
        Function<Integer,Integer> combine= squire.compose(twoTimes);
        System.out.println(combine.apply(4));//64

        Function<Integer,Integer> combine2=twoTimes.compose(squire);
        System.out.println(combine2.apply(4));//32
        //andThen
        Function<Integer,Integer> combine3= squire.andThen(twoTimes);
        System.out.println(combine3.apply(4));//32

        Function<Integer,Integer> combine4= twoTimes.andThen(squire);
        System.out.println(combine4.apply(4));//64




        Optional<Double> opt = average(new int[]{90, 100});
        if (opt.isPresent())
            System.out.println(opt.get()); // 95.0
        opt.ifPresent(System.out::println);


        System.out.println(opt.orElse(Double.NaN));
        System.out.println(opt.orElseGet(() -> Math.random()));

        // System.out.println(opt.orElseThrow());
        //  System.out.println(opt.orElseThrow(
        //       () -> new IllegalStateException()));

        //Creating Finite Streams

        Stream<String> empty = Stream.empty();          // count = 0
        Stream<Integer> singleElement = Stream.of(1);   // count = 1
        Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3

        var list = List.of("a", "b", "c");

        Stream<String> fromList = list.stream();
        fromList.forEach(System.out::print);
        // Creating Parallel stream
        //var list1 = List.of("a", "b", "c");
        Stream<String> fromListParallel = list.parallelStream();
        System.out.println();
        fromListParallel.forEach(System.out::println);//order cant predicted

        //Creating Infinite Streams
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        //Java 9 introduced an overloaded version of iterate()

        Stream<Integer> oddNumberUnder100 = Stream.iterate(
                1,                // seed
                n -> n < 100,     // Predicate to specify when done
                n -> n + 2);      // UnaryOperator to get next value
        oddNumberUnder100.forEach(System.out::println);

        System.out.println("ended");



    }
    public static Optional<Double> average(int[] scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score : scores) sum += score;
        //return Optional.of((double) sum / scores.length);
        return Optional.empty();
    }
}




