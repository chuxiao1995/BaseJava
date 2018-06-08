package com.cx.java14.homework;

public class test {
    public static void main(String args[]) {
        String Str = new String("c:/cd/c/dd");
        System.out.println(Str.substring(5,10));
        System.out.print("匹配成功返回值 :" );
        System.out.println(Str.replaceAll("c:cd/c",""));
        System.out.print("匹配失败返回值 :" );
        //System.out.println(Str.replaceAll("(.*)taobao(.*)", "runoob" ));
    }
}