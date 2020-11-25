package CollectionFrame.Generics;

/**
 * 泛型方法
 * 语法：<T> 返回值类型
 *
 *     */
public class GenericsMethod {
    // 泛型方法
    public <T> void show(T t){
        System.out.println(t);
        // 无返回的泛型函数
    }

    // 有返回值的泛型函数

    public <T> T show2(T t){
        System.out.println(t);
        return t;
    }
}
