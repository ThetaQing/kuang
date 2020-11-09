# 注解
## 1、概论
Annotation的作用：不是程序本身，可以对程序作出解释（与注释Commet类似）；**可以被
其他程序（比如编译器等）读取**。  
Annotation的格式：注解以"@注解名"在代码中存在，还可以添加一些参数值，例如
@SuppressWarnings(value="unchecked").  
Annotation在哪里使用：可以附加在package，class，method，field等上面，
相当于给他们添加了额外的辅助信息，通过反射机制编程实现对这些注解的访问。  
### 1.1 内置注解
@Override：表示一个方法打算重写超类的方法；  
@Deprecated：不鼓励使用这样的元素；  
@SuppressWarnings：抑制编译时的警告信息，需要添加一个参数才能使用。  
### 1.2 元注解
元注解的作用就是负责解释其他注解，Java定义了4个标准的meta-annotation类型，它们被用来提供对其他annotation类型作说明。  
@Target：用于描述注解的使用范围（即被描述的注解可以用在什么地方）；  
@Retention:表示需要在什么级别保存该注释信息，用于描述注解的生命周期，如：SOURCE<CLASS<RUNTIME  
@Document:说明该注解将被包含在javadoc中；  
@Inherited：说明子类可以继承父类中的该注解。  

### 1.3 自定义注解
使用@interface自定义注解：public @interface 注解名{定义内容}  
* 其中的每一个方法实际上是声明了一个配置参数；  
* 方法的名称就是参数的名称；  
* 返回值类型就是参数的类型（返回值只能是基本类型，Class，String，enum）  
* 可以提供default来声明参数的默认值；  
* 如果只有一个参数成员，一般参数名为value,这样就能省略参数名称；  
* 注解元素必须要有值(如果定义了的话，而且参数名要对齐)，我们定义注解元素时，经常使用空字符串，0作为默认值。  
# 反射
因为有了反射，Java作为静态语言具有动态行。  
## 2 概述
静态语言 VS 动态语言  
动态语言：是一类在运行时可以改变其结构的语言，例如新的函数、对象、设置代码可以被引进
，已有的函数可以被删除或是其他结构上的变化，通俗点说就是在运行时代码可以根据某些条件
改变自身结构。  
主要的动态语言有：Object-C、C#、JavaScript、PHP、Python  
静态语言：与动态语言相对应的，运行时结构不可变的语言就是静态语言。  
主要的静态语言有：Java、C、C++。  
Java不是动态语言，但Java可以称为”准动态语言“。即Java有一定的动态性，
我们可以利用反射机制获得类似动态语言的特性，Java的动态性让编程更灵活。  
Reflection（反射）是Java被视为动态语言的关键，反射机制允许程序在执行期借助于
Reflection API 取得任何类的内部消息，并能直接操作任意对象的内部属性及方法。  
Class c = Class.forName("java.lang.String")  

加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象（**一个类只有一个Class对象**,可以通过判断其hashcode来确认），
这个对象就包含了完整的类的结构信息，通过这个对象就能看到类的结构。这个对象就像一面镜子，透过这个
镜子看到类的结构，所以形象地称之为：反射。  
### 2.1 反射机制的功能
* 在运行时判断任意一个对象所属的类；  
* 在运行时构造任意一个类的对象；  
* 在运行时判断任意一个类所具有的成员变量和方法；  
* 在运行时获取泛型信息；  
* 在运行时调用任意一个对象的成员变量和方法；  
* 在运行时处理注解；  
* 生成动态代理等。  
### 2.2 优缺点
* 优点：可以实现动态创建对象和编译，体现很大的灵活性；  
* 缺点： 对性能有影响。使用反射基本上是一种解释操作，总是慢于直接执行相同的操作。  
### 2.3 Class类特点
在Object类中定义了以下方法，被所有子类继承。  
public final Class getClass()  
该方法返回值的类型是一个Class类，该类是Java反射的源头，实际上所谓反射从程序
的运行结果来看，即：可以通过对象反射求出类的名称。  
每个Class类都包括：某个类的属性、方法和构造器、某个类到底实现了哪些接口。对于每个类而言，
JRE都为其保留一个不变的Class类型的对象。一个Class对象包含了特定某个结构的有关信息。  
* Class本身也是一个类；  
* Class对象只能由系统建立对象；  
* 一个加载的类在JVM中只会有一个Class实例；  
* 一个Class对象对应的是一个加载到JVM中的一个class文件；  
* 每个类的实例都会记得自己是由哪个Class实例所生成；  
* 通过Class可以完整地得到一个类中的所有被加载的结构；  
* Class类是Reflection的根源，针对任何你想动态加载、运行的类，唯有先获得相应的Class对象。  

### 2.4 获取Class类的实例  
* 若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序安全性能最高：Class clazz = Person.class;  
* 已知某个类的实例，调用该实例的getClass()方法获取Class对象：Class clazz = person.getClass();  
* 已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能抛出异常：Class clazz = Class.forName("AnnotationAndReflection.ReflectionBasic.User");  
* 内置基本数据类型可以直接用类名.Type；  
* 还可以利用ClassLoader。  
```java
package AnnotationAndReflection.ReflectionBasic;

// 创建Class的几种方式
public class Reflection02 {
    public static void main(String[] args) {
        Person person = new Student();
        System.out.println("this is"+person.name);

        // 1. 通过对象获得
        Class<? extends Person> c1 = person.getClass();
        System.out.println(c1.hashCode());
        //2. get by forname
        try {
            Class<?> c2 = Class.forName("AnnotationAndReflection.ReflectionBasic.Student");
            System.out.println(c2.hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 3. get by .class
        Class<Student> c3 = Student.class;
        System.out.println(c3.hashCode());

        // 4. 基本内置类型的包装类都有一个Type属性
        Class<Integer> c4 = Integer.TYPE;

        // 获得父类类型
        Class<?> c5 = c1.getSuperclass();
        System.out.println(c5);
    }
}

class Person{
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
class Student extends Person{
    public Student() {
        this.name = "学生";
    }
}
class Teacher extends Person{
    public Teacher() {
        this.name = "老师";
    }
}

```
### 2.5 哪些类型可以有Class对象
* class：外部类，成员（成员内部类、静态内部类）、局部内部类、匿名内部类；  
* interface：接口；  
* []:数组；  
* enum：枚举；  
* annotation：注解；  
* primitive type：基本数据类型；  
* void。  
```java
package AnnotationAndReflection.ReflectionBasic;

import java.lang.annotation.ElementType;

// 所有类型的Class
public class Reflection03 {
    public static void main(String[] args) {
        Class c1 = Object.class;  //  类
        Class c2 = Comparable.class;  // 接口
        Class c3 = String[].class;  // 一维数组
        Class c4 = int [].class;  // 二维数组
        Class c5 = Override.class;  // 注解
        Class c6 = ElementType.class;  // 枚举
        Class c7 = Integer.class;  // 基本类型
        Class c8 = void.class;  // void
        Class c9 = Class.class;  // Class

        // 只要元素类型和维度是同一个类型，就是同一个类
        int[] a  = new int[10];
        int[] b = new int[100];


        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());
    }
}

```
# 类加载器
## 3 概述
Java内存： 
* 堆  
    * 存放new的对象和数组；  
    * 可以被所有的线程共享，不会存放别的对象引用；  
* 栈  
    * 存在基本变量类型（包含这个基本类型的具体数值）；  
    * 引用对象的变量（包括存放这个引用在堆里面的具体地址）；
* 方法区
    * 可以被所有的线程共享；  
    * 包含了所有的class和static变量。  

  
类的加载过程：（ClassLoad.java）  
当程序主动使用某个类时，如果该类还未被加载到内存中，则系统会通过以下三个步骤来对该类进行初始化。  
* 1、类的加载（load）：将类的class文件字节码内容读入内存，并将这些静态数据转换成方法区的运行时数据结构
，然后生成一个代表这个类的java.lang.Class对象，所以我们只能”获取“，不能”创建“；  
* 2、链接：将Java类的二进制代码合并到JVM的运行状态之中的过程；  
    * 验证：确保加载的类信息符合JVM规范，没有安全方面的问题；  
    * 准备：正式为类变量（static）分配内存并设置类变量默认初始值的阶段，这些内存都将在方法区进行分配；  
    * 解析：虚拟机常量池内的符号引用（常量名）替换为直接引用（地址）的过程。  
* 3、初始化：  
    * 执行类构造器<clinit>()方法的过程（JVM完成）。类构造器<clinit>()方法是由编译器自动手机类中所有类变量的赋值动作
    和静态代码块的语句合并产生的；（类构造器是构造类信息的，不是构造该类对象的构造器）；  
    * 当初始化一个类的时候，如果发现其父类还没有进行初始化，则需要先触发其父类的初始化；  
    * 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确加锁和同步。  
```java
package AnnotationAndReflection.ReflectionBasic;
// 演示类的加载
public class ClassLoad {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
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

```  
### 3.1 什么时候会发生类的初始化
* 类的主动引用（一定会发生类的初始化）  
    * 当虚拟机启动，先初始化main方法所在的类；  
    * new一个类的对象；  
    * 调用类的静态成员（除final常量）和静态方法（子类调用父类的静态成员只会初始化父类）；  
    * 使用java.lang.reflect包的方法对类进行反射调用；  
    * 当初始化一个类，如果其父类没有被初始化，则先会初始化它的父类。  
* 类的被动引用（不会发生类的初始化）  
    * 当访问一个静态域时，只有真正声明这个域的类才会被初始化，如：当通过子类引用父类的静态变量，不会导致
    子类初始化；  
    * 通过数组定义类引用，不会触发此类的初始化；  
    * 引用常量不会触发此类的初始化（常量在链接阶段就存入调用类的常量池中）。  
```java
package AnnotationAndReflection.ReflectionBasic;
// 测试什么时候会发生类的初始化，什么时候不会发生类的初始化
public class ClassInit {
    static {
        System.out.println("main被初始化");
    }

    public static void main(String[] args) {
        // 会初始化类的情况
        // 1 主动引用
        //System.out.println("===========主动引用=============");
        //Son son = new Son();  // 先初始化main，再初始化父类，最后初始化子类
        // 2 调用类的静态成员
        //System.out.println("=============调用类的非final静态成员=============");
        //System.out.println(Son.m);  // 先初始化main，再父类，再子类


        // 3 反射调用
        /*try {
            System.out.println("=============反射调用===============");
            Class<?> aClass = Class.forName("AnnotationAndReflection.ReflectionBasic.Son");
        // 先main，再父类，最后子类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // 不会初始化类的情况
        //1 子类方位访问父类静态域
        //System.out.println(Son.father);  // 先初始化main，再父类，子类不会被初始化

        // 2 数组定义类引用
        //Son[] array = new Son[5];  // 只初始化main

        // 3 引用常量
        System.out.println(Son.const_son);  // 只初始化main


    }

}

class Father{
    static {
        System.out.println("父类被初始化");
    }
    static int father = 2;
}
class Son extends Father{
    static {
        System.out.println("子类被初始化");
        m = 300;
    }
    static int m = 100;
    static final int const_son = 3;
}

```       
### 3.2 类加载器的作用
类加载器的作用：将class文件字节码内容加载到内存中，并将这些静态数据转换成方法去的运行时数据结构，然后在堆中生成一个代表这个类的
java.lang.Class对象，作为方法区中类数据的访问入口。  
类缓存： 标准的JavaSE类加载器可以按要求查找类，但一旦某个类被加载到类加载器中，它将维持加载（缓存）一段时间。
不过JVM垃圾回收机制可以回收这些Class对象。  
  
源程序（\*.java文件）--> Java编译器 --> 字节码文件（\*.class） --> 类加载器 --> 字节码校验器 -->
解释器 --> 操作系统平台    

### 3.3 类加载器的分类
引导类加载器：用C++编写的，是JVM自带的类加载器，负责JAVA平台核心库（rt.java），用来装载核心类库，该加载器无法直接获取；  
扩展类加载器(ExtClassLoader)：负责jre/lib/ext目录下的jar包或-D java.ext.dirs指定目录下的jar包装入工作库；  
系统类加载器(AppClassLoader)：负责java-classpath或-D java.class.path所指的目录下的类与jar包装入工作，是最常用的加载器。  
获取类加载器并查询类是被哪个加载器加载。  
```java
package AnnotationAndReflection.ReflectionBasic;
// 演示类的加载、类加载器的获取
public class ClassLoad {
    public static void main(String[] args) {

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
```
### 3.4 双亲委派机制
定义一个类，类加载器会查询是否已经存在这个类，不断查询双亲类加载器中是否存在这个类，如果存在，则不会加载自定义的这个类。  

