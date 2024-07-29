package com.paremal.lamda.practice;

import java.util.Optional;
import java.util.stream.IntStream;

public class Pr3 {

    static boolean primeOrNot(Integer no) {
        for (int i = 2; i * i <= no; i++) {
            if (no % i == 0) {
                return false;
            }
        }
        return true;
    }
    static int factorial(int n){
        int fact=1;
        for(int i=1;i<=n;i++){
            fact*=i;
        }
        return fact;
    }

    static boolean amstrongOrNot(Integer no) {
        int tmp = no, qubeSum = 0, r;
        while (no >= 10) {
            r = no % 10;
            qubeSum = qubeSum + (r * r * r);
            no=(no-r)/10;
        }
        qubeSum=qubeSum+(no*no*no);
        if(tmp==qubeSum) {
            return true;
        }
        return false;
    }
    static String reverseWord(String str){
        StringBuffer reversed= new StringBuffer("");
        int len=str.length();
        for(int i=len-1;i>=0;i--){
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
    static String reverseEachWordsInAstring(String str){
        String[] strArray=str.split(" ");
        StringBuffer sb=new StringBuffer();
        for(String word:strArray){
            for(int i=word.length()-1;i>=0;i--){
                sb.append(word.charAt(i));
            }
            sb.append(" ");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(reverseWord("abc"));
        System.out.println(primeOrNot(11));
        System.out.println("151"+amstrongOrNot(151));
        System.out.println("153"+amstrongOrNot(153));
        System.out.println("370"+amstrongOrNot(370));
        System.out.println("371"+amstrongOrNot(371));
        System.out.println("407"+amstrongOrNot(407));
        System.out.println("11:" +factorial(11));
        System.out.println("3:" +factorial(3));
        System.out.println("4:" +factorial(4));
        System.out.println("5:" +factorial(5));
        System.out.println("6:" +factorial(6));
        System.out.println("7:" +factorial(7));
        System.out.println("8:" +factorial(8));
        System.out.println("9:" +factorial(9));
        System.out.println("10:" +factorial(10));
        System.out.println(reverseWord("1234567890"));
        System.out.println("My name is sheebu:"+reverseEachWordsInAstring("My name is Sheebu"));

    }


}
