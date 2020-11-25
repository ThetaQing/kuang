package CollectionFrame.Generics;

// 实现接口时不确定具体类型

// 第一个T是泛型类的T，第二个T是传给实现接口的T。

public class GenInterfaceImplementGenerics<T> implements GenericsInterface<T>{
    @Override
    public T server(T t) {
        System.out.println(t);
        return t;
    }
}
