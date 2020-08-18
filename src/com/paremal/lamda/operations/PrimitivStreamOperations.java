package com.paremal.lamda.operations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PrimitivStreamOperations {
	public static void main(String[] args) {

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		/*
		 * convert Integer stream to intStram only primitive stream define sum method
		 */
		System.out.println("sum of transaction values" + transactions.stream().mapToInt(Transaction::getValue).sum());

		/*
		 * generating a range of values using intStream 1-100 even numbers
		 */
		System.out.println("1-100 even numbers");
		IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).forEach(n-> System.out.printf("%d ", n));

		/**
		 * Pythagorean triple
		 */
		System.out.println("\nPythagorean triple numbers (a,b)<=100");
		
		IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
						.mapToObj(b -> new int[] { a, b,(int) Math.sqrt(a*a + b * b )}))
				.forEach(o -> System.out.println(o[0] + " " + o[1] + " " + o[2]));

	}

}
