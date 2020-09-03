package com.paremal.lamda.operations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.paremal.lamda.util.Utils;

public class CollectingOperations {
	
	static List<Transaction> transactions= Utils.getTransactions();
	
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
		
	}

}
