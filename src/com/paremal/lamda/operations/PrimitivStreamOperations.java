package com.paremal.lamda.operations;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.paremal.lamda.util.Utils;

public class PrimitivStreamOperations {
	static List<Transaction> transactions= Utils.getTransactions();
	
	public static void main(String[] args) {

		long t = System.nanoTime();

		/*
		 * convert Integer stream to intStram only primitive stream define sum method
		 */
		System.out.println("sum of transaction values" + transactions.stream().mapToInt(Transaction::getValue).sum());

		/*
		 * generating a range of values using intStream 1-100 even numbers
		 */
		System.out.println("1-100 even numbers");
		IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).forEach(n -> System.out.printf("%d ", n));

		/**
		 * Pythagorean triple
		 */
		System.out.println("\nPythagorean triple numbers ");

		int UPPER_LMIT = 200;
		long NUMBER_OF_ITEM_TO_DISPLAY = 150;
		long t2 = System.nanoTime();
		Stream<int[]> pythagoreanTriple = IntStream.rangeClosed(1, UPPER_LMIT).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, UPPER_LMIT).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
						.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));
		

		pythagoreanTriple.limit(NUMBER_OF_ITEM_TO_DISPLAY)
				.forEach(n -> System.out.println(n[0] + " " + n[1] + " " + n[2]));
		long t3 = System.nanoTime();

		System.out.println("time taken" + (t3 - t2));
		/* optimal code for Pythagorean triple*/
		IntStream.rangeClosed(1, UPPER_LMIT).boxed()
								.flatMap(a->IntStream.rangeClosed(a, UPPER_LMIT)
								.mapToObj(b-> new double[] {a,b,Math.sqrt(a*a+b*b)})
								.filter(b-> b[2]%1==0)).limit(NUMBER_OF_ITEM_TO_DISPLAY)
								.forEach(b-> System.out.println("###"+", "+b[0]+", "+b[1]+", "+b[2]));
		long t4=System.nanoTime();
		System.out.println("time taken" + (t4 - t3));

		/*
		 * Static method of Stream.of() is used to create stream from values
		 */

		Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "springBoot ", "Microservice");
		stream.map(String::toUpperCase).forEach(System.out::println);

		/*
		 * Arrays.stream() method used to create stream from array
		 */
		int[] values = { 11, 7, 9, 3, 6, 4, 10, 5, 2, 8, 1 };
		System.out.println("Sum of values is:" + Arrays.stream(values).sum());
		long uniqueWordCount = 0;
		/*
		 * splitting text file to words and count unique words
		 */
		long wordCount = 0;
		List<String> words=null;
		try {
			/*
			 * split files to word list
			 */
			 words = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
					.flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			/*
			 * convert word list to unique word list
			 */
			System.out.println(" ####"+ words.stream().distinct().count()+"  "+ words.stream().count());
			List<String> uniqueWords = words.stream().distinct().collect(Collectors.toList());
			uniqueWordCount = uniqueWords.stream().count();
			System.out.println("#Count of words");
			wordCount = words.stream().count();
			/*
			 * printing all words
			 */
			System.out.println("printing all words");
			words.stream().forEach(w -> System.out.printf("%s ", w));
			
			/*
			 * finding frequencies of words using Map
			 */
			
			Map<String,Integer> frequencies=words.stream().collect(Collectors.toMap(Function.identity(), v->1, Integer::sum));
			System.out.println();
			System.out.println("$$$iterating through map and printing frequencies");
			frequencies.entrySet().stream().forEach(System.out::println);
			
			/*
			 * sort map using key
			 */
			System.out.println();
			System.out.println("$$$iterating through map and printing frequencies sort by key");
			frequencies.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
			/*
			 * sort map using value
			 */
			
			System.out.println("$$$iterating through map and printing frequencies sort by value");
			frequencies.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
			
			/*
			 * 
			 */
			
		

		/*
		 * infinite stream using Stream.iterate() even numbers using infinite integer
		 * stream
		 */
		Stream.iterate(0, n -> n + 2).limit(10).forEach(n -> System.out.println(n));

		/*
		 * Fibonacci tuple
		 */
		Stream.iterate(new int[] { 0, 1 }, t1 -> new int[] { t1[1], t1[0] + t1[1] }).limit(10)
				.forEach(t1 -> System.out.println("(" + t1[0] + "," + t1[1] + ")"));

		/*
		 * Fibonacci
		 */
		Stream.iterate(new int[] { 0, 1 }, t1 -> new int[] { t1[1], t1[0] + t1[1] }).limit(10).map(t1 -> t1[0])
				.forEach(System.out::println);
		
	}

}
