package com.paremal.lamda.operations;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProcessEmployee {

    public record Employee1(String firstName,
                            String lastName,
                            Double salary,
                            String department,
                            List<String> phoneNo) {
    }

    public record Customer(String name, Integer age, String dept, Double sal) {
    }

    public static void main(String[] args) {
        List<String> phones = Arrays.asList("9387690660", "9188584218");
        List<Customer> customers = List.of(
                new Customer("John", 20, "IT", 20000.0),
                new Customer("Joseph", 20, "IT", 20000.0),
                new Customer("Krishna", 20, "IT", 20000.0),
                new Customer("Rma", 20, "IT", 20000.0));

        customers.forEach(System.out::println);

        Customer[] custArray = customers.toArray(Customer[]::new);
        for (Customer c : custArray) {
            System.out.println(c);
        }


        List<Employee1> employeeList = List.of(
                new Employee1("Jason", "Red", 5000.0, "IT", phones),
                new Employee1("Ashly", "Green", 7601.0, "IT", phones),
                new Employee1("Mathew", "Indigo", 3587.5, "Sales", phones),
                new Employee1("James", "Indigo", 7600.0, "Marketing", phones),
                new Employee1("Luke", "Indigo", 8200.0, "IT", phones),
                new Employee1("Jason", "Blue", 6200.0, "Sales", phones),
                new Employee1("Jason", "Blue", 3200.0, "Finance", phones),
                new Employee1("Wendy", "Brown", 4236.4, "Marketing", phones),
                new Employee1("Wendy", "Brown", 6200.0, "Marketing", phones));

        /*
         * list all employee objects
         */
        System.out.println("\n\n#All employee objects");
        employeeList.forEach(System.out::println);
        /*
         * create predicate
         */
        Predicate<Employee1> fourToSixThousand = e -> (e.salary >= 3000 & e.salary <= 6000);
        System.out.println("\n\n#Employees with salary 4000 to 6000");

        /*
         * filter with predicate
         */
        employeeList.stream().filter(fourToSixThousand).sorted(Comparator.comparing(Employee1::lastName).reversed())
                .forEach(System.out::println);

        System.out.printf("\n\n#highest salary less than 6001 \n%s\n",
                employeeList.stream().filter(fourToSixThousand).findFirst().get());

        /*
         * Function
         */
        Function<Employee1, String> byLastName = Employee1::lastName;
        Function<Employee1, Double> bySalary = Employee1::salary;

        /*
         * Comparator with multiple comparison
         */
        Comparator<Employee1> lastThenFirstName = Comparator.comparing(byLastName).thenComparing(bySalary);

        /*
         * sort by comparator with multiple value comparing
         */
        System.out.println("\n\n#list of employees sorted first by last name then first name");
        employeeList.stream().sorted(lastThenFirstName).forEach(System.out::println);
        /*
         *
         * Collection.sort()
         */
        System.out.println("\n\n#list of employees sorted with commparator prior to java 8");
       // employeeList.sort(Comparator.comparing(Employee1::lastName));
        for (Employee1 e : employeeList) {
            System.out.println(e);
        }
        /*
         * going through all item in the list with forEach()
         */
        System.out.println("\n\n#going through all item in the list with forEach()");
        employeeList.forEach(System.out::println);
        /*
         * creating TreeMap with department name as key and list of employee as value
         * using Collectores.groupingBY()
         */
        Map<String, List<Employee1>> groupedByDepartMent = employeeList.stream()
                .collect(Collectors.groupingBy(Employee1::department));
        System.out.println("\n\n#iterating with forEach and collectores.groupedBy  ");
        groupedByDepartMent.forEach((department, employeesInDepartment) -> {
            System.out.println(department);
            employeesInDepartment.forEach(employee -> System.out.printf("   %s%n", employee));
        });
        System.out.println("####################################");
        groupedByDepartMent.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);

        /**
         * creating TreeMap with departmentname as key and count as value using
         * Collectores.groupingBY()
         */
        Map<String, Long> employeeCountByDepartMent = employeeList.stream()
                .collect(Collectors.groupingBy(Employee1::department, TreeMap::new, Collectors.counting()));
        System.out.println("\n\n#Iterating with forEach() ");
        employeeCountByDepartMent
                .forEach((department, count) -> System.out.printf("%s has %d employee(s)%n", department, count));

        /*
         * mapToDouble and sum()
         */
        System.out.printf("%n%n%n#Sum of the employee salary using sum method %.2f%n",
                employeeList.stream().mapToDouble(Employee1::salary).sum());

        /*
         * reduce()
         */
        System.out.printf("%n%n%n#Sum of the employee salary using reduce method %.2f%n",
                employeeList.stream().mapToDouble(Employee1::salary).reduce(0, (val1, val2) -> val1 + val2));
        /*
         * reduce()
         */

        System.out.printf("%n%n%n#Sum of the employee salary using reduce method %.2f%n",
                employeeList.stream().mapToDouble(Employee1::salary).reduce(0, (v1, v2) -> v1 + v2));

        /*
         * average() getAsDouble()
         */
        System.out.printf("%n%n%n#Average of the employee salary using average method %.2ff%n",
                employeeList.stream().mapToDouble(Employee1::salary).average().getAsDouble());

        /*
         * getting department name from employee list
         */
        List<String> departmentNames = employeeList.stream().map(Employee1::department).collect(Collectors.toList());

        /*
         * getting distinct department names from employee list using
         * stream.map().distict()
         */
        System.out.println("\n\n#Distinct department names");
        employeeList.stream().map(Employee1::department).distinct().collect(Collectors.toList())
                .forEach(System.out::println);

        /*
         * getting first names from employee list
         */
        System.out.println("\n\n#Employee first names");
        employeeList.stream().map(Employee1::firstName).forEach(n -> System.out.printf("   %s%n", n));

        /*
         * filtering Employees who have more than 5000 salary
         */
        System.out.println("\n\n#Employees who have more than 5000 salary");
        employeeList.stream().filter(e -> e.salary > 5000)
                .forEach(employee -> System.out.printf("   %s%n", employee));

        /*
         * filtering list for items that have frequency more than one
         */
        System.out.println("\n\n#department names with frequency more than one");
        departmentNames.stream().filter(i -> Collections.frequency(departmentNames, i) > 1).collect(Collectors.toList())
                .forEach(System.out::println);

        /*
         * mapping salaries of all employee objects to list of salaries
         */
        System.out.println("\n\n#Salaries");
        employeeList.stream().map(Employee1::salary).collect(Collectors.toList()).forEach(System.out::println);

        /*
         * filtering Employees with predicate here e-> e.getSalary()>5000)
         */
        List<Employee1> salries = employeeList.stream().filter(e -> e.salary() > 5000).collect(Collectors.toList());

        /*
         * filtering Employees with predicate here e-> e.getSalary()>400) chained with
         * skip()
         */
        System.out.println("\n\n#filtered employee list predicate salary>4000 and skip one");
        employeeList.stream().filter(e -> e.salary() > 4000).skip(1).forEach(e -> System.out.printf("   %s%n", e));

        /*
         * Mapping names of employees to list of strings and print them
         */

        System.out.println("\n\n#employee names only");

        employeeList.stream().map(Employee1::firstName).forEach(System.out::println);

        /*
         * Map() and flatMap()
         */
        List<String> words = Arrays.asList("Hello", "World");

        /*
         * following will return Stream<Stream<String>>
         */
        System.out.println("\n\n# trying to get distinct leters from two words using Map()");
        words.stream().map(word -> word.split("")).map(Arrays::stream).distinct().forEach(System.out::println);

        /*
         * following will return distinct stream<characters>
         */
        System.out.println("\n\n# distinct leters from two words using flatMap()");
        words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

        /*
         * map integer list to its squires
         */
        System.out.println("\n\n# numbers and squares");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 9);
        numbers.stream().map(n -> n * n).forEach(System.out::println);

        /*
         * create pairs from two list of integers(combinations)
         */
        System.out.println("\n\n#combination of two integer list");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 5, 6, 11);
        List<Integer> numbers2 = Arrays.asList(3, 4, 7, 9);
        numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .forEach(n -> System.out.printf("%d,%d%n", n[0], n[1]));

        /*
         * combination of two list of integers, filtering with predicate
         */
        System.out.println("\n\n#after filtering the combination sum less than 7");
        numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) < 7).map(j -> new int[]{i, j}))
                .forEach(n -> System.out.printf("%d,%d%n", n[0], n[1]));

        /*
         * combination of two list of integers, filtering with predicate sum%5==5
         */
        System.out.println("\n\n#after filtering the combination with predicate  sum%5==0");
        List<int[]> pairs8 = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 5 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs8.forEach(n -> System.out.printf("%d,%d%n", n[0], n[1]));

        /*
         * find first with predicate
         */
        System.out.println("first no matching the predicate");
        numbers1.stream().filter(n -> n < 9 && n % 5 == 0).findFirst().ifPresent(System.out::println);

        /*
         * calculate sum of the list with reduce()
         */

        System.out.println("Sum of all numbers in the list:" + numbers1.stream().reduce(0, (a, b) -> a + b));

        /*
         * sum of integer list using sum method in Integer class
         *
         */
        System.out.println("Sum of all numbers in the list using sum method in Integer class:"
                + numbers1.stream().reduce(Integer::sum));
        /*
         * minimum using reduce
         */
        System.out.println("Minimum of  numbers in the list using sum  method in Integer class and reduce:"
                + numbers1.stream().reduce(Integer::min));

        /*
         * Maximum using reduce
         */
        System.out.println("Maximum of  numbers in the list using sum  method in Integer class and reduce:"
                + numbers1.stream().reduce(Integer::max));

        /*
         * Maximum without using reduce
         */
        System.out.println("Maximum of  numbers in the list using using reduce :"
                + numbers1.stream().reduce((a, b) -> a > b ? a : b));
        /*
         * Minimum without using reduce
         */
        System.out.println("Minimum of  numbers in the list using using reduce :"
                + numbers1.stream().reduce((a, b) -> a < b ? a : b));
        /*
         * count of numbers in the list using map-reduce method
         */
        System.out.println("count of  numbers in the list using map-reduce method:" + numbers2.stream().map(n -> 1)
                .reduce((a, b) -> a + b));/*
         * count of numbers in the list using count method
         */

        System.out.println("count of  numbers in the list using count method:" + numbers1.stream().count());

        /*
         * find max and min repeated values in Integer list
         */
        List<Integer> grades = Arrays.asList(1, 4, 6, 7, 8, 8, 9, 5, 7, 8, 4, 3, 8, 2, 1, 6, 8);

        /*
         * converting integer list to map
         */
        Map<Integer, Integer> frequencies = grades.stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));

        /*
         * iterate map and print
         */
        System.out.println("#frequencies map");
        frequencies.entrySet().stream().forEach(System.out::println);

        /*
         * sort map based of key
         */
        System.out.println("#frequencies map sorted based of key");
        frequencies.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        /*
         * split sentence to word array then find frequencies of each word to map then
         * sort map basis of key
         */
        String sentence = "Hi Hello Welcome to Infosys Hi Welcome Again Hi";
        Map<String, Integer> wordcout = Arrays.stream(sentence.split(" "))
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
        wordcout.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        /*
         * split Array of sentences concurrently using executorService, future and
         * Callable in each thread sentence is split into array of word then find
         * frequencies of each word to map then sort map basis of key
         *
         */
        String[] words1 = {"Hi Hello Welcome to Infosys Hi Welcome Again Hi &&&&&&&&&&",
                "A Callable interface defined in java.util.concurrent package &&&&&&&&&&",
                "An object of Callable returns a computed result done by a thread in contrast to a Runnable interface that can only run the thread &&&&&&&&&&",
                "The Callable object returns Future object that provides methods to monitor the progress of a task executed by a thread &&&&&&&&&&",
                "An object of the Future used to check the status of a Callable interface and retrieves the result from Callable once the thread has done &&&&&&&&&&"};
        //List<Future<Map<String, Integer>>> lftr = new ArrayList<>();
        var lftr = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (String w : words1) {
            Callable<Map<String, Integer>> cl = () -> Arrays.stream(w.split(" "))
                    .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
            lftr.add(executor.submit(cl));
        }
        lftr.stream().forEach(m -> {
            try {
                ((Future<Map<String, Integer>>) m).get().entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        /*
         * sort map based of value
         */
        System.out.println("#frequencies map sorted based of value");
        frequencies.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        /*
         * sort (ascending) Map to Set<Map.entry> Object and find first for minimum
         * frequency number
         */
        Optional<Map.Entry<Integer, Integer>> minFrequency = frequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).findFirst();
        System.out.println("Minimum frequency number in list(1,4,6,7,8,8,9,5,7,8,4,3,8,2,1,6,8) is :" + minFrequency);

        /*
         * sort (descending) Map to Set<Map.entry> Object and find first for Maximum
         * frequency number
         */
        Optional<Map.Entry<Integer, Integer>> maxFrequency = frequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).findFirst();
        System.out.println("Maximum frequency number in list(1,4,6,7,8,8,9,5,7,8,4,3,8,2,1,6,8) is :" + maxFrequency);
        // converting array to list java 8
        List<Employee1> emps = employeeList;
        // combine list of strings from each object to single list
        phones = emps.stream().map(e -> e.phoneNo()).flatMap(p -> p.stream())
                .collect(Collectors.toList());

        phones.stream().forEach(System.out::println);

        Map<String, Long> duplicates = emps.stream()
                .collect(Collectors.groupingBy(Employee1::lastName, HashMap::new, Collectors.counting()));
        duplicates.entrySet().stream().filter(e -> e.getValue() > 1)
                .forEach(s -> System.out.println(s.getValue() + "...$" + s.getValue()));
        List<Employee1> filteredEmp = emps.stream().distinct().sorted(Comparator.comparing(Employee1::firstName))
                .filter(e -> e.salary > 4000).collect(Collectors.toList());
        filteredEmp.parallelStream().forEach(e -> System.out.println(e));
        System.out.println("**************************");



        /*
         * second highest salaried emplyees list
         */
        Double secondLargest = employeeList.stream().map(e -> e.salary()).distinct().sorted(Comparator.reverseOrder())
                .skip(1).findFirst().get();
        employeeList.stream().filter(e -> e.salary().equals(secondLargest)).forEach(System.out::println);
        System.out.println("**************************#");
        /*
         * Listing employees
         */
        employeeList.stream().forEach(System.out::println);

        /*
         * finding employee with highest salary in each department
         */
        Map<String, Employee1> emp = employeeList.stream().collect(Collectors.groupingBy(Employee1::department, Collectors
                .collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee1::salary)), Optional::get)));
        System.out.println("**************************#");
        emp.entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + " department Max salary:" + e.getValue().salary()));

        System.out.println(
                "+++" + employeeList.stream().sorted(Comparator.comparing(Employee1::salary).reversed()).findFirst());
        /*
         * finding employee with lowest salary in each department
         */
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee1::department,
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Employee1::salary)),
                                Optional::get)))
                .entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + " department Min salary:" + e.getValue().salary()));

        employeeList.stream()
                .collect(
                        Collectors.groupingBy(Employee1::department, Collectors.averagingDouble(Employee1::salary)))
                .entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + " department Avg salary:" + e.getValue()));

        employeeList.stream()
                .collect(Collectors.groupingBy(Employee1::department, Collectors.summingDouble(Employee1::salary)))
                .entrySet().forEach(e -> System.out.println("dept:" + e.getKey() + "  " + e.getValue()));

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        employeeList.stream()
                .collect(Collectors.groupingBy(Employee1::department,
                        Collectors.summarizingDouble(Employee1::salary)))
                .entrySet().forEach(e -> System.out.println("dept:" + e.getKey() + "  " + e.getValue()));

        // Map<String, Integer> wordcout=Arrays.stream(words.split("
        // ")).collect(Collectors.toMap(Function.identity(),v->1,Integer::sum));
        // wordcout.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
        System.out.println("-------------------------final secton__________________________________________________________");
        Map<String, Employee1> deptWiseHighestSal = employeeList.stream().
                collect(Collectors.groupingBy(Employee1::department,
                        Collectors.collectingAndThen(Collectors.maxBy
                                (Comparator.comparing(Employee1::salary)), Optional::get)));
        deptWiseHighestSal.entrySet().forEach(System.out::println);
    }

}
