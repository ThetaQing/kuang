package AnnotationAndReflection.Application;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 通过反射动态创建对象
public class CreateObjectDynamically {
    public static void main(String[] args) {
        try {
            // 1、获得Class对象
            Class<?> c1 = Class.forName("AnnotationAndReflection.Application.User");

            // 2、构造一个对象
            User user = (User)c1.newInstance();  // 本质上是调用了类的无参构造器
            System.out.println(user);

            // 通过构造器创建对象
            Constructor<?> constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
            User me = (User)constructor.newInstance("me", 001, 18);
            System.out.println(me);

            // 通过反射调用普通方法

            // 1 通过反射获取一个方法
            User user3 = (User)c1.newInstance();
            Method setName = c1.getDeclaredMethod("setName", String.class);
            setName.invoke(user3, "songqing");  // invoke(Object,value):激活
            System.out.println(user3.getName());
            Method getName = c1.getDeclaredMethod("getName", null);
            System.out.println(getName.invoke(user3, null));

            // 2 通过反射操作属性
            User user4 = (User)c1.newInstance();
            Field name = c1.getDeclaredField("name");

            // 不能直接操作私有属性，必须关闭程序安全检测，通过属性或方法的setAccessible
            // 关闭权限检测
            name.setAccessible(true);
            name.set(user4,"xiannv4");
            System.out.println(user4.getName());




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
