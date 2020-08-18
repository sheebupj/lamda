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

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		/*
		 * Find all transactions in the year 2011 and sort them by value (small to high)
		 * from list<Transactions>
		 */
		System.out.println("Find all transactions in the year 2011 and sort them by value (small to high)");
		transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue))
				.forEach(tr -> System.out.println(tr));

		/*
		 * all the unique cities where the traders work from list<Transactions>
		 */
		System.out.println("\n\nall the unique cities where the traders work");
		transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);

		/*
		 * Find all traders from Cambridge and sort them by name from list<Transactions>
		 */
		System.out.println("\n\nFind all traders from Cambridge and sort them by name");
		transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(t -> t.getTrader().getName())
				.sorted().forEach(System.out::println);

		/*
		 * Return a string of all tradersí names sorted alphabetically.
		 */
		System.out.println("\n\nReturn a string of all tradersí names sorted alphabetically");
		System.out.println(transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("",
				(a, b) -> a.concat(" ").concat(b)));

		/*
		 * find any traders based in Milan
		 */
		System.out.println("\n\nfinding   traders based in Milan");
		transactions.stream().map(t -> t.getTrader()).filter(tr -> tr.getCity().equals("Milan")).distinct()
				.forEach(System.out::println);
		/*
		 * Print all transactionsí values from the traders living in Cambridge
		 */
		System.out.println("\n\nPrinting all transactionsí values from the traders living in Cambridge");
		transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge")).forEach(System.out::println);

	}

}
