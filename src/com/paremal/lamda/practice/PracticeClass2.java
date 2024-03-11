package com.paremal.lamda.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.paremal.lamda.operations.Employee;
import com.paremal.lamda.operations.Transaction;
import com.paremal.lamda.util.Utils;

public class PracticeClass2 {
	static List<Transaction> transactions= Utils.getTransactions();
	public static void main(String[] args) {
		//converting list to map
		Map<String,List<Transaction>> currencyTransactions=transactions.stream()
				.collect(Collectors.groupingBy(Transaction::getCurrency));
		//currencyTransactions.entrySet().stream().map(m-> m.getValue()).forEach(t->t.forEach(System.out::println));
		
		//iterate, filter, and print map values
		currencyTransactions.entrySet().stream().map(m-> m.getValue()).
		forEach(t->t.stream().filter(t1->t1.getYear()==2012).forEach(System.out::println));
		//iterate, filter, and print map keys
		currencyTransactions.entrySet().stream().map(m->m.getKey()).forEach(System.out::println);
		List<Employee> empList= Arrays.stream(Utils.getEmployees()).collect(Collectors.toList());
		empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.collectingAndThen
				(Collectors.maxBy(Comparator.comparing(Employee::getSalary)), Optional::get)));
		
		/*
		 * print all numbers with second digit is 9 in a list
		 */
		int[] numAr= {9,193,1347,647,198374,2934};
		Arrays.stream(numAr).filter(n->{
			if(n<10)return false;
			while(n>=100) {
				n=(n-(n%10))/10;
			}
			n=n%10;
			if(n==9) return true;
			return false;
			
		}).forEach(System.out::println);
	}

}
