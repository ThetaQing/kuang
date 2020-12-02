package JVM.NativeDemo;

/**
 * @author SongQing
 * 测试Native关键字
 * 重点
 * 凡是带了native关键字的，说明java的作用范围达不到了，需要调底层C语言的库，会进入本地方法栈，然后调用
 * 本地方法接口（JNI）
 * JNI的作用：扩展JAVA的使用，融合不同的编程语言为Java所用，最初是为了融合C、C++
 * Java诞生的时候，C、C++横行，想要立足，就必须有调用C、C++的程序
 * 它在内存区域中，专门开辟了一块标记区域（本地方法栈），登记native方法，会在最终执行的时候加载
 * 本地方法库中的本地方法
 *
 *
 * 例如
 * Java程序驱动打印机、管理系统、Robot类等，在企业级应用中较为少见。
 *
 * 调用其他接口的方法：Socket、WebService、Http
 */
public class NativeTest {
    public static void main(String[] args) {
        new Thread(()->{

        },"my thread name").start();
    }
}
