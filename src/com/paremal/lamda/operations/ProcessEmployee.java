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
		Employee[] employees = { new Employee("Jason", "Red", 5000, "IT"),
				new Employee("Ashly", "Green", 7600, "IT"),
				new Employee("Mathew", "Indigo", 3587.5, "Sales"),
				new Employee("James", "Indigo", 4700.77, "Marketing"), 
				new Employee("Luke", "Indigo", 6200, "IT"),
				new Employee("Jason", "Blue", 3200, "Sales"),
				new Employee("Jason", "Blue", 3200, "finance"),
				new Employee("Wendy", "Brown", 4236.4, "Marketing") };

		List<Employee> list = Arrays.asList(employees);

		list.stream().forEach(System.out::println);
		Predicate<Employee> fourToSixThousand = e -> (e.getSalary() >= 3000 & e.getSalary() <= 6000);
		System.out.println("Employees with salary 4000 to 6000");

		list.stream().filter(fourToSixThousand).sorted(Comparator.comparing(Employee::getLastName))
				.forEach(System.out::println);
		System.out.printf("highest salary less than 6001 %n%s%n",
				list.stream().filter(fourToSixThousand).findFirst().get());

		Function<Employee, String> byLastName = Employee::getLastName;
		Function<Employee, Double> bySalary = Employee::getSalary;

		Comparator<Employee> lastThenFirstName = Comparator.comparing(byLastName).thenComparing(bySalary);

		System.out.printf("last name then first");
		System.out.println();
		list.stream().sorted(lastThenFirstName).forEach(System.out::println);
		Collections.sort(list, new EmployeeComparator());
		for (Employee e : list) {
			System.out.println(e);
		}
		list.stream().forEach(System.out::println);

		Map<String, List<Employee>> groupedByDepartMent = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		groupedByDepartMent.forEach((department, employeesInDepartment) -> {
			System.out.println(department);
			employeesInDepartment.forEach(employee -> System.out.printf("   %s%n", employee));
		});

		Map<String, Long> employeeCountByDepartMent = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));

		employeeCountByDepartMent
				.forEach((department, count) -> System.out.printf("%s has %d employee(s)%n", department, count));

		System.out.printf("%nSum of the employee salary using sum method %.2f%n",
				list.stream().mapToDouble(Employee::getSalary).sum());

		System.out.printf("%nSum of the employee salary using reduce method %.2f%n",
				list.stream().mapToDouble(Employee::getSalary).reduce(0, (val1, val2) -> val1 + val2));

		System.out.printf("%nSum of the employee salary using reduce method %.2f%n",
				list.stream().mapToDouble(Employee::getSalary).reduce(0, (v1, v2) -> v1 + v2));
		System.out.printf("%nAverage of the employee salary using average method %.2ff%n",
				list.stream().mapToDouble(Employee::getSalary).average().getAsDouble());

		List<String> names = list.stream().map(Employee::getDepartment).collect(Collectors.toList());

		//System.out.printf("%n salary:%f", salaries);
		//salaries.stream().forEach(System.out::println);
		
		list.stream().map(Employee::getSalary).collect(Collectors.toList()).forEach(System.out::println);
		System.out.println("..........");
		names.stream().filter(i -> Collections.frequency(names, i) ==2)
        .collect(Collectors.toSet()).forEach(System.out::println);
		System.out.println("..........");
		list.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList()).forEach(System.out::println);
		
		System.out.println(".........&&&.");
		//list.stream().map(Employee::getFirstName)
		names.stream().filter(n->Collections.frequency(names, n)>1)
		.collect(Collectors.toSet()).forEach(System.out::println);
		
		
		
		list.stream().filter(e-> e.getSalary()>5000).forEach(employee -> System.out.printf("   %s%n", employee));
		
		
		
		names.stream().filter(i->Collections.frequency(names, i)>1).collect(Collectors.toList()).forEach(System.out::println);
			
		
		/*
		 * mapping salaries of all employee objects to list of salaries
		 */
		list.stream().map(Employee::getSalary).collect(Collectors.toList()).forEach(System.out::println);
		
		/*
		 * filtering Employees  with predicate here e-> e.getSalary()>5000)
		 */
		List<Employee> salries=list.stream().filter(e-> e.getSalary()>5000).collect(Collectors.toList());
		
		/*
		 * filtering Employees  with predicate here e-> e.getSalary()>400) chained with skip()
		 */
		list.stream().filter(e-> e.getSalary()>4000).skip(1).forEach(e -> System.out.printf("   %s%n", e));
		
		/*
		 * maping names of employees to list of strings and print them
		 */
		list.stream().map(Employee::getFirstName).forEach(System.out::println);
		
		/*
		 * Map() and flatMap()
		 */
		List<String> words = Arrays.asList("Hello", "World");
		
		/*
		 * following will return Stream<Stream<String>>
		 */
		words.stream().map(word->word.split(""))
		.map(Arrays::stream).distinct().forEach(System.out::println);
		
		/*
		 * following will return distinct stream<characters> 
		 */
		words.stream().map(word->word.split(""))
		.flatMap(Arrays::stream).distinct().forEach(System.out::println);
		
		/*
		 * map integer list to its squires
		 */
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5,9);
		numbers.stream().map(n-> n*n).forEach(System.out::println);
		
		/*
		 * create pairs from two list of integers(combinations)
		 */
		System.out.println("combination of two integer list");
		List<Integer> numbers1 = Arrays.asList(1, 2, 3,5,6);
		List<Integer> numbers2 = Arrays.asList(3, 4,7,9);
		numbers1.stream().flatMap(i->numbers2.stream()
				.map(j-> new int[] {i,j}))
		.forEach(n-> System.out.printf("%d,%d%n", n[0],n[1]));
		
		/*
		 * two list of integers(combinations)  filtering with predicate
		 */
		System.out.println("after filtering the combination sum less than 7");
		numbers1.stream().flatMap(i->numbers2.stream()
				.filter(j-> (i+j)<7).map(j->new int[] {i,j}))
		.forEach(n->System.out.printf("%d,%d%n",n[0],n[1]));
		
	}
}
