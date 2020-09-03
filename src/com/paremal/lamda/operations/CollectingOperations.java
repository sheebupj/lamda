package com.paremal.lamda.operations;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.paremal.lamda.util.Utils;

public class CollectingOperations {
	
	static List<Transaction> transactions= Utils.getTransactions();
	static List<Transaction> nulltrans= Utils.getnullTransactions();
	
	public static void main(String[] args) {
		
		
		
		/*
		 * grouping operations with  Collectors
		 */
		Map<String,List<Transaction>> transactionsByCurrencies=transactions.stream()
				.collect(Collectors.groupingBy(Transaction::getCurrency));
		
		/*
		 * counting using Stream.cout()
		 */
		
		Long numberOfTransactions=transactions.stream().count();
		
		/*
		 * counting using collect and Collectors
		 */
		
		 numberOfTransactions=transactions.stream().collect(Collectors.counting());
		 
		/*
		 * find max valued transactions using comparator and collect
		 */
		 
		 Optional<Transaction> higehestTransactions=transactions.stream().collect(Collectors.maxBy(Comparator.comparing(Transaction::getValue)));
	
		 System.out.println("Transtions with highest amount is:"+higehestTransactions.get());
		 
		 /*
		  * find sum using Collectors and summingInt method
		  */
		 System.out.println(transactions.stream().collect(Collectors.summingInt(Transaction::getValue)));
	}

}
