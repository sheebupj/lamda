package com.paremal.lamda.operations;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.paremal.lamda.util.Utils;

public class PrimitivStreamOperations {
	static List<Transaction> transactions = Utils.getTransactions();

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
		/* optimal code for Pythagorean triple */
		IntStream.rangeClosed(1, UPPER_LMIT).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, UPPER_LMIT)
						.mapToObj(b -> new double[] { a, b, Math.sqrt(a * a + b * b) }).filter(b -> b[2] % 1 == 0))
				.limit(NUMBER_OF_ITEM_TO_DISPLAY)
				.forEach(b -> System.out.println("###" + ", " + b[0] + ", " + b[1] + ", " + b[2]));
		long t4 = System.nanoTime();
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
		System.out.println("Sum of values is:" + Arrays.stream(values).max());
		long uniqueWordCount = 0;
		/*
		 * splitting text file to words and count unique words
		 */
		long wordCount = 0;
		List<String> words = null;
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
		System.out.println(" ####" + words.stream().distinct().count() + "  " + words.stream().count());
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

		Map<String, Integer> frequencies = words.stream()
				.collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
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
		 * convert list to map by counting each words frequency sort by value
		 */

		System.out.println("$$$$ word count");
		words.stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum)).entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.forEach(e -> System.out.println("word: " + e.getKey() + " " + e.getValue() + "times"));
		
		Map<String,Integer> wl=words.stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum,LinkedHashMap::new)).entrySet().stream()
		.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)-> e1,LinkedHashMap::new));
		System.out.println("$$$**********************************************************************");
		wl.entrySet().forEach(System.out::println);
		System.out.println("$$$**********************************************************************end");
		/*
		 * convert list to map by counting each char frequency sort by key
		 */

		System.out.println("$$$$charactor count sort by key");
		words.stream().map(w -> w.split("")).flatMap(Arrays::stream)
				.collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum)).entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.forEach(entry -> System.out.println("char: " + entry.getKey() + " " + entry.getValue() + " times"));

		/*
		 * convert list to map by counting each char frequency sort by value
		 */

		System.out.println("$$$$charactor count sort by value");
		words.stream().map(w -> w.split("")).flatMap(Arrays::stream)
				.collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum)).entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.forEach(entry -> System.out.println("char: " + entry.getKey() + " " + entry.getValue() + " times"));

		
		long count = words.stream().map(w -> w.split("")).flatMap(Arrays::stream)
				.filter(PrimitivStreamOperations::checkspecialChar).count();
		System.out.println("!!! spcial character count in the text is:" + count);
		List<String>wlist= new ArrayList<>();
		
		wlist.add("this");
		wlist.add("is");
		wlist.add("test");
		
		long vowelCount=wlist.stream()
				.map(w-> w.split(""))
				.flatMap(Arrays::stream)
				
				.filter(PrimitivStreamOperations::isVowel)
				.count();
		System.out.println("Vowel cout:"+ vowelCount);
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
		System.out.println("********************");
		Stream.iterate(new int[] { 0, 1 }, t1 -> new int[] { t1[1], t1[0] + t1[1] }).limit(10).map(t1 -> t1[0])
				.forEach(System.out::println);
		
		/*
		 * counting special characters in a word list
		 */
		String str = "This#string%contains^special*characters&.(-_ ";
		System.out.println("!!!" + Arrays.stream(str.split(""))
						.map(s->s.charAt(0))
						.filter(c-> !Character.isAlphabetic(c)&& !Character.isDigit(c)).collect(Collectors.counting()));

		
		/*
		 * printing all primes between 1 to 100
		 */

		IntStream.rangeClosed(1, 100).mapToObj(i -> new Object[] { i, PrimitivStreamOperations.checkPrime(i) })
				.filter(f -> (boolean) f[1]).skip(1).forEach(n -> System.out.println(n[0]+" is a Prime"));
		/*
		 * stream operations with reduce sum min max
		 */
		int[] nums= {5,7,3,8,2,1,4,6};
		System.out.println("sum of list"+Arrays.stream(nums).reduce(Integer::sum).getAsInt());
		System.out.println("min of list"+Arrays.stream(nums).reduce((a,b)-> a<b ? a:b).getAsInt());
		System.out.println("max of list"+Arrays.stream(nums).boxed().reduce((a,b)-> a>b ? a:b).get());
		
		
		/*
		 * printing all primes between 1 to 49
		 */
		IntStream.range(2, 50).filter(n->{
			for(int i=2;i*i<=n;i++) {
				if(n%i==0) return false;
				}return true;
			}).forEach(n->System.out.println(n+"  is prime"));
		
		/*
		 * finding primes with complete lamda
		 */
		IntStream.range(2, 50).filter(n->
			  IntStream.iterate(2, i-> i+1).takeWhile(i-> i*i<=n).noneMatch(i-> n%i==0)
			).forEach(n->System.out.println(n+"  is prime --find using lamda"));
		
		
		/*
		 * print all numbers starting with 1 from a an array
		 */
		int[] ns= { 17,257,1078,892638,19,92328332,123456778};
		Arrays.stream(ns).boxed().filter(i-> {
			
			while(i>=10) {
				i=(i-(i%10))/10;
			}
			if(i==1) {
				return true;
			}
			return false;
			
		}).forEach(System.out::println);
		
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^print all numbers  with second n=9 from a an array");
		IntStream.rangeClosed(1, 10000).boxed().filter(PrimitivStreamOperations::checkAmstrongOrNot).forEach(System.out::println);
		IntStream.rangeClosed(1, 10000).boxed().filter(n->{
			int tmpNo=n;int cubeSum=0; int r=0;
			while(n>=10) {
				r=n%10;
				cubeSum+= r*r*r;
				n=(n-r)/10;
			}
			cubeSum+= n*n*n;
			if(tmpNo==cubeSum) {
				return true;
			}
			else {
				return false;
			}
		}).forEach(System.out::println);



		Random r= new Random();
		long tt2 = System.nanoTime();
		IntStream ints=IntStream.generate(()-> r.nextInt(100));
		int [] intsv=ints.limit(50000).toArray();
		int x=6;

		/*
		print highest repeated mumbers in array up to xth position
		 */
		Arrays.stream(intsv).boxed()
				.collect(Collectors.toMap(Function.identity(),v->1,Integer::sum))
				.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)-> e1,LinkedHashMap::new))
				.entrySet().stream().limit(x).forEach(em-> System.out.print(em.getKey()+"-"+em.getValue()+" "));
		long tt3=System.nanoTime();
		System.out.println();
		System.out.println("milli Seconds:"+(tt3-tt2)/1000000);
        /*
        { "array", "apple", "rat"}
     */
		List<String> strListr= Arrays.asList( "array", "apple", "rat");
		System.out.println(strListr.stream().map(PrimitivStreamOperations::getFirstC).collect(Collectors.joining()));





	}
	static String getFirstC(String w){
		return Arrays.stream(w.split(""))
				.collect(Collectors.toMap(Function.identity(), v-> 1,Integer::sum, LinkedHashMap::new))
				.entrySet().stream()
				.filter(em-> em.getValue()==1)
				.findFirst()
				.get()
				.getKey();
	}
	public static boolean checkAmstrongOrNot(int n) {
		int tmp=n, cubeSum=0, r=0;
		while(n>=10) {
			r=n%10;
			cubeSum+=r*r*r;
			n=(n-r)/10;
		}
		cubeSum+=n*n*n;
		if(tmp==cubeSum) {
			return true;
		}else {
			return false;
		}
	}
	

	public static boolean checkPrime(Integer i) {
		boolean flag = true;
		for (int j = 2; j * j <= i; j++) {
			if (i % j == 0)
				flag = false;
		}
		return flag;
	}

	public static boolean checkspecialChar(String s) {
		boolean result = true;
		Character c = null;
		if (s.length() < 1)
			result = false;
		else {
			c = s.charAt(0);

			if (Character.isAlphabetic(c) || Character.isDigit(c))
				result = false;

		}
		return result;
	}
	/*
	 * method for getting first digit of a number
	 */
	
	public static int getLastNo(int i) {
		
		while(i>=10) {
			i=(i-(i%10))/10;
		}
		return i;
	}
	public static boolean isVowel(String s) {
		char c=' ';
		if(s.length()>0) {
			c=s.charAt(0);
		}


		if(c=='a'||c=='A'|| c=='e'|| c=='E'|| c=='i'|| c=='I'|| c=='o'|| c=='O'|| c=='u'|| c=='U'  ){
				return true;
	}
	return false;

}
}