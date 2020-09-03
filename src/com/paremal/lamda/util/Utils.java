package com.paremal.lamda.util;

import java.util.Arrays;
import java.util.List;

import com.paremal.lamda.operations.Trader;
import com.paremal.lamda.operations.Transaction;

public class Utils {
	static List<Transaction> transactions = null;

	static {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		transactions = Arrays.asList(new Transaction(brian, 2011, 300, "$"), new Transaction(raoul, 2012, 1000, "$"),
				new Transaction(raoul, 2011, 400, "$"), new Transaction(mario, 2012, 710, "$"),
				new Transaction(mario, 2012, 700, "$"), new Transaction(alan, 2012, 950, "$"));
	}

	public static List<Transaction> getTransactions() {
		return transactions;

	}
}
