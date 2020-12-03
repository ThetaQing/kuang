@[toc]
# JVM
## 0 面试题
* 1、请你谈谈对JVM的理解

* 2、java8虚拟机和之前的变化更新？

* 3、什么是OOM，什么是栈溢出StackOverFlowError？怎么分析

* 4、JVM的常用调优参数有哪些？

* 5、内存快照如何抓取，怎么分析Dump文件？知道吗？

* 6、谈谈JVM中，类加载器你的认识？
    rt-jar等
    
 * 7、补充：new一个对象的内存分析
##  知识点
### 1、JVM的位置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201094642592.png)

### 2、JVM的体系结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201110719217.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)


### 3、类加载器
	作用：加载Class文件
	记住：类是模板，是抽象的，对象是具体的
	![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201100733417.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
类加载器的分类
	* 1、虚拟机自带的加载器
	* 2、启动类（根）加载器
	* 3、扩展类加载器
	* 4、应用程序（系统）加载器：返回null，java调用不到，C、C++编写的
	```java
	package JVM.ClassLoaderDemo;
	
	/**
	 * @author ThetaQing
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
	
	```
### 4、双亲委派机制
作用：为了保障安全；
运行一个类之前，逐级类加载器向上找 1. AppClassLoader --> 2. EXC --> 3.BOOT，所以定义重名类是无效的。假设BOOT加载器中没有这个类，倒回去找扩展类加载器EXC，如果还没有回到APP类加载器。
过程：
	* 1、 类加载器收到类加载的请求；
	* 2、将这个请求向上委托给父类加载器去完成，一直向上委托，直到启动类加载器；
	* 3、启动类加载器检查是否能够加载当前这个类，能加载就结束，使用当前加载器，否则，抛出异常通知子加载器进行加载；
	* 4、该加载器重复步骤3；
	* 5、都找不到就报Class Not Found。
	```java
	package java.lang
	
	public class String{
	    public String toString(){
	        return "hello";
	    }
	
	    public static void main(String[] args) {
	        String string = new String();
	        string.toString();
	    }
	}
	```
	报错内容：java.lang.String中找不到main方法。
	报错原因：根据双亲委派机制，最终调用的是BOOT中的String类
	```java
	public class Student{
	    @Override
	    public String toString(){
	        return "hello";
	    }
	
	    public static void main(String[] args) {
	        Student student= new Student();
	        student.toString();
	    }
	}
	```
	正常运行
	可以通过`student.getClass().getClassLoader()`进行确认是从哪个加载器中加载的。
### 5、java历史——沙箱安全机制（了解）
 沙箱安全机制是什么：
	> Java安全模型的核心就是Java沙箱（sandbox）。沙箱是一个限制程序运行的环境。沙箱机制就是讲Java代码限定在虚拟机（JVM）特定的运行范围中，并且严格限制代码对本地资源访问，通过这样的措施来保证对代码的有效隔离，防止对本地系统造成破坏。
	
	沙箱主要限制系统资源访问，包括`CPU、内存、文件系统、网络`。
	组成沙箱的基本组件：
	> `字节码校验器(bytecode verifier)`：确保Java类文件遵循Java语言规范，帮助Java程序实现内存保护，但并不是所有的类文件都会经过字节码校验，比如核心类(以java、javax开头的那些)；
	`类装载器(class loader)`：主要实现为双亲委派机制，主要作用有：
	1、防止恶意代码去干涉善意代码（双亲委派机制）；
	2、守护被信任的类库边界；
	3、将代码归入保护域，确定代码可以进行哪些操作。
	`存取控制器（access controller）`：存取控制器可以控制核心API对操作系统的存取权限，而这个控制的策略设定，可以由用户指定。
	`安全管理器（security manager）`：是核心API和操作系统之间的主要接口。实现权限控制，比存取控制器优先级高。
	`安全软件包（security package）`：java.security下的类和扩展包下的类，允许用户为自己的应用增加新的安全特性，包括
1、安全提供者
2、消息摘要
3、数字签名
4、加密
5、鉴别

其他知识点参考
	[java中的安全模型(沙箱机制)](https://blog.csdn.net/qq_30336433/article/details/83268945)
### 6、Native（重点）
**作用**：凡是带了native关键字的，说明java的作用范围达不到，需要调底层C语言的库，扩展JAVA的使用，融合不同的编程语言为Java所用，最初是为了融合C、C++；
**出现的原因**：Java诞生的时候，C、C++横行，想要立足，就必须有调用C、C++的程序
**怎么做到的**：在内存区域中，专门开辟了一块标记区域（本地方法栈），登记native方法，会在最终执行的时候加载本地方法库中的本地方法。
```java
package JVM.NativeDemo;

/**
 * @author ThetaQing
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

        },"my thread name").start();  // 调用了start0()本地方法
    }
}

```
### 7、PC寄存器（了解）
程序计数器（Program Counter Register）
每个线程都有一个程序计数器，是**线程私有**的，就是一个指针，指向方法区中的方法字节码（用来存储指向下一条指令的地址，即即将要执行的指令代码），在执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不计。
### 8、方法区
方法区是一种特殊的堆（参见永久区），被所有线程共享，所有字段和方法字节码，以及一些特殊方法，如构造函数、接口代码也在此定义，简单地说，所有定义的方法的信息都保存在该区域，**此区域属于共享区间**。
（要求背诵，重点）==静态变量static、常量final、类信息Class（构造方法、接口定义）、运行时的常量池存在方法区中，但是实例变量存在堆内存中，和方法区无关。==
### 9、栈
栈是一种数据结构，先进后出，后进先出 FILO
队列：先进先出，后进后出 FIFO
栈：主管程序的运行，生命周期和线程同步，线程结束，栈内存释放（main线程结束，mian方法从栈中弹出），==不存在垃圾回收问题==。
**栈里面存放的内容**： 8大基本类型、对象引用、实例的方法。
栈运行原理：栈帧
程序正在执行的方法一定在栈的顶部。栈满了就会抛出`StackOverflowError`。
**栈+堆+方法区的交互关系**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201161121883.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
待解决问题
 * [ ] 栈里面具体存的东西，具体是怎么存的；
 * [ ] 画出一个对象实例化在内存中的过程；

栈溢出示例：

	package JVM.StackOverDemo;
	
	/**
	 * @author ThetaQing
	 * 栈溢出
	 */
	public class StackPverTest {
	    public static void main(String[] args) {
	        new StackPverTest().a();
	    }
	    public void a(){
	        b();
	    }
	    public void b(){
	        a();
	    }
	}
	
栈内存的示意图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201154642307.png)

### 10、三种JVM

 - Sun公司的`HotSpot`，查询命令`java -version`。
 -  BEA JRockit 
 - IBM J9VM
我们现在用的都是HotSpot。

### 11、堆Heap
一个JVM只有一个堆内存，堆内存的大小是可以调节的。
类加载器读取了类文件后，一般会把什么东西放到堆中？类的实例、方法、常量、变量，保存我们所有引用类型的真实对象。
堆内存中分为三个区域
* 新生区（伊甸园区）Young/New
* 养老区old
* 永久区perm
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201165639572.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

GC垃圾回收，主要是在伊甸园区和养老区。内存满了，报OOM，堆内存不够。`java.lang.OutOfMemoryError: Java heap space`
JDK8以后，永久存储区改名为（元空间）。

### 12、新生区

 - 类诞生和成长甚至死亡的地方。
 - 伊甸园区：所有的对象都是在伊甸园区`new`出来的
 - 幸存者区（0区、1区）
 
经过研究，99%的对象都是临时对象。正常情况下很少有进养老区的对象，所以很少出现OOM。
**OOM一般处理**
* 1、尝试扩大堆内存`-Xms1024m -Xmx1024m -XX:+PrintGCDetails`
>* -Xms1024m  设置初始化内存分配大小
> * -Xmx1024m  设置最大分配内存
> *  -XX:+PrintGCDetails  打印GC细节
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201174332443.png)![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201174420242.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
 * 2、如果还OOM，分析内存，看一下哪个地方出现了问题（专业工具）

#### 如果出现了OOM，该如何排除
* 能够看到代码第几行出错是最理想的情况：因此需要一些内存快照分析工具，MAT（早年针对eclipse），JProfiler
* Debug

MAT、JProfiler作用：
* 分析Dump内存份文件，快速定位内存泄露；
* 获得堆中的数据；
* 获得大的对象。

排错过程：
* 1、先Dump文件：熟记JVM优化命令，当出现内存溢出时DUMP:`-XX:+HeapDumpOnOutOfMemoryError`
> `-XX:+HeapDumpOnOutOfMemoryError`  当出现内存溢出时DUMP
> 类似的可以把`OutOfMemory`改成其他错误定位问题。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201201722985.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201201746504.png)
* 2、使用JProfiler打开该文件（和src同级的目录下），利用JProfile分析。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201201203181.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
* 2.1 先查看大对象，找到出错的原因
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201201533828.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
* 2.2 从线程中定位代码行数。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201201435901.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

### 13、老年区
可以用` -XX:+PrintGCDetails`查看垃圾回收过程
示例：一个会OOM的程序
```
package JVM.HeapDemo;
import java.util.Random;
/**
 * @author ThetaQing
 * 堆溢出测试
 */
public class HeapOverTest {
    public static void main(String[] args) {
        String str = "hello";

        while(true){
            str += str + new Random().nextInt(888888888)+new Random().nextInt(999999999);

        }
    }
}

```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201180146291.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

### 14、永久区
这个区域常驻内存，用来存放JDK自身携带的Class对象，Interface元数据，存储的是Java运行时的一些环境或类信息，==这个区域不存在垃圾回收==。在JVM关闭时，释放这个区域的内存。
一个启动类，加载了大量的第三方jar包，Tomcat部署了太多的应用，或大量动态生成的反射类，如果不断被加载，直到内存满就会出现OOM。
* jdk1.6之前：永久代，常量池是在方法区；
* jdk1.7：永久代慢慢退化，去永久化，常量池在堆中；
* jdk1.8后：无永久代，常量池在元空间。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201175208766.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
元空间逻辑上存在但物理上不存在，利用`-Xms1024m -Xmx1024m -XX:+PrintGCDetails`优化堆空间后：
```
package JVM.HeapDemo;

/**
 * @author ThetaQing
 * 测试虚拟机内存
 * 默认情况下：分配的总内存是电脑内存的1/4，而初始化的内存是电脑内存的1/64.
 * OOM:
 * 1、尝试扩大堆内存看结果  -Xms1024m -Xmx1024m -XX:+PrintGCDetails
 * 2、如果还OOM，分析内存，看一下哪个地方出现了问题（专业工具）
 *
 * -Xms1024m -Xmx1024m -XX:+PrintGCDetails 扩充堆内存后，
 * PSYoungGen  305664K
 * ParOldGen   699392K
 * 共 981.5MB 等于总分配的空间大小
 * 所以元空间逻辑上存在，物理上不存在
 */
public class HeapMemoryTest {
    public static void main(String[] args) {
        // 返回虚拟机试图使用的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        // 返回jvm的总内存
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 默认1799.5MB左右，电脑内存7.9G，约1/4
        System.out.println("max memory:" + maxMemory + "字节\t" + (maxMemory/(double)1024/1024) + "MB");
        // 默认123MB左右，电脑内存7.9G，约1/64
        System.out.println("total memory:" + totalMemory + "字节\t" + (totalMemory/(double)1024/1024) + "MB");

        //

    }
}

```

### 15、堆内存调优
调优位置
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201201722985.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201201201746504.png)
常用命令
> * `-XX:+HeapDumpOnOutOfMemoryError`  当出现内存溢出时DUMP
> * 类似的可以把`OutOfMemory`改成其他错误定位问题。
> * `-Xms1024m`  设置初始化内存分配大小
> * `-Xmx1024m`  设置最大分配内存
> *  `-XX:+PrintGCDetails`  打印GC细节
>*  `-XX:MaxTenuringThreshold=3`  控制新生代需要经历多少次GC晋升到老年代中的最大阈值，默认值15
>
### 16、GC

#### 常用算法
* 引用计数法
计数器本身也有消耗。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020120219394467.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

* 复制算法
1、每次GC都会将Eden中的对象复制到幸存`To`区；一旦Eden被GC之后就变为空；
2、总是将`From`区的对象复制到`To`区，然后`From`和`To`区的`身份发生转换`；
3、谁空谁是`To`，要保证`To`区在GC之后总是空的；
4、当一个对象经历了15次（默认值）GC之后依然存在，该对象进入老年代。可以通过设置`-XX:MaxTenuringThreshold=num`进行控制。 
5、针对的主要是新生区的对象。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201202195811228.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

好处
> 没有内存的碎片；

坏处
> 浪费内存空间，多了一半空间永远是空的，即`To`区。假设对象100%成活（极端情况），复制成本额巨大。

复制算法最佳使用场景：对象存活度较低的时候，即新生区。

* 标记清除算法
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201202201327557.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

好处
> 不需要额外的空间

缺点
> 两次扫描严重浪费时间，会产生内存碎片

* 标记清除压缩法
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201202201724570.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

好处
> 防止内存碎片的产生
> 
坏处
> 三次扫描时间

* 继续优化的思路
多次标记清除算法GC之后再进行标记清除压缩算法

#### 小结
* 内存效率
复制算法>标记清除算法>标记压缩算法（时间复杂度）
* 内存整齐度
复制算法 = 标记压缩算法 > 标记清除算法 （内存碎片问题）
* 内存利用率
标记压缩算法 = 标记清除算法 > 复制算法

==没有最好的算法，只有最合适的算法==，所以GC也被称之为**分代收集算法**。
年轻代：
* 存活率低
* 采用复制算法

老年代：
* 区域大、存活率高
* 标记清除（内存碎片不是太多） + 标记压缩混合实现

JVM调优：标记清除和标记压缩怎么进行混合。

### 17、JMM
* 1、什么是JMM？
Java Memory Model，Java内存模型
* 2、它是用来做什么的？
作用：是一种缓存一致性协议，用于定义数据读写的**规则**。
JMM定义了线程工作内存(==每个线程都有自己的工作区域，是从主内存中拷贝的==)和主内存之间的抽象关系：线程之间的共享变量存储在主内存（Main Memory）中，每个线程都有一个私有的本地内存（Local Memory）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201202210432358.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)

解决共享对象可见性这个问题：volatile关键字，保证一致性，及时刷新到主内存。
* 3、它该如何学习？
先弄明白是什么（规则是什么？）
> JMM 在执行前面介绍 8 种基本操作时，为了保证内存间数据一致性，JMM 中规定需要满足以下**规则**：
>* 规则 1：如果要把一个变量从主内存中复制到工作内存，就需要按顺序的执行 read 和 load 操作，如果把变量从工作内存中同步回主内存中，就要按顺序的执行 store 和 write 操作。
但 Java 内存模型只要求上述操作必须按顺序执行，而没有保证必须是连续执行。
>* 规则 2：不允许 read 和 load、store 和 write 操作之一单独出现。
>* 规则 3：不允许一个线程丢弃它的最近 assign 的操作，即变量在工作内存中改变了之后必须同步到主内存中。
>* 规则 4：不允许一个线程无原因的（没有发生过任何 assign 操作）把数据从工作内存同步回主内存中。
>* 规则 5：一个新的变量只能在主内存中诞生，不允许在工作内存中直接使用一个未被初始化（load 或 assign ）的变量。
即对一个变量实施 use 和 store 操作之前，必须先执行过了 load 或 assign 操作。
>* 规则 6：一个变量在同一个时刻只允许一条线程对其进行 lock 操作，但 lock 操作可以被同一条线程重复执行多次，多次执行 lock 后，只有执行相同次数的 unlock 操作，变量才会被解锁。所以 lock 和 unlock 必须成对出现。
>* 规则 7：如果对一个变量执行 lock 操作，将会清空工作内存中此变量的值，在执行引擎使用这个变量前需要重新执行 load 或 assign 操作初始化变量的值。
>* 规则 8：如果一个变量事先没有被 lock 操作锁定，则不允许对它执行 unlock 操作；也不允许去 unlock 一个被其他线程锁定的变量。
>* 规则 9：对一个变量执行 unlock 操作之前，必须先把此变量同步到主内存中（执行 store 和 write 操作）。
>
>JMM的这些规则确定哪里操作是线程安全的，哪些操作时线程不安全的。

#### 重点
再看相关面试题确定重点

堆、栈、GC等属于Java内存结构，Java内存模型（JMM）实际上一般问到Java内存模型都是想问多线程，Java并发相关的问题。  
##### 1、JMM定义了什么  
整个Java内存模型实际上是围绕着三个特征建立起来的。分别是：原子性，可见性，有序性，即Java并发的基础。
> 原子性：指的是一个操作是不可分割，不可中断的，一个线程在执行时不会被其他线程干扰。
```
int i = 2;  // 基本类型赋值操作，必定是原子性操作。
int j = i;  // 先读取i的值，再赋值到j，两步操作，不能保证原子性。
i++;  // 先读取i的值，再+1，最后赋值到i，三步操作了，不能保证原子性。
i = i + 1;  // 等效i++
```
JMM只能保证基本的原子性，如果要保证一个代码块的原子性，添加synchronized关键字，在 synchronized 块之间的操作都是原子性的。

> 可见性：指当一个线程修改共享变量的值，其他线程能够立即知道被修改了。利用volatile关键字来提供可见性的，final和synchronized也能实现可见性。

> 有序性:可以使用synchronized或者volatile保证多线程之间操作的有序性.实现原理有些区别：
>* volatile关键字是使用内存屏障达到禁止指令重排序，以保证有序性。
> * synchronized的原理是，一个线程lock之后，必须unlock后，其他线程才可以重新lock，使得被synchronized包住的代码块在多线程之间是串行执行的。
    
#### 八种内存交互操作
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201202222745622.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
* lock(锁定)，作用于主内存中的变量，把变量标识为线程独占的状态。
* read(读取)，作用于主内存的变量，把变量的值从主内存传输到线程的工作内存中，以便下一步的load操作使用。
* load(加载)，作用于工作内存的变量，把read操作主存的变量放入到工作内存的变量副本中。
* use(使用)，作用于工作内存的变量，把工作内存中的变量传输到执行引擎，每当虚拟机遇到一个需要使用到变量的值的字节码指令时将会执行这个操作。
* assign(赋值)，作用于工作内存的变量，它把一个从执行引擎中接受到的值赋值给工作内存的变量副本中，每当虚拟机遇到一个给变量赋值的字节码指令时将会执行这个操作。
* store(存储)，作用于工作内存的变量，它把一个从工作内存中一个变量的值传送到主内存中，以便后续的write使用。
* write(写入)：作用于主内存中的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中。
* unlock(解锁)：作用于主内存的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定。

#### volatile关键字
主要的作用包括两点：

1、保证线程间变量的可见性。
2、禁止CPU进行指令重排序。(使用内存屏障达到禁止指令重排序，以保证有序性)

volatile修饰的变量，当一个线程改变了该变量的值，其他线程是立即可见的。普通变量则需要重新读取才能获得最新值。volatile保证可见性的流程大概就是这个一个过程：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201203090943630.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
#### volatile一定能保证线程安全吗
volatile不能一定能保证线程安全。
```java
/**
 * volatile 线程不一定安全
 **/
public class VolatileTest extends Thread {

    private static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        Vector<Thread> threads = new Vector<>();
        for (int i = 0; i < 100; i++) {
            VolatileTest thread = new VolatileTest();
            threads.add(thread);
            thread.start();
        }
        //等待子线程全部完成
        for (Thread thread : threads) {
            thread.join();
        }
        //输出结果，正确结果应该是1000，实际却是984
        System.out.println(count);//984
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                //休眠500毫秒
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}

```
可见性不能保证操作的原子性，`count++`不是原子性操作，会当做三步，先读取count的值，然后+1，最后赋值回去count变量。需要保证线程安全的话，需要使用`synchronized`关键字或者`lock`锁，给`count++`这段代码上锁。
`private static synchronized void add() {
    count++;
}`
#### 禁止指令重排序
不管怎么重排序，（单线程）程序的执行结果不能被改变。为了使指令更加符合CPU的执行特性，最大限度的发挥机器的性能，提高程序的执行效率，只要程序的最终结果与它顺序化情况的结果相等，那么指令的执行顺序可以与代码逻辑顺序不一致，这个过程就叫做**指令的重排序**。
重排序的种类分为三种，分别是：编译器重排序，指令级并行的重排序，内存系统重排序。整个过程如下所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020120309184015.png)
指令重排序在单线程是没有问题的，不会影响执行结果，而且还提高了性能。但是在多线程的环境下就不能保证一定不会影响执行结果了。

所以在多线程环境下，就需要禁止指令重排序。
volatile关键字禁止指令重排序有两层意思：

* 当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见，在其后面的操作肯定还没有进行。
* 在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句放到其前面执行。
```
private static int a;//非volatile修饰变量
private static int b;//非volatile修饰变量
private static volatile int k;//volatile修饰变量

private void hello() {
    a = 1;  //语句1
    b = 2;  //语句2
    k = 3;  //语句3
    a = 4;  //语句4
    b = 5;  //语句5
    //以下省略...
}
```
变量a，b是非volatile修饰的变量，k则使用volatile修饰。所以语句3不能放在语句1、2前，也不能放在语句4、5后。但是语句1、2的顺序是不能保证的，同理，语句4、5也不能保证顺序。

并且，执行到语句3的时候，语句1，2是肯定执行完毕的，而且语句1,2的执行结果对于语句3,4,5是可见的。
#### volatile禁止指令重排序的原理是什么
首先要讲一下内存屏障，内存屏障可以分为以下几类：

* LoadLoad 屏障：对于这样的语句Load1，LoadLoad，Load2。在Load2及后续读取操作要读取的数据被访问前，保证Load1要读取的数据被读取完毕。
* StoreStore屏障：对于这样的语句Store1， StoreStore， Store2，在Store2及后续写入操作执行前，保证Store1的写入操作对其它处理器可见。
* LoadStore 屏障：对于这样的语句Load1， LoadStore，Store2，在Store2及后续写入操作被刷出前，保证Load1要读取的数据被读取完毕。
* StoreLoad 屏障：对于这样的语句Store1， StoreLoad，Load2，在Load2及后续所有读取操作执行前，保证Store1的写入对所有处理器可见。

在每个volatile读操作后插入LoadLoad屏障，在读操作后插入LoadStore屏障。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201203092932886.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
在每个volatile写操作的前面插入一个StoreStore屏障，后面插入一个SotreLoad屏障。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201203093011639.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoZXJhX3Fpbmc=,size_16,color_FFFFFF,t_70)
重点是Java内存模型(JMM)的工作方式，三大特征，还有volatile关键字。为什么喜欢问volatile关键字呢，因为volatile关键字可以扯出很多东西，比如可见性，有序性，还有内存屏障等等。
