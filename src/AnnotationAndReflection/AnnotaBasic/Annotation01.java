package AnnotationAndReflection.AnnotaBasic;

import java.lang.annotation.*;

public class Annotation01 {
    // 注解可以显式地赋值，如果没有默认值就必须传参
    @MyAnnotation(name = "h",schools = {"中南大学"})
    public void test(){}

    @MyAnnotation2("")
    public void test2(){}

}
// 定义一个注解
// 添加元注解
// Target表示注解适用在哪些地方
@Target(value = {ElementType.METHOD,ElementType.TYPE})
// Retention 表示注解在什么地方还有效,注解的级别
// Runtime > Class > Source,自定义注解一般都是runtime
@Retention(value = RetentionPolicy.RUNTIME)

//Document表示是否将我们的注解生成在JavaDoc中
@Documented
//Inherited 表示子类可以继承父类的注解
@Inherited

@interface  MyAnnotation{
    // 定义注解的参数：参数类型 + 参数名 + ();
    String name() default "";
    int age() default 0;
    int id() default -1;  // 如果默认值为-1，代表不存在

    String[] schools() default {"清华大学","中南大学"};

}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    String value();
}