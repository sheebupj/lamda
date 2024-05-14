package com.paremal.lamda.practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.paremal.lamda.operations.Employee;
import com.paremal.lamda.operations.Transaction;
import com.paremal.lamda.util.Utils;

import static java.util.Map.*;

public class PracticeClass2 {
    static List<Transaction> transactions = Utils.getTransactions();

    public record Customer(String name, String age, String city, Double netWorth) {
    }

    public record Employee(String firstName, String lastName, Double salary, String department, List<String> phoneNo) {
    }

    public static void main(String[] args) {
        final List<String> phones = Arrays.asList(new String[]{"9387690660", "9188584218"});

        List<Customer> customers = List.of(new Customer("zohn", "15", "NewJersy", 1000.0),
                new Customer("aby", "15", "NewYork", 2000.0), new Customer("john3", "15", "NewJersy", 4000.0),
                new Customer("john4", "15", "NewJersy", 5000.0));

        List<Employee> empList = List.of(new Employee("Jason", "Red", 5000.0, "IT", phones),
                new Employee("Ashly", "Green", 7601.0, "IT", phones),
                new Employee("Mathew", "Indigo", 3587.5, "Sales", phones),
                new Employee("James", "Indigo", 7600.0, "Marketing", phones),
                new Employee("Luke", "Indigo", 8200.0, "IT", phones),
                new Employee("Jason", "Blue", 6200.0, "Sales", phones),
                new Employee("Jason", "Blue", 3200.0, "finance", phones),
                new Employee("Wendy", "Brown", 4236.4, "Marketing", phones),
                new Employee("Wendy", "Brown", 6200.0, "Marketing", phones));

        /*
         * List all customers who's city is not equal to Newyork
         */
        List<Customer> filtredCusts = customers.stream().filter(c -> !c.city.equals("NewYork"))
                .collect(Collectors.toList());
        filtredCusts.forEach(System.out::println);

        /*
         * create Map based on city and number of customers with that city
         */
        Map<String, Long> custsMap = customers.stream()
                .collect(Collectors.groupingBy(Customer::city, Collectors.counting()));
        /**
         * Sort Map ByKey
         */
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        Map<String, Long> cityCoutMapbyKey = sortedMapbyKey(custsMap);
        cityCoutMapbyKey.entrySet().forEach(System.out::println);

        /**
         * Sort map by value
         */
        Map<String, Long> cityCoutMapbyValue = sortedMapbyValue(custsMap);
        cityCoutMapbyValue.entrySet().forEach(System.out::println);
        /*
         * sort customerlist based of customer name
         */
        customers.stream().sorted(Comparator.comparing(Customer::name)).forEach(System.out::println);

        /*
         * get all customers above average NetWorth
         */
        Double avg = customers.stream().collect(Collectors.averagingDouble(Customer::netWorth));

        customers.stream().filter(c -> c.netWorth > avg).forEach(System.out::println);

        /*
         * converting list to map
         */
        Map<String, List<Transaction>> currencyTransactions = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));

        /*
         * iterate, filter, and print map values
         */
        currencyTransactions.entrySet().stream().map(m -> m.getValue())
                .forEach(t -> t.stream().filter(t1 -> t1.getYear() == 2012).forEach(System.out::println));

        /*
         * iterate, filter, and print map keys
         */
        currencyTransactions.entrySet().stream().map(m -> m.getKey()).forEach(System.out::println);

        List<Employee> empList1 = empList.stream().collect(Collectors.toList());
        /*
         *
         */
        empList.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors
                        .collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::salary)), Optional::get)))
                .entrySet().forEach(System.out::println);
        ;

        /*
         * print all numbers with second digit is 9 in a list
         */
        int[] numAr = {9, 193, 1347, 647, 198374, 2934};
        Arrays.stream(numAr).filter(PracticeClass2::whetherSecondDigitNineOrNot).forEach(System.out::println);

        empList.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors
                        .collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::salary)), Optional::get)))
                .entrySet().stream().forEach(System.out::println);

        empList.stream().collect(Collectors.groupingBy(Employee::department)).entrySet().stream()
                .forEach(System.out::println);
        Map<String, Long> cCustMap = customers.stream()
                .collect(Collectors.groupingBy(Customer::city, Collectors.counting()));
        cCustMap.entrySet().forEach(System.out::println);
        Map<String, Long> empMap = empList.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.counting()));
        empMap.entrySet().stream().filter(m -> m.getValue() == 2).forEach(System.out::println);
        /**
         * department wise highest salaries Employee
         */
        empList.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors
                        .collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::salary)), Optional::get)))
                .entrySet().stream().forEach(System.out::println);
        /**
         * second-highest salaried employee
         */
        System.out.println("second highest salaried Employee:" +
                empList.stream().sorted(Comparator.comparing(Employee::salary).reversed()).distinct().skip(1).findFirst().get());

        Double secondHighest = empList.stream().distinct().sorted(Comparator.comparing(Employee::salary).reversed()).skip(1).findFirst().get().salary;
        empList.stream().filter(e -> e.salary == secondHighest).forEach(System.out::println);
        /*
          Highest salaried employee in each department
         */
        empList.stream().collect(Collectors.groupingBy(Employee::department,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::salary)),
                        Optional::get))).entrySet().forEach(System.out::println);

        /*
        find first character which have more than one frequency in a string
         */

        String str = "Java coding is Awesome";

        String result = Arrays.stream(str.split(""))
                .map(e -> e.toLowerCase())
                .filter(c -> !c.equals(" "))
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum))
                .entrySet().stream()
                .findFirst()
                .get().getKey();
        System.out.println(result);
        System.out.println(Arrays.stream(str.split(""))
                .map(String::toLowerCase).map(s->s.charAt(0))
                .filter(Character::isAlphabetic)
                .collect(Collectors.toMap(Function.identity(),v->1, Integer::sum))
                .entrySet().stream().filter(e-> e.getValue()>1)
                .findFirst()
                .get());
        System.out.println( empList.stream().sorted(Comparator.comparing(Employee::salary).reversed()).skip(1).findFirst().get());



    }

    public static boolean whetherSecondDigitNineOrNot(Integer n) {
        if (n < 10)
            return false;
        while (n >= 100) {
            n = (n - (n % 10)) / 10;
        }
        n = n % 10;
        if (n == 9)
            return true;
        return false;

    }

    /**
     * sort map by key
     *
     * @param custMap
     * @return
     */
    public static Map<String, Long> sortedMapbyKey(Map<String, Long> custMap) {
        return custMap
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * sort map by value
     *
     * @param custMap
     * @return
     */
    public static Map<String, Long> sortedMapbyValue(Map<String, Long> custMap) {
        return custMap
                .entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
