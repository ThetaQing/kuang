package AnnotationAndReflection.ReflectionBasic;

import java.lang.annotation.ElementType;

// 所有类型的Class
public class Reflection03 {
    public static void main(String[] args) {
        Class c1 = Object.class;  //  类
        Class c2 = Comparable.class;  // 接口
        Class c3 = String[].class;  // 一维数组
        Class c4 = int [].class;  // 二维数组
        Class c5 = Override.class;  // 注解
        Class c6 = ElementType.class;  // 枚举
        Class c7 = Integer.class;  // 基本类型
        Class c8 = void.class;  // void
        Class c9 = Class.class;  // Class

        // 只要元素类型和维度是同一个类型，就是同一个类
        int[] a  = new int[10];
        int[] b = new int[100];


        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());
    }
}
