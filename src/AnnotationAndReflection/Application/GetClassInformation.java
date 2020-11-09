package AnnotationAndReflection.Application;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

// 获取类的信息
public class GetClassInformation {
    public static void main(String[] args) {
        try {
            // 1、通过反射获取Class对象
            Class<?> aClass = Class.forName("AnnotationAndReflection.ReflectionBasic.User");

            // 获得类的名字
            System.out.println("================获取类名==============");
            System.out.println(aClass.getName());  // 获得包名+类名
            System.out.println(aClass.getSimpleName());  // 获得类名

            // 获得类的属性
            System.out.println("==================获取类的属性====================");
            Field[] fields = aClass.getFields();  // 只能找到public属性

            fields = aClass.getDeclaredFields();  // 找到全部的属性
            for (Field field : fields) {
                System.out.println("getDeclaredFields: "+field);

            }
            Field name = aClass.getDeclaredField("name");
            System.out.println(name);

            // 获得类的方法
            System.out.println("==================获取类的方法====================");
            Method[] methods = aClass.getMethods();  // 获得本类及其父类的所有public 方法
            for (Method method : methods) {
                System.out.println("getMethods: "+method);
            }

            methods = aClass.getDeclaredMethods();  // 获得本类的所有方法
            for (Method method : methods) {
                System.out.println("getDeclaredMethods: "+method);
            }

            // 获得指定的方法

            Method getName = aClass.getMethod("getName", null);  // 参数的目的是为了重载
            Method setName = aClass.getMethod("setName", String.class);

            System.out.println(getName);
            System.out.println(setName);

            // 获得指定的构造器
            System.out.println("==================获取类的构造器====================");
            Constructor<?>[] constructors = aClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("getConstructors: "+constructor);
            }

            constructors = aClass.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("getDeclaredConstructors: "+constructor);
            }

            // 获得指定的构造器
            System.out.println("==================获取指定的的构造器====================");
            Constructor<?> constructor = aClass.getConstructor(String.class, int.class, int.class);
            System.out.println(constructor);
            constructor = aClass.getDeclaredConstructor(String.class,int.class,int.class);
            System.out.println(constructor);

            // 2、通过实例
            System.out.println("================获取类名==============");
            User user = new User();
            Class<? extends User> userClass = user.getClass();
            System.out.println(userClass.getName());
            System.out.println(userClass.getSimpleName());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
