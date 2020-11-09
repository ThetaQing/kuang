package AnnotationAndReflection.Application;

import java.lang.reflect.Method;

// 分析性能
public class AnalyzePerformance {
    // 普通方法调用
    public static void normalCall(){
        User user = new User();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("普通调用执行10亿次："+(endTime-startTime)+"ms");

    }


    // 反射方式调用  开启检测

    public static void reflectionCall(){
        try {
            Class<?> aClass = Class.forName("AnnotationAndReflection.Application.User");
            User user = (User)aClass.newInstance();
            Method getName = aClass.getMethod("getName", null);


            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000000000; i++) {
                getName.invoke(user,null);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("反射调用执行10亿次："+(endTime-startTime)+"ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 反射方式调用  关闭检查
    public static void reflectionCallSetAccessibleTrue(){
        try {
            Class<?> aClass = Class.forName("AnnotationAndReflection.Application.User");
            User user = (User)aClass.newInstance();
            Method getName = aClass.getMethod("getName", null);
            getName.setAccessible(true);


            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000000000; i++) {
                getName.invoke(user,null);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("反射调用并关闭检查执行10亿次："+(endTime-startTime)+"ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        normalCall();
        reflectionCall();
        reflectionCallSetAccessibleTrue();


    }
}
