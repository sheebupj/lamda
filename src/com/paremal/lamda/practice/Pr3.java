package com.paremal.lamda.practice;



import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static java.lang.Math.abs;


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
        Optional<List<String>> opString=Optional.of(Optional.ofNullable(List.of("sheebu","sheenoj","sheena")).orElseGet(Collections::emptyList));
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
        Optional<String> opStr=Optional.empty();
        //Optional<String> opStr= Optional.ofNullable(str);
        //opStr.map(s->s.toUpperCase()).ifPresent(System.out::println);
       // opStr.ifPresent(System.out::println);
        System.out.println(opStr.orElse("empty1"));
        opStr.ifPresent(System.out::println);
        int[] data = {2, 3, -2, 4, -5};
        int[] result=findClosestToZero(data);
        System.out.println("Closest number to zero: " +result[0]+ " :" +result[1]);


    }
   static Optional<String> sup() {
       return Optional.ofNullable(null);
   }

    /*
    An Array of integers is given, both +ve and -ve.
    To find the two elements such that their
     sum is closest to zero.
     */
    public static int[] findClosestToZero(int[] numbers) {
        int[] cz= new int[2];
        cz[0]=abs(numbers[0]);
        cz[1]=abs(numbers[1]);
        int cn=cz[0]+cz[1];

        for(int i=0;i<numbers.length;i++){
            for(int j=i+1;j<numbers.length;j++){
                //math.abs() is used to get closest to 0 since we are checking lowest no  which include negetives
                if(cn>abs(numbers[i])+abs(numbers[j])){
                    cz[0]=numbers[i];
                    cz[1]=numbers[j];
                    cn=abs(cz[0])+abs(cz[1]);
                }
            }

        }


        return cz;
    }

}
