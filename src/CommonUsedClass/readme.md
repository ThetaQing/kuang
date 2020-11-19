# 常用类
## 1 内部类
概念：在一个类的内部再定义一个完整的类。  
特点：  
* 编译之后可以生成独立的字节码文件（class文件）；  
* 内部类可以直接访问外部类的私有成员，而不破坏封装；  
* 可为外部类提供必要的内部功能组件。  
  
### 1.1 成员内部类
* 在类的内部定义，与实例变量、实例方法同级别的类。  
* 作为外部类的一个实例部分，创建内部类对象时，必须依赖外部类对象。  
* 当外部类、内部类存在重名属性时，会优先访问内部类属性，如果仍然需要
访问外部类(OuterClassName)的属性(attributeName)需要加`OuterClassName.this.attributeName`。  
* 成员内部类不能定义静态成员,但是可以定义静态常量。  

定义一个成员内部类：  
```java
package CommonUsedClass.InnerClass.AnonymousInner.MemberInner;

// 成员内部类
public class MemberInnerClass {
    private String name;
    private int age;
    private String school;
    public MemberInnerClass() {
    }
    public MemberInnerClass(String name, int age) {
        this.name = name;
        this.age = age;
    }



    class Inner{
        private String school;

        public Inner() {
        }

        public Inner(String school) {
            this.school = school;
        }

        public void show(){
            System.out.println(name);
            System.out.println(age);
            System.out.println("内部类学校： "+school);
            System.out.println("外部类名字： " + CommonUsedClass.InnerClass.MemberInner.MemberInnerClass.this.school);
        }
    }

}

```
创建一个成员内部类的对象：
```java
package CommonUsedClass.InnerClass.AnonymousInner.MemberInner;

public class Main {
    public static void main(String[] args) {
        
        MemberInnerClass.Inner inner = new MemberInnerClass("zhangsan", 18).new Inner("CSU");
        inner.show();
    }
}

```

### 1.2 静态内部类
* 在成员内部类上加`static`关键字变成静态内部类，只有静态内部类才能用`static`修饰，普通的类时不能用`static`修饰的；  
* 不依赖外部类对象，可直接创建或通过类名访问，可声明静态成员。  
* 级别和外部类相同,不能直接调用外部类的属性，只能先在内部类中创建外部类的对象，再通过对象调用外部类的属性。  
* 可以直接创建一个静态内部类的对象。

定义一个静态内部类  
```java
package CommonUsedClass.InnerClass.AnonymousInner.StaticMemberInner;


// 静态成员内部类
public class StaticMember {
    private String name;
    private int age;
    private String school;
    public StaticMember() {
    }

    public StaticMember(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;

    }

    // 静态内部类，级别和外部类相同
    static class Inner{
        private String school;

        public Inner() {
        }

        public Inner(String school) {
            this.school = school;
        }

        public void show(){
// 只能通过创建外部类的对象才能访问外部类的属性
            CommonUsedClass.InnerClass.StaticMemberInner.StaticMember sm = new CommonUsedClass.InnerClass.StaticMemberInner.StaticMember("zhangsan",18,"PekingUniversity");

            System.out.println("内部类学校： "+school);
            System.out.println("外部类名字： " +sm.school);
        }
    }

}

```  

直接创建一个静态内部类的对象  
```java
package CommonUsedClass.InnerClass.AnonymousInner.StaticMemberInner;

public class Main {
    public static void main(String[] args) {
        StaticMember.Inner csu = new StaticMember.Inner("CSU");
        csu.show();
    }
}

```

### 1.3 局部内部类
定义在外部类方法中，作用范围和创建对象范围仅限于当前方法。  
定义局部内部类时，不能加任何访问修饰符。  

* 局部内部类访问外部类当前方法中的局部变量时，因无法保障变量的生命周期与自身相同，所以变量必须
修饰为`final`； 
    
    * 访问局部变量时，jdk1.7要求必须是常量`final`，jdk1.8会自动添加`final`(因为非常量的话，方法执行完局部
变量就消失了，而这个类的对象并没有立即消失（虽然变量没有了），并且这个类也不会消失，所以类不能引用一个消失的变量);  
* 访问和外部类的属性同名的内部类属性时，必须加`外部类名字+this.`; 
*  不能包含静态成员，但是可以声明静态常量成员；  
* 限制类的使用范围，只能在当前方法中使用。  

局部内部类的定义：
```java
package CommonUsedClass.InnerClass.LocalInner;

public class Local {
    private String name = "zhangsan";
    private int age = 18;

    public void show(){
        // 定义局部变量
        String address = "shenzhen";
        String name = "lisi";
        class Inner{
            private String phone = "110";
            private void show(){
                System.out.println("外部类中的名字： "+Local.this.name);
                System.out.println("内部类中的名字： "+name);
                System.out.println("age = "+age);
                System.out.println("address = "+address);
            }
        }
        Inner inner = new Inner();  // 创建一个对象
        inner.show();
    }
}

```

局部内部类的使用
```java
package CommonUsedClass.InnerClass.LocalInner;

public class Main {
    public static void main(String[] args) {
        Local local = new Local();
        local.show();
    }
}

```
### 1.4 匿名内部类
没有类名(有名字但不是我们显式定义的)的局部内部类，一切特征都与局部内部类相同。  
* 必须继承一个父类或者实现一个接口；  
* 定义类、实现类、创建对象的语法合并，只能创建一个该类的对象；  

优点：减少代码量；  
缺点：可读性较差。  

如果有一个接口  
```java
package CommonUsedClass.InnerClass.AnonymousInner;

public interface USB {
    void service();  // 服务函数
}

```
用匿名内部类实现接口
```java
package CommonUsedClass.InnerClass.AnonymousInner;

public class Anonymous {
    public static void main(String[] args) {
        

        // 匿名内部类 相同于创建了一个局部内部类
        new USB(){
            @Override
            public void service() {
                System.out.println("begin from 匿名内部类……");
            }
        }.service();


    }

}

```

## 2 Object类
超类、基类，所有类的直接或间接父类，位于继承树的最顶层。  
任何类，如果没有书写`extends`显示继承某个类，都默认直接继承`Object`类，否则为间接继承。  
* `Object`类中所定义的方法，是所有对象都具备的方法；  
* `Object`类型可以存储任何对象：
    * 作为参数，可接受任何对象；
    * 作为返回值，可返回任何对象。  
  
### 2.1 常用方法
* 类<?> getClass() 
  返回此 Object的运行时类。  
*   int hashCode() 
   返回对象的哈希码值。哈希值根据`对象的地址`或`字符串`或`数字`使用`hash`算法计算出来的
   `int`类型的数值。一般情况下相同对象返回相同哈希码。  
*   void notify() 
   唤醒正在等待对象监视器的单个线程。  
*   String toString() 
   返回对象的字符串表示形式。  
 *  void wait() 
   导致当前线程等待，直到另一个线程调用该对象的 notify()方法或 notifyAll()方法。  
* boolean equals(Object obj) 
  指示一些其他对象是否等于此对象。比较两个对象的地址。  
* protected void finalize() 
  当对象被判定为垃圾对象时，由`jvm`自动调用此方法，用以标记垃圾对象，进入回收队列。
    * 垃圾对象：没有有效引用指向该对象时，为垃圾对象；
    * 垃圾回收：由`GC`销毁垃圾对象，释放数据存储空间；  
    * 自动回收机制：`JVM`的内存耗尽，一次性回收所有垃圾对象；  
    * 手动回收机制：使用`System.gc();`，通知`JVM`执行垃圾回收。  
## 3 包装类
基本数据类型所对应的**引用数据类型**。   
八大基本数据类型的数据是存储在内存空间的栈空间。栈里面存的是地址，引用类型的数据（对象）存储在堆空间。
包装类属于引用数据类型，默认值是`null`。  
包装类对应关系：  
基本数据类型     包装类型  
byte --> Byte  
short --> Short  
int --> Integer  
long --> Long  
float --> Float  
double --> Double  
boolean --> Boolean  
char --> Character  
### 3.1 类型转换（装箱、拆箱）
装箱：把基本类型转为引用类型：将存储在栈内存的基本数据类型  -->  转换为存储在堆内存的对象；  
拆箱：把引用类型转为基本类型：将堆里面的对象放到栈里面。  

JDK 1.5之后实现自动装箱。
```java
public class Test {
    public static void main(String[] args) {     
        int age = 18;
        Integer integerAge = age;  // 自动装箱        
        int intAge = integerAge;  // 自动拆箱
    }

}


```  
实际上市自动调用了下面的拆箱、装箱方法。
 
实现装箱：  
* 1、构造函数；  
* 2、调用方法`valueOf()`;// 自动装箱时调用  

实现拆箱：  
 Number类（拆箱）
可以实现引用类型转基本类型。抽象类Number是表示数字值可转换为基本数据类型平台类的超类byte ， double ， float ， int ， long和short 。
* byte byteValue() 
  返回指定号码作为值 byte ，这可能涉及舍入或截断。  
*  abstract double doubleValue() 
  返回指定数字的值为 double ，可能涉及四舍五入。  
*  abstract float floatValue() 
  返回指定数字的值为 float ，可能涉及四舍五入。  
*  abstract int intValue() 
  返回指定号码作为值 int ，这可能涉及舍入或截断。  
* abstract long longValue() 
  返回指定数字的值为 long ，可能涉及四舍五入或截断。  
*  short shortValue() 
  返回指定号码作为值 short ，这可能涉及舍入或截断。 
### 3.2 基本类型与字符串之间的转换
基本类型转字符串：  
* 1、使用`+''`;  
* 2、使用对应包装类的`toString()`方法；  

字符串转基本类型：  
* 1、使用`Integer.parseXXX()`;// 不能包含非数字的字符  
* 2、注意：字符串形式转为Boolean类型，只有"true" --> true，其他都是false
### 3.3 整数缓存区
Java预先创建了256个常用的整数包装类型对象（-128到127）,进入`valueOf()`进行源码分析可知；在实际应用当中，对已创建的对象进行复用。
```java
package CommonUsedClass.InnerClass.AnonymousInner;

public class Test {
    public static void main(String[] args) {


        Integer integer1 = new Integer(100);
        Integer integer2 = new Integer(100);
        System.out.println(integer1 == integer2);  // false
        
        Integer integer3 = Integer.valueOf(100);
        Integer integer4 = 100;  // 自动装箱，实际上调用的就是Integer.valueOf()方法
        System.out.println(integer3 == integer4);  // true

        Integer integer5 = Integer.valueOf(200);
        Integer integer6 = 200;  // 自动装箱，实际上调用的就是Integer.valueOf()方法
        System.out.println(integer5 == integer6);  // true
        
    }

}

```

## 4 String类
* 字符串是常量，创建之后不可改变(重新赋值并不是修改数据，而是重新开辟一片空间，详见`内存分析.png`)；  

* 字符串字面值存储在字符串池（在方法区里面）中，可以共享。  
* `String s = "hello";` 产生一个对象，存储在字符串池中；  
* `String s = new String("hello");`产生两个对象，堆和池中各存储一个,浪费空间。  
 ```java
package CommonUsedClass.StringClass;

public class StringDemo {
    public static void main(String[] args) {
        String name = "hello";
        name = "zhangsan";  // “zhangsan”赋值给name变量，给字符串赋值时，并没有修改数据，而
        //是重新开辟空间
        // 创建新变量时，会去常量池查询是否存在这个字符串，如果没有的话就创建，有的话直接指向
        String name2 = "zhangsan";  // 两个变量指向字符串池中的同一片空间
        String school = new String("CSU");  // school存储的是堆里面的地址
        String school2 = new String("CSU");
        System.out.println(school == school2);  // false，比的是地址，指向堆里面的不同的地址

        System.out.println(school.equals(school2));  // true，比的不再是地址，比的是数据

    }
}

```  
  
### 4.1 String常用函数
  * char charAt(int index) 
    返回 char指定索引处的值。  
*  String concat(String str) 
  将指定的字符串连接到该字符串的末尾。  
*  boolean contains(CharSequence s) 
  当且仅当此字符串包含指定的char值序列时才返回true。  
 * boolean endsWith(String suffix) 
  测试此字符串是否以指定的后缀结尾。  
*  byte[] getBytes() 
  使用平台的默认字符集将此 String编码为字节序列，将结果存储到新的字节数组中。  
 * int indexOf(String str) 
  返回指定子字符串第一次出现的字符串内的索引。  
 * int lastIndexOf(int ch) 
  返回指定字符的最后一次出现的字符串中的索引。  
 * String replace(char oldChar, char newChar) 
  返回从替换所有出现的导致一个字符串 oldChar在此字符串 newChar 。  
 * boolean startsWith(String prefix) 
  测试此字符串是否以指定的前缀开头。  
 * String substring(int beginIndex) 
  返回一个字符串，该字符串是此字符串的子字符串。  
* String trim() 
返回一个字符串，其值为此字符串，并删除任何前导和尾随空格。  
* String toUpperCase(Locale locale) 
  将所有在此字符 String使用给定的规则，大写 Locale 。  
*  String toLowerCase() 
  将所有在此字符 String使用默认语言环境的规则，以小写。  
*  char[] toCharArray() 
  将此字符串转换为新的字符数组。  
* String[] split(String regex) 
  将此字符串分割为给定的 regular expression的匹配。  
*  boolean equalsIgnoreCase(String anotherString) 
  将此 String与其他 String比较，忽略大小写注意事项。 
* int compareTo(String anotherString) 
  按字典顺序比较两个字符串（如果是形如"abc".compareTo("abcxyz")，结果为-3，否则按顺序比字符，那么比的是长度）。 
  
### 4.2 可变字符串
String的不可变性导致可能出现一些无用的字符串、每次操作的时候都会产生新的字符串导致效率低。  

为了提高效率、节省内存，于是有了StringBuffer、StringBuilder。  

StringBuffer 线程安全，相对StringBuilder运行效率低，可变的字符序列。字符串缓冲区就像一个String ，但可以修改。 在任何时间点，
它包含一些特定的字符序列，但可以通过某些方法调用来更改序列的长度和内容。   
* StringBuffer append(char c) 
  将 char参数的字符串表示附加到此序列。  
*  StringBuffer insert(int offset, char c) 
  在此序列中插入 char参数的字符串表示形式。  
*  StringBuffer replace(int start, int end, String str) 
  用指定的String中的字符替换此序列的子字符串中的 String 。  
* StringBuffer delete(int start, int end) 
  删除此序列的子字符串中的字符。  

StringBuilder 一个可变的字符序列。 此类提供与StringBuffer的API，但不保证同步，运行速度高，线程不安全。

## 5 BigDecimal类
由于精度要求。
```java
public class StringBufferAndBuilder {
    public static void main(String[] args) {
        double r = (1.4-0.5)/0.9;
        System.out.println(r);  // 0.999999999
    }
}
```
 
 位置：java.math  
 作用：精确计算浮点数；  
 创建方式： `BigDecimal bigDecimal = new BigDecimal("1.4");`注意创建的时候用字符串，因为字符串是最准确的  
 运算  
 * 加法：add(BigDecimal augend) 
      返回 BigDecimal ，其值是 (this + augend) ，其标为 max(this.scale(), augend.scale()) 。 
 * 减法：BigDecimal subtract(BigDecimal subtrahend) 
      返回 BigDecimal ，其值是 (this - subtrahend) ，其标为 max(this.scale(), subtrahend.scale()) 。  
 * 乘法： BigDecimal multiply(BigDecimal multiplicand) 
       返回 BigDecimal ，其值是 (this × multiplicand)，其标为 (this.scale() + multiplicand.scale()) 。  
 * 除法：BigDecimal divide(BigDecimal divisor) 
      返回BigDecimal ，其值为(this / divisor) ，优先级为(this.scale() - divisor.scale()) ; 如果不能表示确切的商（因为它具有非终止的十进制扩展），则抛出一个ArithmeticException 。       
    * 注意：除法中，如果除不净需要指定保留几位小数，并指定四舍五入法`ROUND_HALF_UP`，否则会报错     
 
## 6 Date
 Date表示特定的瞬间，精确到毫秒，Data类中的大部分方法都已经被Calendar类中的方法所取代。  
 ```java
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        
        Date date = new Date();
        System.out.println(date.toString());
        long time = date.getTime();  // ms单位
        System.out.println(time);
    }
}

```
 
## 7 Calendar
推荐使用的日期类，构造方法受保护，用`Calendar.getInstance();`创建对象。  
```java

import java.util.Calendar;


public class Test {
    public static void main(String[] args) {        

        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        int year = instance.get(Calendar.YEAR);// 获取时间信息
        instance.set(Calendar.YEAR,2019);  // 修改时间
        instance.add(Calendar.MONTH,3);  // 在当前基础上加amount的年、月、日
    }
}
```
## 8 SimpleDateFormat类
SimpleDateFormat是一个以与语言环境有关的方式来格式化和解析日期的具体类。  
常用的时间模式字母：  
y：年  
M：月  
d：天数  
H：小时  
m：分钟  
s：秒  
S：毫秒  

使用方法：
* 1、创建对象  
    * 创建SimpleDateFormat对象；  
    * 创建Date对象；
* 2、格式化  
* 3、解析，注意解析的格式要个创建时的格式保持一致。  

`simpleDateFormat.format(date)`

```java

import java.text.SimpleDateFormat;

import java.util.Date;

public class Test {
    public static void main(String[] args) {    

        // 创建对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        // 格式化
        String s = sdf.format(date);
        System.out.println(s);
        // 解析
        try {
            Date date1 = sdf.parse("2020/02/01 00:00:00");  // 解析的格式要跟创建的格式一样
            System.out.println(date1.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

```

## 9 System类
System系统类主要用于获取系统的属性数据和其他操作，构造方法私有。不需要创建对象，静态方法可直接调用。  

常用方法：
* static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) 
  将指定源数组中的数组从指定位置复制到目标数组的指定位置。  
* static long currentTimeMillis() 
  返回当前时间（以毫秒为单位）。  
* static void gc() 
  运行垃圾回收器。  
* static void exit(int status) 
  终止当前运行的Java虚拟机。  

注意事项：
* arraycopy是一个本地方法，一般用C或C++实现，效率高，推荐指数大于`Arrays.copyOf()`。
* currentTimeMillis一般用于程序计时。  
  
字段信息：
* in
  public static final InputStream in“标准”输入流。 该流已经打开，准备提供输入数据。 通常，该流对应于键盘输入或由主机环境或用户指定的另一个输入源。 
* out
  public static final PrintStream out“标准”输出流。 此流已经打开并准备好接受输出数据。 通常，此流对应于显示输出或由主机环境或用户指定的另一个输出目标。 
  对于简单的独立Java应用程序，编写一行输出数据的典型方法是： 
  
       System.out.println(data)
    
  
  