package com.qhit.test.proxy1;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by tp on 2018/12/29.
 */
public class SortTest {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(radom(20,30)));
//        System.out.println(Arrays.toString(selectStort());

    }
    public  static int[] radom(int n,int m){
        Set<Integer> integers=new TreeSet<Integer>();
        Random random=new Random();
        for (int i = 0; i <n ; i++) {
            while (integers.size()<n){
                integers.add(random.nextInt(m+1));
            }

        }
        Object[] objects = integers.toArray();
        int b[]=new int[n];
        for (int i = 0; i <objects.length ; i++) {
           b[i]=(int)objects[i];
        }
        return b;
        
    }

    public  static void selectStort(int a[]){
     int min=0;
        for (int i = 0; i <a.length ; i++) {
            for (int j = 1; j < a.length-1; j++) {
                if(a[i]<a[j]){
                    i=min;
                }else {
                }

            }
        }

    }

}
