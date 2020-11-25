package CollectionFrame.Generics;

/**
 * 泛型接口
 * 语法：接口名<T>
 * 注意，接口中静态常量默认有：`public static final`修饰。不能创建泛型静态常量；
 */
public interface GenericsInterface<T> {
    String name = "zhangsan";
    T server(T t);


}
