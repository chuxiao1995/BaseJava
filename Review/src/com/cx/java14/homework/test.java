package com.cx.java14.homework;

public class test {
    public static void main(String args[]) {
        String Str = new String("c:/cd/c/dd");
        System.out.println(Str.substring(5,10));
        System.out.print("ƥ��ɹ�����ֵ :" );
        System.out.println(Str.replaceAll("c:cd/c",""));
        System.out.print("ƥ��ʧ�ܷ���ֵ :" );
        //System.out.println(Str.replaceAll("(.*)taobao(.*)", "runoob" ));
    }
}