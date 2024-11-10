package com.paremal.lamda.practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ToMap_GroupedBy {
    public record Employee(Integer id, String Name, Integer age) {  }
    public record Customer(String name, String age, String city, Double netWorth) {    }
    public static void main(String[] args) {

        List<Customer> customers = List.of(
                new Customer("zohn", "25", "NewJersy", 1000.0),
                new Customer("aby", "36", "NewYork", 2000.0),
                new Customer("john3", "45", "NewJersy", 4000.0),
                new Customer("john4", "57", "NewJersy", 5000.0),
                new Customer("joseph", "62", "NewJersy", 1000.0),
                new Customer("george", "33", "NewYork", 2000.0),
                new Customer("sebastian", "22", "NewJersy", 4000.0),
                new Customer("anto", "19", "NewJersy", 5000.0));





        Optional<List<String>> strs = Optional.of(Optional.ofNullable(List.of("ABC", "DDBA", "RADEA", "QQ")).orElseGet(Collections::emptyList));
        strs.ifPresent(s -> s.stream().collect(Collectors.toMap(key -> key, value -> value.length())));
        //Map<String,Integer> mapStringInteger=strs.ifPresent(s -> s.stream().collect(Collectors.toMap(key -> key, value -> value.length())));
        List<Employee> empList= new ArrayList<>();
        empList.add(new Employee(1, "emp1", 22));
        empList.add(new Employee(2, "emp2", 28));
        empList.add(new Employee(3, "emp3", 45));
        empList.add(new Employee(4, "emp4", 45));
      Map<Integer,List<Employee>> empMap= empList.stream().collect( Collectors.groupingBy(Employee::age,Collectors.toList()));


        var ohMy1 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map1 = ohMy1.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                str1 -> str1.charAt(0),
                                Collectors.minBy((a, b) -> a -b))));
        System.out.println(map1);    // {5=Optional[b], 6=Optional[t]}

        var ohMy2 = Stream.of("lions", "tigers", "bears");
        String result = ohMy2.collect(Collectors.joining(", "));
        System.out.println(result); // lions, tigers, bears

        //Collectors.averagingInt()
        var ohMy3 = Stream.of("lions", "tigers", "bears");
        Double result1 = ohMy3.collect(Collectors.averagingInt(String::length));
        System.out.println(result1); // 5.333333333333333

        //Collectors.toMap()...HashMap
        var ohMy4 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map2 = ohMy4.collect(Collectors.toMap(
                String::length,
                k -> k,
                (a1, a2) -> a1 + "," + a2));
        System.out.println(map2);            // {5=lions,bears, 6=tigers}
        System.out.println(map2.getClass()); // class java.util.HashMap

        //Collectors.toMap()...LinkedHashMap
        var ohMy5 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map3 = ohMy5.collect(Collectors.toMap(
                String::length,
                k -> k,
                (a1, a2) -> a1 + "," + a2,
                LinkedHashMap::new));
        System.out.println(map3);            // {5=lions,bears, 6=tigers}
        System.out.println(map3.getClass()); // class java.util.LinkedHashmap

        //Collectors.groupingBy()...TreeMap..Set
        var ohMy6 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, Set<String>> map4 = ohMy6.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toSet()));
        System.out.println(map4); // {5=[lions, bears], 6=[tigers]}

        //Collectors.partitioningBy
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map = ohMy.collect(
                Collectors.partitioningBy(str -> str.length() <= 5));
        System.out.println(map);    // {false=[tigers], true=[lions, bears]}

        /*
        Instead of using the downstream collector to specify the type,
        we can use any of the collectors that we've already shown.
         For example, we can group by the length of the animal name
         to see how many of each length we have.
         */
        var ohMy8 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> map6 = ohMy8.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.counting()));
        System.out.println(map6);    // {5=2, 6=1}


        //Collectors.groupingBy..Collectors.mapping....Collectors.minBy
        var ohMy7 = Stream.of("lions", "tigers", "bears","abcdef");
        Map<Integer, Optional<Character>> map5 = ohMy7.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                cs -> cs.charAt(0),
                                Collectors.minBy((a, b) -> a -b))));
        System.out.println(map5);    // {5=Optional[b], 6=Optional[t]}


        Optional<List<String>> strs1 = Optional.of(Optional.ofNullable(List.of("ABC", "DDBA", "RADEA", "QQ")).orElseGet(Collections::emptyList));
        // strs.stream().filter(s-> !s.isEmpty()).collect(Collectors.toMap(s->s,s->s.length())).entrySet().stream().forEach(System.out::println);
        strs1.ifPresent(s -> s.stream().collect(Collectors.toMap(key -> key, value -> value.length())).entrySet().stream().forEach(System.out::println));

        Random random= new Random();
        IntStream ints=IntStream.generate(()-> random.nextInt(100));
        int [] intsv=ints.limit(50000).toArray();
        int frqRank=5;

        /*
        most repeated number from array with frequency ranking up to position frqRank
         */
        System.out.println("frquency rank");
        Arrays.stream(intsv).boxed()
                .collect(Collectors.toMap(Function.identity(), v->1,Integer::sum))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)-> e1,LinkedHashMap::new))
                .entrySet().stream().limit(frqRank).forEach(em-> System.out.print(em.getKey()+"-"+em.getValue()+" "));
        System.out.println();

        /*
         * create Map based on city and number of customers with that city
         */
        Map<String, Long> custsMap = customers.stream()
                .collect(Collectors.groupingBy(Customer::city, Collectors.counting()));

        Map<String, Long> cityCoutMapbyKey = sortedMapbyKey(custsMap);
        cityCoutMapbyKey.entrySet().forEach(System.out::println);



        /*
       printing first non-Repeatable character from list of words
       */
        List<String> strListr= Arrays.asList( "arrayy", "appleale", "rat");
        System.out.println(strListr.stream().map(ToMap_GroupedBy::getFirstNonRepeatableChar).collect(Collectors.joining()));


    }
    public static Map<String, Long> sortedMapbyKey(Map<String, Long> custMap) {
        return custMap
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /*
    finding first non repeatable character from string
     */
    static String getFirstNonRepeatableChar(String w){
        Optional<Map.Entry<String,Integer>> me= Arrays.stream(w.split(""))
                .collect(Collectors.toMap(Function.identity(), v-> 1,Integer::sum, LinkedHashMap::new))
                .entrySet().stream()
                .filter(em-> em.getValue()==1)
                .findFirst();
        return me.isPresent() ? me.get().getKey():"";
    }

}
