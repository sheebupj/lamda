package com.paremal.lamda.util;

import java.util.Arrays;
import java.util.List;

//import com.paremal.lamda.operations.Employee;
import com.paremal.lamda.operations.Trader;
import com.paremal.lamda.operations.Transaction;

public class Utils {
    static List<Transaction> transactions;
    static List<String> phones = Arrays.asList("9387690660", "9188584218");

    public record Employee(String firstName,
                           String lastName,
                           Double salary,
                           String department,
                           List<String> phoneNo){}

    static Employee[] employees = {new Employee("Jason", "Red", 5000.0, "IT", phones),
            new Employee("Ashly", "Green", 7601.0, "IT", phones),
            new Employee("Mathew", "Indigo", 3587.5, "Sales", phones),
            new Employee("James", "Indigo", 7600.0, "Marketing", phones),
            new Employee("Luke", "Indigo", 8200.0, "IT", phones),
            new Employee("Jason", "Blue", 6200.0, "Sales", phones),
            new Employee("Jason", "Blue", 3200.0, "finance", phones),
            new Employee("Wendy", "Brown", 4236.4, "Marketing", phones),
            new Employee("Wendy", "Brown", 6200.0, "Marketing", phones)};

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader Sheenoj = new Trader("Sheenoj", "bangalore");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        Trader sheebu = new Trader("sheebu", "bangalore");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300, "$"), new Transaction(raoul, 2012, 1000, "$"),
                new Transaction(raoul, 2011, 400, "$"), new Transaction(mario, 2012, 710, "$"),
                new Transaction(mario, 2012, 700, "$"), new Transaction(alan, 2012, 950, "$"),
                new Transaction(sheebu, 2022, 1800, "INR"), new Transaction(Sheenoj, 2022, 950, "INR"));

    }

    public static List<Employee> getEmployeeslist() {
        return Arrays.asList(getEmployees());
    }

    public static List<Transaction> getTransactions() {
        return transactions;

    }

    public static List<Transaction> getnullTransactions() {
        return null;

    }

    public static Employee[] getEmployees() {
        return employees;
    }

}
