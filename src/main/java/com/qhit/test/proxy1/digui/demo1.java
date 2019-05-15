package com.qhit.test.proxy1.digui;


/**
 * Created by tp on 2018/12/18.
 */
public class demo1 {
   private static int sum=0;
   private  static int  fnum=1;
    private  static int  snum=1;
    public static void main(String[] args) {

//        System.out.println(calc(10));
//        System.out.println(demo3(7));
        System.out.println(demo4("1"));

    }
    public  static int  calc(int n){
        if(n>0){
            sum+=n;
            n--;
            calc(n);
        }
        return sum;
    }
    public  static  boolean demo2(int num){
        String str=num+"";
        char[] chars = str.toCharArray();
        boolean flag=true;
      out: for (int i = 0; i <chars.length/2 ; i++) {
            for (int j = chars.length/2; j >=0 ; j--) {
                if(chars[i]==chars[j]){
                 continue out;
                }
                return false;
            }
        }
        return false;
    }
//    1、1、2、3、5、8、13、21、34 ，
    public static int  demo3(int a){
        if(a<=0){
            return -1;
        }
        else if(a==1||a==2){
            return 1;
        }
        else{
            return demo3(a-1)+demo3(a-2);
        }

    }
    public static boolean demo4(String str){
        StringBuffer stringBuffer=new StringBuffer(str);
        StringBuffer reverse = stringBuffer.reverse();
        String s = reverse.toString();
        System.out.println(reverse);
        System.out.println(str);
        if(s.equals(str)){
            return true;
        }
        return false;
    }
}
