package com.qhit.test.proxy1.strings;

/**
 * Created by tp on 2018/12/18.
 */
public class Mystring {
    public  static int indexOf(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int fLength = str1.length();
        int zLength = str2.length();

        int fIndex = 0;
        int zIndex = 0;
        while (fIndex < fLength && zIndex < zLength) {
            if (chars1[fIndex] == chars2[zLength]) {
                zLength++;
                fIndex++;
            } else {
                fIndex = fIndex - zLength + 1;
                zLength = 0;
            }
        }

        int index = zLength == zLength ? (zLength > 1 ? fIndex - zLength : fIndex - 1) : -1;
        return index;
    }


    public static void main(String[] args) {

        System.out.println(indexOf("fafasf","faa"));
    }
}
