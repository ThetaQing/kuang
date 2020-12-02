package JVM.ClassLoaderDemo;

/**
 * @author SongQing
 * 类加载器、双亲委派机制
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        // 类是模板，对象是具体的
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        System.out.println(car3.hashCode());

        Class<? extends Car> car1Class = car1.getClass();
        Class<? extends Car> car2Class = car2.getClass();
        Class<? extends Car> car3Class = car3.getClass();

        System.out.println(car1Class.hashCode());
        System.out.println(car2Class.hashCode());
        System.out.println(car3Class.hashCode());


        ClassLoader classLoader = car1Class.getClassLoader();
        // AppClassLoader
        System.out.println(classLoader);

        // ExtClassLoader  位置 ...\Java\jdk1.8.0_202\jre\lib\ext
        System.out.println(classLoader.getParent());

        // null java程序不能获取到的
        System.out.println(classLoader.getParent().getParent());


    }
}
class Car{
    public int id;
}
