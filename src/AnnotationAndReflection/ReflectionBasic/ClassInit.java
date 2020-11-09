package AnnotationAndReflection.ReflectionBasic;
// 测试什么时候会发生类的初始化，什么时候不会发生类的初始化
public class ClassInit {
    static {
        System.out.println("main被初始化");
    }

    public static void main(String[] args) {
        // 会初始化类的情况
        // 1 主动引用
        //System.out.println("===========主动引用=============");
        //Son son = new Son();  // 先初始化main，再初始化父类，最后初始化子类
        // 2 调用类的静态成员
        //System.out.println("=============调用类的非final静态成员=============");
        //System.out.println(Son.m);  // 先初始化main，再父类，再子类


        // 3 反射调用
        /*try {
            System.out.println("=============反射调用===============");
            Class<?> aClass = Class.forName("AnnotationAndReflection.ReflectionBasic.Son");
        // 先main，再父类，最后子类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // 不会初始化类的情况
        //1 子类方位访问父类静态域
        //System.out.println(Son.father);  // 先初始化main，再父类，子类不会被初始化

        // 2 数组定义类引用
        //Son[] array = new Son[5];  // 只初始化main

        // 3 引用常量
        System.out.println(Son.const_son);  // 只初始化main


    }

}

class Father{
    static {
        System.out.println("父类被初始化");
    }
    static int father = 2;
}
class Son extends Father{
    static {
        System.out.println("子类被初始化");
        m = 300;
    }
    static int m = 100;
    static final int const_son = 3;
}
