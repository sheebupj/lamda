package com.paremal.lamda.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
check a string anagram or not without sort
 */
public class CheckAnagram {
    public static void main(String[] args) {
        System.out.println(isAllInWord("godd","dog")&&isAllInWord("dog","god"));

    }

    static boolean isAllInWord(String f, String s){
        List<String> distinctCharList= Arrays.stream(f.split("")).distinct().collect(Collectors.toList());

        for(String c:s.split("")){
            if(!distinctCharList.contains(c)) return false;
        }
        return true;
    }
}
