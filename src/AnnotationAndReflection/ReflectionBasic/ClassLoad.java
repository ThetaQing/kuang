package AnnotationAndReflection.ReflectionBasic;
// 演示类的加载、类加载器的获取
public class ClassLoad {
    public static void main(String[] args) {
        /*
        * 1、加载到内存，就会产生一个类对应的Class对象；
        * 2、链接，链接结束后，m = 0，（给int分配的默认值）；
        * 3、初始化
        <clinit>(){
                    System.out.println("A类的静态代码块初始化");
                    m = 300;
                    m = 100;
                }
         初始化完了之后
               m = 100;
        * */
        A a = new A();
        System.out.println(A.m);

        // 获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父类加载器  -- > 扩展类加载器
        ClassLoader systemClassLoaderParent = systemClassLoader.getParent();
        System.out.println(systemClassLoaderParent);

        // 获取扩展类加载器的父类加载器 --> 根加载器（C/C++）
        ClassLoader systemClassLoaderParentParent = systemClassLoaderParent.getParent();
        System.out.println(systemClassLoaderParentParent);


        try {
            // 测试当前类是那个加载器加载的  --> AppClassLoader加载的
            Class<?> aClass = Class.forName("AnnotationAndReflection.ReflectionBasic.ClassLoad");
            System.out.println(aClass.getClassLoader());

            // 测试JDK内部类是由谁加载的  --> 根加载器加载的
            aClass = Class.forName("java.lang.Object");
            System.out.println(aClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // 如何获得系统加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
        /*
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\charsets.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\deploy.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\access-bridge-64.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\cldrdata.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\dnsns.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\jaccess.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\jfxrt.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\localedata.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\nashorn.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunec.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunjce_provider.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunmscapi.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunpkcs11.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\zipfs.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\javaws.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\jce.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\jfr.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\jfxswt.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\jsse.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\management-agent.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\plugin.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\resources.jar;
        D:\Program Files\Java\jdk1.8.0_202\jre\lib\rt.jar;
        G:\Code\Java\kuang\out\production\kuang;
        D:\Program Files\JetBrains\IntelliJ IDEA 2020.1\lib\idea_rt.jar

         * */

    }

}
class A{
    static{
        System.out.println("A类的静态代码块初始化");
        m = 300;
    }
    static int m = 100;

    public A() {
        System.out.println("A类的无参构造器初始化");
    }
}
