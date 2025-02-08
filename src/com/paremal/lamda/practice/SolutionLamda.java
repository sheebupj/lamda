package com.paremal.lamda.practice;
import java.io.*;
import java.util.*;
interface PerformOperation {
    boolean check(int a);
}
class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }
    PerformOperation isOdd(){
        return (n-> n%2==2);

    }
    PerformOperation isPrime(){
        return (n-> {
            for(int i=2;i*i<=n;i++){
                if(n%i==0) return false;
            }
        return false;
        });
    }
    PerformOperation isPalindrome(){
        return (n-> {
            String[] strAr=Integer.toString(n).split("");
            String str="";
            for(int i=strAr.length-1;i>=0;i--){
                str=str+strAr[i];
            }
            return (Integer.toString(n).equals(str));
        });
    }




} // Write your code here

public class SolutionLamda {

    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T--> 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }
}
