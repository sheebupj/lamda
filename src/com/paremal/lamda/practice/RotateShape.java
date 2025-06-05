package com.paremal.lamda.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RotateShape {

    /*
    rotate 2dlist of integers (length and breadth equel





    1 2 3
    4 5 6
    7 8 9
    After rotating clockwise  it become
    7 4 1
    8 5 2
    9 6 3
     */
    static List<List<Integer>> rotateClockwise(List<List<Integer>> llist) {
        int size = llist.size();
        Integer[][] twoDArr=llist.stream().map(l-> l.stream().toArray(Integer[]::new)).toArray(Integer[][]::new);

        return Stream.of(rotateClockewise(twoDArr)).map(Arrays::asList).toList();


    }

    /*
    1 2 3
    4 5 6
    7 8 9
    after flipping vertical it becomes
    7 8 9
    4 5 6
    1 2 3
     */
    static List<List<Integer>> flipVertical(List<List<Integer>> llist) {
        int size = llist.size();

        Integer[][] twoDArr=llist.stream().map(l-> l.stream().toArray(Integer[]::new)).toArray(Integer[][]::new);

        return Stream.of(flipVertical(twoDArr)).map(Arrays::asList).toList();


    }

    /*
    1 2 3
    4 5 6
    7 8 9
    after flipping horizontal
    3 2 1
    6 5 4
    9 8 7
     */
    static List<List<Integer>> flipHorizontal(List<List<Integer>> llist) {
        int size = llist.size();

        Integer[][] twoDArr=llist.stream().map(l-> l.stream().toArray(Integer[]::new)).toArray(Integer[][]::new);

        return Stream.of(flipHorzontal(twoDArr)).map(Arrays::asList).toList();


    }


    /*
   1 2 3
   4 5 6
   7 8 9
   After rotating anti-clockwise  it become
   3 6 9
   2 5 8
   1 4 7
    */
    static List<List<Integer>> rotateAntiClockwise(List<List<Integer>> llist) {
        int size = llist.size();
        Integer[][] twoDArr=llist.stream().map(l-> l.stream().toArray(Integer[]::new)).toArray(Integer[][]::new);

        return Stream.of(rotateAntiClockewise(twoDArr)).map(Arrays::asList).toList();


    }
    /*
    1 2 3
    4 5 6
    7 8 9
    after rotating clockwise
    7 4 1
    8 5 2
    9 6 3
     */
  static Integer [][] rotateClockewise( Integer [][] twodAr){
        int size=twodAr.length;
        int indexSize=size-1;
        Integer[][] resultArr= new Integer[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                resultArr[j][indexSize-i]=twodAr[i][j];
            }
        }
        return  resultArr;
    }


    /*
   1 2 3
   4 5 6
   7 8 9
   After rotating anti-clockwise  it become
   3 6 9
   2 5 8
   1 4 7
    */
    static Integer [][] rotateAntiClockewise( Integer [][] twodAr){
        int size=twodAr.length;
        int indexSize=size-1;
        Integer[][] resultArr= new Integer[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                resultArr[i][j]=twodAr[j][indexSize-i];
            }
        }
        return  resultArr;
    }

    /*
    1 2 3
    4 5 6
    7 8 9
    after vertical flip
    7 8 9
    4 5 6
    1 2 3
     */
    static Integer [][] flipVertical(Integer [][] twodAr){
        int size=twodAr.length;
        int indexSize=size-1;
        Integer[][] resultArr= new Integer[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                resultArr[indexSize-i][j]=twodAr[i][j];
            }
        }
        return  resultArr;
    }

    /*
    1 2 3
    4 5 6
    7 8 9
    after horizontal flip
    3 2 1
    6 5 4
    9 8 7
     */
    static Integer [][] flipHorzontal(Integer [][] twodAr){
        int size=twodAr.length;
        int indexSize=size-1;
        Integer[][] resultArr= new Integer[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                resultArr[i][j]=twodAr[i][indexSize-j];
            }
        }
        return  resultArr;
    }


    public static void main(String[] args) {
        System.out.println("Enter integers n*n (squire) numbers in one line equal to number of line and after enter additional Enter key");
        System.out.println(":");
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<List<Integer>> input = new ArrayList<>();
        String line=null;
        while(true){
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(line.length()>0) {
                input.add(Stream.of(line.split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
            }
            else{
                break;
            }
        }
        display(input);

        List<List<Integer>> resul1 = rotateClockwise(input);
        System.out.println("after rotating clockwise");
        display(resul1);
        System.out.println();
        display(input);
        List<List<Integer>> resul4 = rotateAntiClockwise(input);
        System.out.println("after rotating anticlockwise");
        display(resul4);
        System.out.println();

        display(input);
        System.out.println("after vertical flip");
        List<List<Integer>> result2= flipVertical(input);
        display(result2);
        System.out.println();
        display(input);
        System.out.println("after horizontal flip");
        List<List<Integer>> result3= flipHorizontal(input);
        display(result3);

    }

    /*
     display data from two-dimensional list
     */
    static void display(List<List<Integer>> inputList) {
        for (List<Integer> olist : inputList) {
            for (Integer igr : olist) {
                System.out.print(igr + " ");
            }
            System.out.println();
        }

    }

    /*
     display data from two-dimensional Array
     */
    static void displayArray(Integer[][] ar2d) {
        List<List<Integer>> inputList = Arrays.stream(ar2d).map(Arrays::asList).toList();
        display(inputList);
    }
}
