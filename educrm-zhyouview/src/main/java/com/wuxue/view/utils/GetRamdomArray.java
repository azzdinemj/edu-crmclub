package com.wuxue.view.utils;

import java.util.HashSet;

public class GetRamdomArray {

    public static void main(String[] args) {
//        int[] array = new int[9];
//        for(int i=0;i<array.length;i++){
//            array[i] = (int)(Math.random()*9);
//        }
//
//        for ( int i: array) {
//            System.out.println(i);
//        }

    }


    //获取随机数组   param1 数量   param2 范围
    public static HashSet<Integer> getramdomArr(Integer size,Integer allsize) {
        HashSet<Integer> hs = new HashSet<Integer>();
        int intArray[] =new int[size];
        while (true) {
            int a = (int)(Math.random() * allsize);
            if(a >= 0 && a <= allsize) {
                hs.add(a);
            }
            if (hs.size() == size) {
                break;
            }
        }
        return hs;
    }




}









