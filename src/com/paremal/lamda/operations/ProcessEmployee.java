package com.paremal.lamda.operations;

import java.io.ObjectInputStream.GetField;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.paremal.lamda.operations.comparator.EmployeeComparator;

public class ProcessEmployee {

	public static void main(String[] args) {
		// initialize array of employee
		Employee[] employees = { new Employee("Jason", "Red", 5000, "IT"), new Employee("Ashly", "Green", 7600, "IT"),
				new Employee("Mathew", "Indigo", 3587.5, "Sales"),
				new Employee("James", "Indigo", 4700.77, "Marketing"), new Employee("Luke", "Indigo", 6200, "IT"),
				new Employee("Jason", "Blue", 3200, "Sales"), new Employee("Jason", "Blue", 3200, "finance"),
				new Employee("Wendy", "Brown", 4236.4, "Marketing") };

		List<Employee> list = Arrays.asList(employees);

		/*
		 * list all employee objects
		 */
		System.out.println("\n\n#All employee objects");
		list.stream().forEach(System.out::println);
		/*
		 * create predicate
		 */
		Predicate<Employee> fourToSixThousand = e -> (e.getSalary() >= 3000 & e.getSalary() <= 6000);
		System.out.println("\n\n#Employees with salary 4000 to 6000");

		/*
		 * filter with predicate
		 */
		list.stream().filter(fourToSixThousand).sorted(Comparator.comparing(Employee::getLastName))
				.forEach(System.out::println);

		System.out.printf("\n\n#highest salary less than 6001 \n%s\n",
				list.stream().filter(fourToSixThousand).findFirst().get());

		/*
		 * Function
		 */
		Function<Employee, String> byLastName = Employee::getLastName;
		Function<Employee, Double> bySalary = Employee::getSalary;

		/*
		 * Comparator with multiple comparison
		 */
		Comparator<Employee> lastThenFirstName = Comparator.comparing(byLastName).thenComparing(bySalary);

		/*
		 * sort by comparator with multiple value comparing
		 */
		System.out.println("\n\n#list of employees sorted first by last name then first name");
		list.stream().sorted(lastThenFirstName).forEach(System.out::println);
		/*
		 * 
		 * Collection.sort()
		 */
		System.out.println("\n\n#list of employees sorted with commparator prior to java 8");
		Collections.sort(list, new EmployeeComparator());
		for (Employee e : list) {
			System.out.println(e);
		}
		/*
		 * going through all item in the list with forEach()
		 */
		System.out.println("\n\n#going through all item in the list with forEach()");
		list.stream().forEach(System.out::println);
		/*
		 * creating TreeMap with departmentname as key and list of employee as value
		 * using Collectores.groupingBY()
		 */
		Map<String, List<Employee>> groupedByDepartMent = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		System.out.println("\n\n#iterating with forEach and collectores.groupedBy  ");
		groupedByDepartMent.forEach((department, employeesInDepartment) -> {
			System.out.println(department);
			employeesInDepartment.forEach(employee -> System.out.printf("   %s%n", employee));
		});

		/*
		 * creating TreeMap with departmentname as key and count as value using
		 * Collectores.groupingBY()
		 */
		Map<String, Long> employeeCountByDepartMent = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));
		System.out.println("\n\n#Iterating with forEach() ");
		employeeCountByDepartMent
				.forEach((department, count) -> System.out.printf("%s has %d employee(s)%n", department, count));

		/*
		 * mapToDouble and sum()
		 */
		System.out.printf("%n%n%n#Sum of the employee salary using sum method %.2f%n",
				list.stream().mapToDouble(Employee::getSalary).sum());

		/*
		 * reduce()
		 */
		System.out.printf("%n%n%n#Sum of the employee salary using reduce method %.2f%n",
				list.stream().mapToDouble(Employee::getSalary).reduce(0, (val1, val2) -> val1 + val2));
		/*
		 * reduce()
		 */

		System.out.printf("%n%n%n#Sum of the employee salary using reduce method %.2f%n",
				list.stream().mapToDouble(Employee::getSalary).reduce(0, (v1, v2) -> v1 + v2));

		/*
		 * average() getAsDouble()
		 */
		System.out.printf("%n%n%n#Average of the employee salary using average method %.2ff%n",
				list.stream().mapToDouble(Employee::getSalary).average().getAsDouble());

		/*
		 * getting department name from employee list
		 */
		List<String> departmentNames = list.stream().map(Employee::getDepartment).collect(Collectors.toList());

		/*
		 * getting distinct department names from employee list using
		 * stream.map().distict()
		 */
		System.out.println("\n\n#Distinct department names");
		list.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList()).forEach(System.out::println);

		/*
		 * getting first names from employee list
		 */
		System.out.println("\n\n#Employee first names");
		list.stream().map(Employee::getFirstName).forEach(n -> System.out.printf("   %s%n", n));

		/*
		 * filtering Employees who have more than 5000 salary
		 */
		System.out.println("\n\n#Employees who have more than 5000 salary");
		list.stream().filter(e -> e.getSalary() > 5000).forEach(employee -> System.out.printf("   %s%n", employee));

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
		list.stream().map(Employee::getSalary).collect(Collectors.toList()).forEach(System.out::println);

		/*
		 * filtering Employees with predicate here e-> e.getSalary()>5000)
		 */
		List<Employee> salries = list.stream().filter(e -> e.getSalary() > 5000).collect(Collectors.toList());

		/*
		 * filtering Employees with predicate here e-> e.getSalary()>400) chained with
		 * skip()
		 */
		System.out.println("\n\n#filtered employee list predicate salary>4000 and skip one");
		list.stream().filter(e -> e.getSalary() > 4000).skip(1).forEach(e -> System.out.printf("   %s%n", e));

		/*
		 * Mapping names of employees to list of strings and print them
		 */
		 
		System.out.println("\n\n#employee names only");

		list.stream().map(Employee::getFirstName).forEach(System.out::println);

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
		List<Integer> numbers1 = Arrays.asList(1, 2, 3, 5, 6,11);
		List<Integer> numbers2 = Arrays.asList(3, 4, 7, 9);
		numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
				.forEach(n -> System.out.printf("%d,%d%n", n[0], n[1]));

		/*
		 * combination of two list of integers, filtering with predicate
		 */
		System.out.println("\n\n#after filtering the combination sum less than 7");
		numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) < 7).map(j -> new int[] { i, j }))
				.forEach(n -> System.out.printf("%d,%d%n", n[0], n[1]));

		/*
		 * combination of two list of integers, filtering with predicate sum%5==5
		 */
		System.out.println("\n\n#after filtering the combination with predicate  sum%5==0");
		List<int[]> pairs8 = numbers1.stream()
				.flatMap(i -> numbers2.stream().filter(j -> (i + j) % 5 == 0).map(j -> new int[] { i, j }))
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
				+ numbers1.stream().reduce( Integer::min));
		
		/*
		 * Maximum using reduce
		 */
		System.out.println("Maximum of  numbers in the list using sum  method in Integer class and reduce:"
				+ numbers1.stream().reduce( Integer::max));
		
		/*
		 * Maximum without using reduce
		 */
		System.out.println("Maximum of  numbers in the list using sum  method in Integer class and without reduce:"
				+ numbers1.stream().reduce(( a,b)-> a>b?a:b));
		/*
		 * Minimum without using reduce
		 */
		System.out.println("Minimum of  numbers in the list using sum  method in Integer class and without reduce:"
				+ numbers1.stream().reduce(( a,b)-> a<b?a:b));
		/*
		 * count of  numbers in the list using map-reduce method
		 */
		System.out.println("count of  numbers in the list using map-reduce method:"
				+ numbers1.stream().map(n-> 1).reduce(( a,b)-> a+b));/*
		 * count of  numbers in the list using count method
		 */
		System.out.println("count of  numbers in the list using count method:"
				+ numbers1.stream().count());
		
		

	}
}
