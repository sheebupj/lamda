package com.paremal.lamda.practice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        System.out.println("&&&&" + list3.stream().reduce((a, b) -> a + b).get());
        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        List<Integer> listOfAllIntegers = listOfLists.stream().flatMap(x -> x.stream()).collect(Collectors.toList());

        System.out.println(listOfAllIntegers); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        /*
         * Primes between 1 to 100
         */
        IntStream.rangeClosed(1, 100).filter(Pr1::primeOrNot).forEach(System.out::println);

        System.out.println("###");
        IntStream.rangeClosed(1, 100).filter(n -> {
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
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
        String str2 = "removing duplicates from string";
        System.out.println(":::" + Arrays.stream(str2.split("")).distinct().collect(Collectors.joining()));

        /*
         * find Armstrong
         */
        IntStream.rangeClosed(1, 500).boxed().filter(Pr1::amstrongOrNot).forEach(System.out::println);

        /*
         * second largest salary
         */
        List<Employee> emplist = Utils.getEmployeeslist();
        System.out.println("Second lartgest salary:" + emplist.stream().distinct()
                .sorted(Comparator.comparing(Employee::salary).reversed().thenComparing(Employee::firstName).thenComparing(Employee::salary)).skip(1).findFirst());
        //emplist.stream().sorted()
        /*
         * highest salary for each department
         */
        Map<String, Employee> dpmtWisemaxSal = emplist.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Employee::salary)), Optional::get)));
        dpmtWisemaxSal.entrySet().stream().forEach(System.out::println);

        List<Employee> e1 = emplist.stream().filter(e -> e.firstName().startsWith("j")).collect(Collectors.toList());
        System.out.println(e1.size());
        List<String> worlds1 = null;

        try {
            /*
             * split files to word list
             */
            Path path = Paths.get("data.txt");
            words = Files.lines(path, Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());
            worlds1 = Files.lines(path, Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .filter(w -> !w.equals("minima")).collect(Collectors.toList());
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
        String reversedWords1=reverseStringUsingStreamApi(nme);
        System.out.println(reversedWords+" : "+reversedWords1);
        String reverseEachWords=reverseEachStringUsingStreamApi(nme);
        System.out.println(reverseEachWords);


        /*
            create a map from a wordlist with word as key and length as value
            Arrays.asList("ABC", "DDBA", "RADEA", "QQ")
         */

        Optional<List<String>> strs = Optional.of(Optional.ofNullable(List.of("ABC", "DDBA", "RADEA", "QQ")).orElseGet(Collections::emptyList));
        // strs.stream().filter(s-> !s.isEmpty()).collect(Collectors.toMap(s->s,s->s.length())).entrySet().stream().forEach(System.out::println);
        strs.ifPresent(s -> s.stream().collect(Collectors.toMap(key -> key, value -> value.length())).entrySet().stream().forEach(System.out::println));
        char c = 'b';
        String binaryString = Integer.toBinaryString(c);
        System.out.println("1" + binaryString.substring(0, 3) + "  " + binaryString.substring(3));
        System.out.println(binaryString);
        String str1 = "sheebu";
        /*
        removing duplicates ...not recommended
         */
        Map<String, Integer> charMap = Arrays.stream(str1.split("")).collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum, LinkedHashMap::new));
        String unequeString =
                charMap.entrySet().stream().map(es -> es.getKey()).collect(Collectors.joining());
        System.out.println(unequeString);
        /*
        remove duplicate char
         */
        String duplicateRemoved = Arrays.stream(str1.split("")).distinct().collect(Collectors.joining());
        System.out.println(duplicateRemoved);


        int steps = 8;
        String strhike = "UDDDUDUU";
        System.out.println();
        System.out.println(countingValleys(steps, strhike));







        /*
        most repeated number from array with frequency ranking up to kth frequency
         */
        int k = 2;
        int[] intsv = {2, 2, 1, 3, 1, 1};
        Arrays.stream(intsv).boxed()
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (s1, s2) -> s1, LinkedHashMap::new))
                .entrySet().stream().limit(k)
                .forEach(em -> System.out.print(em.getKey() + "-" + em.getValue() + " "));
        System.out.println();
        /*
       combining  first non-Repeatable character from list of words
       */
        List<String> strListr = Arrays.asList("array", "apple", "rat");
        System.out.println(strListr.stream().map(Pr1::getFirstC).collect(Collectors.joining()));
        /*
        combining  first non-Repeatable character from list of words in single stream api method pipeline
         */
        String stringFromNonrepeatables=strListr.stream().map(w->{
            Optional<Map.Entry<String,Integer>> buildStringFistNonRepeatableChar=Arrays.stream(w.split(""))
                    .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum,LinkedHashMap::new))
                    .entrySet().stream().filter(es -> es.getValue() == 1).findFirst();
            return buildStringFistNonRepeatableChar.isPresent() ? buildStringFistNonRepeatableChar.get().getKey():"";
        }).collect(Collectors.joining());
        System.out.println("&&& "+stringFromNonrepeatables);



        String s = "welcome to cforge";
        String[] chars = s.split("");

        Arrays.stream(chars)
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum, LinkedHashMap::new))
                .entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(k1 -> k1.getKey(), v -> v.getValue(),
                        (ee1, ee2) -> ee1, LinkedHashMap::new))
                .entrySet().stream().filter(es -> es.getValue() == 1)
                .skip(2)
                .limit(1)
                .forEach(es -> System.out.println("third non repeatable character in String:" + es.getKey()));

        /*
        generate list of randome integers between 1 and 10
         */
        Random random = new Random();
        var ints = IntStream.generate(() -> random.nextInt(1, 10))
                .boxed()
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("......................................." + ints.size());
        ints.forEach(System.out::println);

        /*
        find longest string that do not contain vowel in the collection  using stream and regex
         */
        String regex = "[aeiouAEIOU]";
        Pattern pattern = Pattern.compile(regex);
        Stream.of("ada",
                        "xyz",
                        "absolute",
                        "arithmetic",
                        "bcdfghjklm")
                .filter(w -> {
                    Matcher matcher = pattern.matcher(w);
                    return !matcher.find();
                })
                .reduce((a, b) -> a.length() > b.length() ? a : b)
                .ifPresent(System.out::println);

         /*
        find longest string that do not contain vowel in the collection using method reference  and stream
         */

        Stream.of("ada",
                "xyz",
                "absolute",
                "arithmetic1",
                "bcdfghjklm",
                "aithmatic-expression")

                .filter(Pr1::containsVowel)
                .reduce((a,b)-> a.length()>b.length() ? a:b)
                .ifPresent(System.out::println);
        /*
        find max length string that do not contain vowel in the collection
         */

        Stream.of("abstract", "devil", "friend", "abcd", "evolution", "fghjklm", "pronouciation", "nmpqrstvwxyz")
                .filter(w -> {
                    String lCase = w.toLowerCase();
                    String[] cAr=lCase.split("");
                    boolean rsl=true;
                    for(String chr:cAr) {
                        if (chr.contains("a")
                                || chr.contains("e")
                                || chr.contains("i")
                                || chr.contains("o")
                                || chr.contains("u")) {
                            rsl= false;
                        }
                    }
                    return rsl;
                }).reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2)
                .ifPresent(System.out::println);


    }

    /*
      method to  check a String contains vowel or not
     */
    static boolean containsVowel(String str) {
        String[] strs = str.split("");
        boolean rslt=true;
        for (String s : strs) {

            if (   s.equals("a") ||
                    s.equals("e") ||
                    s.equals("i") ||
                    s.equals("o") ||
                    s.equals("u")||
                    s.equals("A") ||
                    s.equals("E") ||
                    s.equals("I") ||
                    s.equals("O") ||
                    s.equals("U")) {
                rslt= false;
            }
        }

        return rslt;
    }


    /*
    Method to find first non-Repeatable character from a string
     */
    static String getFirstC(String w) {
        Optional<Map.Entry<String, Integer>> me = Arrays.stream(w.split(""))
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum, LinkedHashMap::new))
                .entrySet().stream()
                .filter(em -> em.getValue() == 1)
                .findFirst();
        return me.isPresent() ? me.get().getKey() : "";
    }

    public static int countingValleys(int steps, String path) {
        // Write your code here
        String[] strArr = path.split("");

        int v = 0, present = 0, previous = 0;
        for (String str : strArr) {
            previous = present;
            present = present + getValue(str);
            v = v + statusChange(present, previous);
            // System.out.print(v+" ");

        }
        return v;

    }

    static int getValue(String c) {
        if (c.equals("U")) return 1;
        if (c.equals("D")) return -1;
        return 0;
    }

    static int statusChange(int present, int previous) {
        if (previous == -1 && present == 0) {
            return 1;
        }
        return 0;


    }

    static int getPairCount(Integer n) {
        return n / 2;
    }

    /*
     reverse every word in a String without using built in reverse method
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
    /*
    reverse  String   without using built in reverse method(using stream api method)
    */
    public static String reverseStringUsingStreamApi(String str) {
        String[] chars = str.split("");
        int len=chars.length;
        return   IntStream.iterate(len-1,i-> i>=0,i-> i=i-1)
                .boxed()
                .map(str::charAt)
                .map(c-> String.valueOf(c))
                .collect(Collectors.joining());
   }

    /*
    reverse each words in a wordlist    without using built in reverse method(using stream api method)
    */
    public static String reverseEachStringUsingStreamApi(String str) {
        String[] words = str.split(" ");
        int len = words.length;
        return IntStream.iterate(0, j -> j < len, j -> j = j + 1)
                .boxed()
                .map(j -> words[j])
                .map(w -> {
                    String[] wrd = w.split("");
                    int l = wrd.length;
                    return IntStream.iterate(l - 1, i -> i >= 0, i -> i = i - 1)
                            .boxed()
                            .map(w::charAt)
                            .map(c -> String.valueOf(c))
                            .collect(Collectors.joining());
                })
                .map(w-> w+" ")
                .collect(Collectors.joining());
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
