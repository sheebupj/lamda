package com.paremal.lamda.operations;

import java.util.stream.IntStream;

public class IntStreamOperations {
	
	
	public static void main(String[] args) {
		
		int [] values= {7,9,3,6,4,10,5,2,8,1};
		
		System.out.println("Original values");
		IntStream.of(values).sorted().forEach(value->System.out.printf("%d%n",value));
		
		
		System.out.printf("Count: %d%n",IntStream.of(values).count());
		System.out.printf("sum: %d%n",IntStream.of(values).sum());
		System.out.printf("Minimum: %d%n",IntStream.of(values).min().getAsInt());
		System.out.printf("Maximum: %d%n",IntStream.of(values).max().getAsInt());
		System.out.printf("Average: %.3f%n",IntStream.of(values).average().getAsDouble());
		IntStream.of(values).forEach(value->System.out.printf("%d%n",value));
		
	}

}
