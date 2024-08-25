package com.paremal.lamda.util;

import com.paremal.lamda.practice.PracticeClass2;

import java.util.Arrays;
import java.util.List;

public class Utils1 {
    static List<String> phones = Arrays.asList("9387690660", "9188584218");
    public record Customer(String name, String age, String city, Double netWorth) {
    }

    public record Employee(String firstName, String lastName, Double salary, String department, List<String> phoneNo) {
    }
    List<PracticeClass2.Customer> customers = List.of(new PracticeClass2.Customer("zohn", "15", "NewJersy", 1000.0),
            new PracticeClass2.Customer("aby", "15", "NewYork", 2000.0), new PracticeClass2.Customer("john3", "15", "NewJersy", 4000.0),
            new PracticeClass2.Customer("john4", "15", "NewJersy", 5000.0));

    List<PracticeClass2.Employee> empList = List.of(new PracticeClass2.Employee("Jason", "Red", 5000.0, "IT", phones),
            new PracticeClass2.Employee("Ashly", "Green", 7601.0, "IT", phones),
            new PracticeClass2.Employee("Mathew", "Indigo", 3587.5, "Sales", phones),
            new PracticeClass2.Employee("James", "Indigo", 7600.0, "Marketing", phones),
            new PracticeClass2.Employee("Luke", "Indigo", 8200.0, "IT", phones),
            new PracticeClass2.Employee("Jason", "Blue", 6200.0, "Sales", phones),
            new PracticeClass2.Employee("Jason", "Blue", 3200.0, "finance", phones),
            new PracticeClass2.Employee("Wendy", "Brown", 4236.4, "Marketing", phones),
            new PracticeClass2.Employee("Wendy", "Brown", 6200.0, "Marketing", phones));
}
