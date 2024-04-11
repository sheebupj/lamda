package com.paremal.lamda.practice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.paremal.lamda.util.Utils.Employee;
import com.paremal.lamda.util.Utils;

public class Pr1 {

    static public void main(String[] args) {
        List<Integer> n1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> n2 = Arrays.asList(3, 4, 5);
        /* skiping elements */
        n1.stream().skip(1).forEach(System.out::println);
        /* sorting */
        n1.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        /* combinations */
        List<int[]> combinations = n1.stream()
                .flatMap(i -> n2.stream().filter(j -> (i + j) % 2 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        for (int[] i : combinations) {
            System.out.println(i[0] + "," + i[1]);
        }
        List<String> words = Arrays.asList("Hello", "world", "warm", "where", "weard", "hero", "world", "hectic", "horific");
        System.out.println("------");
        words.stream().filter(w -> w.startsWith("h")).forEach(System.out::println);
        System.out.println("------");

        /* sorting */
        words.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        /* avoiding duplicate charachors */
        List<String> uChars = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct()
                .collect(Collectors.toList());
        uChars.forEach(System.out::println);
        /* sum using reduce */
        Optional<Integer> sum = n1.stream().reduce(Integer::sum);

        int sum1 = n1.stream().reduce(0, (x, y) -> x + y);

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        List<Integer> listOfAllIntegers = listOfLists.stream().flatMap(x -> x.stream()).collect(Collectors.toList());

        System.out.println(listOfAllIntegers); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        /*
         * Primes between 1 to 100
         */
        IntStream.rangeClosed(1, 100).filter(Pr1::primeOrNot).forEach(System.out::println);

        /*
         * find number of special characters
         */
        String str = "This#string%contains^spe cial*characters&.(-_  ";
        System.out.println(":" + Arrays.stream(str.split("")).map(s -> s.charAt(0))
                .filter(c -> !(Character.isAlphabetic(c) || Character.isDigit(c))).count());

        /*
         * find Armstrong
         */
        IntStream.rangeClosed(1, 500).boxed().filter(Pr1::amstrongOrNot).forEach(System.out::println);

        /*
         * second largest salary
         */
        List<Employee> emplist = Utils.getEmployeeslist();
        System.out.println("Second lartgest salary:" + emplist.stream().distinct()
                .sorted(Comparator.comparing(Employee::salary).reversed()).skip(1).findFirst());

        /*
         * highest salary for each department
         */
        Map<String, Object> dpmtWisemaxSal = emplist.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Employee::salary)), Optional::get)));
        dpmtWisemaxSal.entrySet().stream().forEach(System.out::println);

        List<Employee> e1 = emplist.stream().filter(e -> e.firstName().startsWith("j")).collect(Collectors.toList());
        System.out.println(e1.size());


        try {
            /*
             * split files to word list
             */
            words = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Map<String, Integer> wl = words.stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
        wl.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        System.out.println("$$$**********************************************************************");
        wl.entrySet().forEach(System.out::println);

    }

    public static boolean amstrongOrNot(Integer n) {
        int tmp = n;
        int qubesum = 0;
        int r;
        while (n >= 10) {
            r = n % 10;
            qubesum += r * r * r;
            n = (n - r) / 10;
        }
        qubesum += n * n * n;
        return tmp == qubesum;
    }

    public static boolean primeOrNot(Integer n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

}
