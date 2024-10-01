package com.paremal.lamda.practice;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

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



        //Terminal operations
        Stream<String> s1 = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(s1.count());   // 3

      //  min() and max()
       // Optional<T> min(Comparator<? super T> comparator);
      //  Optional<T> max(Comparator<? super T> comparator)
        Stream<String> s2 = Stream.of("bonobo","monkey", "ape");
        Optional<String> min = s2.min((s3, s4) -> s3.length()-s4.length());
        min.ifPresent(System.out::println); // ape
        Stream<String> s5 = Stream.of("monkey", "ape", "bonobo");
        Optional<String> max = s5.max((s3, s4) -> s3.compareTo(s4));
        max.ifPresent(System.out::println);


      //  Optional<T> findAny()
      //  Optional<T> findFirst()

        Stream<String> ss = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");

        ss.findAny().ifPresent(System.out::println);        // monkey (usually)
        infinite.findAny().ifPresent(System.out::println); // chimp

      //  allMatch(), anyMatch(), and noneMatch()
      //  boolean anyMatch(Predicate <? super T> predicate)
      //  boolean allMatch(Predicate <? super T> predicate)
       // boolean noneMatch(Predicate <? super T> predicate)
        var list1 = List.of("monkey", "2", "chimp");
        Stream<String> infinite1 = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

        System.out.println(list1.stream().anyMatch(pred));  // true
        System.out.println(list1.stream().allMatch(pred));  // false
        System.out.println(list1.stream().noneMatch(pred)); // false
        System.out.println(infinite1.anyMatch(pred));       // true

       // forEach()
        //void forEach(Consumer<? super T> action)
        Stream<String> sss = Stream.of("Monkey", "Gorilla", "Bonobo");
        sss.forEach(System.out::print); // MonkeyGorillaBonobo

        /*
        reduce()
        The reduce() method combines a stream into a single object. It is a reduction, which means it processes all elements.

                T reduce(T identity, BinaryOperator<T> accumulator)

                Optional<T> reduce(BinaryOperator<T> accumulator)

                <U> U reduce(U identity,
                BiFunction<U,? super T,U> accumulator,
                BinaryOperator<U> combiner)
        */


        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", (x, y) -> x + y);
        System.out.println();
        System.out.println(word); // wolf
    ;
        Stream<String> stream1 = Stream.of("w", "o", "l", "f");
        String word1 = stream1.reduce("", String::concat);
        System.out.println(word1); // wolf

        Stream<Integer> stream3 = Stream.of(3, 5, 6);
        System.out.println(stream3.reduce(1, (a, b) -> a*b));  // 90

        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty1 = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty1.reduce(op).ifPresent(System.out::println);         // no output
        oneElement.reduce(op).ifPresent(System.out::println);    // 3
        threeElements.reduce(op).ifPresent(System.out::println); // 90

        Stream<String> stream4 = Stream.of("w", "o", "l", "f!");
        int length = stream4.reduce(0, (i, str) -> i+str.length(), (a, b) -> a+b);
        System.out.println(length); // 5


        /*  <R> R collect(Supplier<R> supplier,
            BiConsumer<R, ? super T> accumulator,
            BiConsumer<R, R> combiner)

            <R,A> R collect(Collector<? super T, A,R> collector)
         */
        Stream<String> stream2 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set =
                stream2.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set); // [f, l, o, w]

        Stream<String> stream5 = Stream.of("w", "o", "l", "f");
        Set<String> set1 = stream5.collect(Collectors.toSet());
        System.out.println(set); // [f, w, l, o]

        /*
        Common Intermediate Operations
        filter()
        The filter() method returns a Stream with elements that match a given expression
        Stream<T> filter(Predicate<? super T> predicate)
        distinct()
        The distinct() method returns a stream with duplicate values removed.
        Stream<T> distinct()
         */
        Stream<String> s3 = Stream.of("duck", "duck", "duck", "goose");
        s3.distinct() .forEach(System.out::print); // duckgoose
        /*
        limit() and skip()
        The limit() and skip() methods can make a Stream smaller, or they could make a finite stream out of an infinite stream. The method signatures are shown here:
        Stream<T> limit(long maxSize)
        Stream<T> skip(long n)
        */
        System.out.println();
        Stream<Integer> s4 = Stream.iterate(1, n -> n + 1);
        s4.skip(5)
                .limit(2)
                .forEach(System.out::print);//67
        /*
        map()
        The map() method creates a one‐to‐one mapping from the elements in the stream to the elements of the next step in the stream. The method signature is as follows:

        <R> Stream<R> map(Function<? super T, ? extends R> mapper)
         */
        System.out.println();
        Stream<String> s7 = Stream.of("monkey", "gorilla", "bonobo");
        s7.map(String::length)
                .forEach(System.out::print); // 676
        /*
        flatMap()
        The flatMap() method takes each element in the stream and makes any elements it contains top‐level elements in a single stream.
        <R> Stream<R> flatMap(
        Function<? super T, ? extends Stream<? extends R>> mapper)
        */
        System.out.println();
        List<String> zero = List.of();
        var one = List.of("Bonobo");
        var two = List.of("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);

        animals.flatMap(m -> m.stream())
                .forEach(System.out::println);

        /*
        sorted()
        The sorted() method returns a stream with the elements sorted.
        Stream<T> sorted()
        Stream<T> sorted(Comparator<? super T> comparator)
        */
        Stream<String> s8 = Stream.of("brown-", "bear-");
        s8.sorted()
                .forEach(System.out::print); // bear-brown-

        /*
        peek()
        The peek() method is our final intermediate operation. It is useful for debugging because
        it allows us to perform a stream operation without actually changing the stream. The method
        signature is as follows:

        Stream<T> peek(Consumer<? super T> action)
         */
        var stream6 = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream6.filter(s6 -> s6.startsWith("g"))
                .peek(System.out::println).count();              // grizzly
        System.out.println(count);                          // 1

        var list2 = List.of("Toby", "Anna", "Leroy", "Alex");
        list2.stream()
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);

        /*
        IntStream: Used for the primitive types int, short, byte, and char
        LongStream: Used for the primitive type long
        DoubleStream: Used for the primitive types double and float
        */
        DoubleStream empty2 = DoubleStream.empty();
       // Another way is to use the of() factory method from a single value or by using the varargs overload.

        DoubleStream oneValue = DoubleStream.of(3.14);
        oneValue.forEach(System.out::println);

        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        varargs.forEach(System.out::println);

        var random = DoubleStream.generate(Math::random);
        var fractions = DoubleStream.iterate(.5, d -> d / 2);
        random.limit(3).forEach(System.out::println);
        fractions.limit(4).forEach(System.out::println);

        /*
        Using flatMap()
        The flatMap() method exists on primitive streams as well.
        It works the same way as on a regular Stream except the method name is different.
        Here's an example:
        */
        var integerList = new ArrayList<Integer>();
        integerList.addAll(List.of(1,2,3,7,9));
        IntStream ints = integerList.stream()
                .flatMapToInt(x -> IntStream.of(x));
        ints.forEach(System.out::println);

        DoubleStream doubles = integerList.stream()
                .flatMapToDouble(x -> DoubleStream.of(x));
        doubles.forEach(System.out::println);

        LongStream longs = integerList.stream()
                .flatMapToLong(x -> LongStream.of(x));
        longs.forEach(System.out::println);

        //Using Optional with Primitive Streams
        var stream7 = IntStream.rangeClosed(1,10);
        OptionalDouble optional = stream7.average();
        optional.ifPresent(System.out::println);                  // 5.5
        System.out.println(optional.getAsDouble());               // 5.5
        System.out.println(optional.orElseGet(() -> Double.NaN)); // 5.5




















    }
    public static Optional<Double> average(int[] scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score : scores) sum += score;
        //return Optional.of((double) sum / scores.length);
        return Optional.empty();
    }
}




