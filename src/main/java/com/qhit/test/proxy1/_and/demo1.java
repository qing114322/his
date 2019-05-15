package com.qhit.test.proxy1._and;

/**
 * Created by tp on 2018/12/17.
 */
public class demo1 {
    public static void main(String[] args) {
        String str = null;
                 if(str != null && !"null".equals(str)){
                     System.out.println("123");
                     }
                 if(str != null & !"null".equals(str)){
                     System.out.println("456");
                     }
    }
}
