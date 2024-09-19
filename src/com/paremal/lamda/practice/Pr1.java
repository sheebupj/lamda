package com.paremal.lamda.practice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.paremal.lamda.util.Utils.Employee;
import com.paremal.lamda.util.Utils;

public class Pr1 {

    static public void main(String[] args) {
        List<Integer> n1 = List.of(1, 2, 3, 4);
        List<Integer> n2 = List.of(3, 4, 5);
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

        System.out.println("###");
        IntStream.rangeClosed(1, 100).filter(n->{
            for(int i=2;i*i<=n;i++){
                 if(n%i==0){
                    return false;
                }
            }
            return true;
        }).forEach(System.out::println);

        /*
         * find number of special characters
         */
        String str = "This#string%contains^special*characters&.(-_";
        System.out.println(str + Arrays.stream(str.split("")).map(s -> s.charAt(0))
                .filter(c -> !(Character.isAlphabetic(c) || Character.isDigit(c))).count());
        /*
        removing duplicates from string
         */
        String str2="removing duplicates from string";
        System.out.println(":::"+Arrays.stream(str2.split("")).distinct().collect(Collectors.joining()));

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
        Map<String, Employee> dpmtWisemaxSal = emplist.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Employee::salary)), Optional::get)));
        dpmtWisemaxSal.entrySet().stream().forEach(System.out::println);

        List<Employee> e1 = emplist.stream().filter(e -> e.firstName().startsWith("j")).collect(Collectors.toList());
        System.out.println(e1.size());
        List<String> worlds1=null;

        try {
            /*
             * split files to word list
             */
            Path path=Paths.get("data.txt");
            words = Files.lines(path, Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());
            worlds1=Files.lines(Paths.get("data.txt"),Charset.defaultCharset()).flatMap(line->Arrays.stream(line.split(" "))).filter(w->!w.equals("minima")).collect(Collectors.toList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Map<String, Integer> wl = worlds1.stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
        wl.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        System.out.println("$$$**********************************************************************");
        wl.entrySet().forEach(System.out::println);
        String nme = "Sheebu Paremal Jayadevan";
        String[] strArr = nme.split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = strArr.length - 1; index >= 0; index--) {
            stringBuilder.append(strArr[index]);
        }
        System.out.println(stringBuilder);
        System.out.println(nme);

        StringBuilder stringBuilder1 = new StringBuilder();
        String reversedWords = reverseWordsFromString(nme);
        System.out.println(reversedWords);


        /*
            create a map from a wordlist with word as key and length as value
            Arrays.asList("ABC", "DDBA", "RADEA", "QQ")
         */

        Optional<List<String>> strs = Optional.of(Optional.ofNullable(List.of("ABC", "DDBA", "RADEA", "QQ")).orElseGet(Collections::emptyList));
        // strs.stream().filter(s-> !s.isEmpty()).collect(Collectors.toMap(s->s,s->s.length())).entrySet().stream().forEach(System.out::println);
        strs.ifPresent(s -> s.stream().collect(Collectors.toMap(key -> key, value -> value.length())).entrySet().stream().forEach(System.out::println));
        char c = 'b';
        String binaryString =Integer.toBinaryString(c);
        System.out.println("1" + binaryString.substring(0, 3) + "  " + binaryString.substring(3));
        System.out.println(binaryString);
        String str1="sheebu";
        Map<String,Integer> charMap=Arrays.stream(str1.split("")).collect(Collectors.toMap(Function.identity(),v->1,Integer::sum));
        /*
        make a string without duplicate char removed (order not kept)
         */
       String unequeString=
                charMap.entrySet().stream().filter(em-> em.getValue()==1).map(em-> em.getKey()).collect(Collectors.joining());
        System.out.println(unequeString);
        /*
        remove duplicate char
         */
        String duplicateRemoved=Arrays.stream(str1.split("")).distinct().collect(Collectors.joining());
        System.out.println(duplicateRemoved);
    }

    /*
     reverse every word in a String
     */
    public static String reverseWordsFromString(String str) {
        String[] words = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            for (int i = word.length() - 1; i >= 0; i--) {
                stringBuilder.append(word.charAt(i));
            }
            stringBuilder.append(" ");

        }
        return stringBuilder.toString();
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

    class myException extends Exception {
        public myException(String message) {
            super(message);
        }
    }

}
