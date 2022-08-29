package test1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printArray(fillArray());
    }
    //fill array with random num
    public static int [][]  fillArray (){
        int[][] myArr = new int[5][5];
        for (int x = 0; x < myArr.length; x++) {
            for (int y = 0; y < myArr[x].length; y++) {
                myArr[x][y] = (int) generateRandomNum(System.nanoTime());//add the random number to specific index
            }
        }
        return myArr;
    }
    //Xorshift-random numbers generator
    public static long generateRandomNum (long seed){
        seed ^= seed << 13;
        seed ^= seed >> 17;
        seed ^= seed << 5;
        return seed;
    }
    //find MAX,MIN,AVERAGE
    public static void findMaxMinAverage (int[][]myArr){
        int max=myArr[0][0];
        int min=myArr[0][0];
        long sum=0;
        int count=0;
        double averageNum = 0;
        for (int x = 0; x < myArr.length; x++) {
            for (int y = 0; y < myArr[x].length; y++) {
                if (myArr[x][y] > max) {
                    max = myArr[x][y];
                }
                if (myArr[x][y] < min) {
                    min = myArr[x][y];
                }
                sum += myArr[x][y];
                count++;
            }
        }
        averageNum = sum / count;
        System.out.println("MAX " +max + " MIN " + min + " AVERAGE " + averageNum);
        System.out.println("also using stream: " +
                "MAX " + Arrays.stream(myArr).flatMapToInt(Arrays::stream).max()+
                " MIN " + Arrays.stream(myArr).flatMapToInt(Arrays::stream).min()+
                " AVERAGE " + Arrays.stream(myArr).flatMapToInt(Arrays::stream).average().getAsDouble());
    }
    //Print the 2d Array and results
    public static void printArray (int[][] myArr){
        for (int x = 0; x < myArr.length; x++) {
            for (int y = 0; y < myArr[x].length; y++) {
                System.out.print(myArr[x][y] + "\t");
            }
            System.out.println();
        }
        findMaxMinAverage(myArr);
    }
}
