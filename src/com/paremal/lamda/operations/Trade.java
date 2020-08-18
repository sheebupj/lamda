package com.paremal.lamda.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Trade {

	public static void main(String[] args) {
		
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Trader> traders=new ArrayList<Trader>();
		traders.add(raoul);
		traders.add(mario);
		traders.add(alan);
		traders.add(brian);
		
		List<Transaction> transactions =
				Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), 
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
				);
		
		/*
		 * Find all transactions in the year 2011 and sort them by value (small to high) from list<Transactions>
		 */
		System.out.println("Find all transactions in the year 2011 and sort them by value (small to high)");
		transactions.stream()
		.filter(t-> t.getYear()==2011)
		.sorted(Comparator.comparing(Transaction::getValue))
		.forEach(tr->System.out.println(tr));
		
		/*
		 * all the unique cities where the traders work from list<Transactions>
		 */
		System.out.println("\n\nall the unique cities where the traders work");
		transactions.stream().map(t->t.getTrader().getCity()).distinct().forEach(System.out::println);
		
		
		/*
		 * Find all traders from Cambridge and sort them by name from list<Transactions>
		 */
		System.out.println("\n\nFind all traders from Cambridge and sort them by name");
		transactions.stream()
		.filter(t->t.getTrader().getCity().equals("Cambridge"))
		.map(t-> t.getTrader().getName())
		.sorted()
		.forEach(System.out::println);
	}

}
