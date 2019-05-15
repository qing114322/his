package com.qhit.test.proxy1.fori;

/**
 * Created by tp on 2019/1/7.
 */
/*定义一个变量n=5
        1 打印如下图形 (打印n个图形)
        +-+-+
        2 打印如下图形 (打印n行n列 + -)
        +-+-+-
        -+-+-+
        +-+-+-
        -+-+-+
        +-+-+-
     */
public class Demo1 {
    public static void main(String[] args) {
        work2(5);
    }
    public  static void work1(int n){

        for (int i = 0; i <n ; i++) {
            if(i%2==0){
                System.out.print("+");
            }
            if(i%2!=0){
                System.out.print("-");
            }
        }

    }
    public static void work2(int n){
        for (int j = 0; j < n; j++) {
            for (int i = j; i <n ; i++) {
                if(i%2==0){
                    System.out.print("+");
                }
                if(i%2!=0){
                    System.out.print("-");
                }
            }

        }

    }

}
