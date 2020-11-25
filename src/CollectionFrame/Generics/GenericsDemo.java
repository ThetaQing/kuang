package CollectionFrame.Generics;

/**
 * 泛型测试
 * 语法：类名<T>
 * T是类型占位符，表示一种引用类型，如果编写多个使用逗号隔开。
 * 注意：1、泛型只能是引用类型；2、不同泛型对象之间不能相互赋值
 */

public class GenericsDemo{

    public static void main(String[] args){
        MyGenerics<String> stringMyGenerics = new MyGenerics<String>();  // 传递给T的只能是引用类型
        stringMyGenerics.t = "hello";
        String t = stringMyGenerics.getT();
        System.out.println(t);

        GenInterfaceImplement tmp1 = new GenInterfaceImplement();

        tmp1.server("h");

        GenInterfaceImplementGenerics<Integer> tmp2 = new GenInterfaceImplementGenerics<>();
        tmp2.server(2);

        GenericsMethod tmp3 = new GenericsMethod();
        tmp3.show("hello");
        System.out.println(tmp3.show2(2));
    }

}

class MyGenerics <T>{
    // 使用泛型T
    // 创建变量
    T t;

    // 创建方法,作为方法的参数
    public void show(T t){
        // 注意，可以创建对象的引用，但是不可以new一个对象

    }
    // 泛型作为方法的返回值
    public T getT(){
        return t;

    }


}

