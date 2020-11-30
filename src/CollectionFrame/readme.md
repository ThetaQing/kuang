[toc]

# 集合框架
## 1 概述
概念：对象的容器，定义了对多个对象进行操作的常用方法，可类似于实现数组的功能。  

和数组的区别：
* 1、数组长度固定，集合长度不固定；  
* 2、 数组可以存储基本类型和引用类型，集合只能存储引用类型。(所以添加int对象时存在自动装箱操作，删除int对象时要把
基本类型转换为包装类new Integer(value)或Object (value)，否则就是按索引删除)  
注意：  
* `remove()`方法调用之前会先调用`hashCode()`，返回`true`之后再调用`equals()`，如果返回为`true`才会删除，所以如果需要删除一个`new`出来的对象，必须重写`equals()`,自定义两个对象相等
满足的条件。  
    * 重写时分三步：判断是否是本身；判断是否为空；判断是否是同一类型。
    ```
        @Override
        /**
         * id作为唯一识别号
         */
    
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }
            if(obj == null){
                return false;
            }
            if(obj instanceof Person){
                Person person = (Person)obj;
                return this.id == person.id;
            }
            return false;
        }
    ```  

##2 Collection父接口
特点：代表一组任意类型的对象，无序、无下标、不能重复。  
常用方法： 
* boolean add(E e) 
  确保此集合包含指定的元素（可选操作）。  
*  boolean addAll(Collection<? extends E> c) 
  将指定集合中的所有元素添加到此集合（可选操作）。  
*  void clear() 
  从此集合中删除所有元素（可选操作）。  
*  boolean contains(Object o) 
  如果此集合包含指定的元素，则返回 true 。  
*  boolean containsAll(Collection<?> c) 
  如果此集合包含指定 集合中的所有元素，则返回true。  
*  boolean equals(Object o) 
  将指定的对象与此集合进行比较以获得相等性。  
*  int hashCode() 
  返回此集合的哈希码值。  
*  boolean isEmpty() 
  如果此集合不包含元素，则返回 true 。  
 * Iterator<E> iterator() 
  返回此集合中的元素的迭代器。  
*  default Stream<E> parallelStream() 
  返回可能并行的 Stream与此集合作为其来源。  
 * boolean remove(Object o) 
  从该集合中删除指定元素的单个实例（如果存在）（可选操作）。  
*  boolean removeAll(Collection<?> c) 
  删除指定集合中包含的所有此集合的元素（可选操作）。  
*  default boolean removeIf(Predicate<? super E> filter) 
  删除满足给定谓词的此集合的所有元素。  
*  boolean retainAll(Collection<?> c) 
  仅保留此集合中包含在指定集合中的元素（可选操作）。  
 * int size() 
  返回此集合中的元素数。  
 * default Spliterator<E> spliterator() 
  创建一个Spliterator在这个集合中的元素。  
 * default Stream<E> stream() 
  返回以此集合作为源的顺序 Stream 。  
 * Object[] toArray() 
  返回一个包含此集合中所有元素的数组。  
 * `<T> T[] toArray(T[] a) ` 返回包含此集合中所有元素的数组; 返回的数组的运行时类型是指定数组的运行时类型;
### 2.1 遍历集合
* 1、使用增强`for`循环；  
* 2、使用迭代器 Iterator<E> iterator() ;    
    * 1. hasNext():该迭代器是否存在下一个元素；  
    * 2. next() 返回下一个元素；  
    * 3. remove() 删除当前迭代器。注意这里只能使用迭代器的删除操作，不能使用集合其他函数删除，否则报并发删除错误。
```java
package CollectionFrame;

import java.util.ArrayList;
import java.util.Iterator;

public class CollcetionDemo {
    public static  void test(){
        //  创建
        ArrayList<Object> arrayList = new ArrayList<>();
        // 添加
        arrayList.add("apple");
        arrayList.add("apple");
        arrayList.add("apple");
        arrayList.add("apple");
        // 查询
        System.out.println(arrayList.size());
        // 删除
        arrayList.remove("apple");
        System.out.println(arrayList.size());
        // 清空
        arrayList.clear();

        arrayList.add("苹果");
        arrayList.add("香蕉");
        arrayList.add("橘子");
        arrayList.add("芒果");

        //  遍历 增强for
        for (Object o : arrayList) {
            System.out.println(o);
        }
        //  遍历 迭代器，用来迭代集合的一种方式
        Iterator<Object> iterator = arrayList.iterator();
        while(iterator.hasNext()){
            String s = (String)iterator.next();
            System.out.println(s);
            iterator.remove();
        }
        // 判断
        System.out.println(arrayList.contains("西瓜"));
    }

}
```
     
          
 

### 2.2 集合元素为引用类的对象
注意`remove()`删除的只是指针，不是对象本身。  
```java
package CollectionFrame;

import AnnotationAndReflection.Application.User;

import java.util.ArrayList;
import java.util.Iterator;

public class CollcetionDemo {
    // 自定义类的集合测试
    public static void testCustomizeClass(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("zhangsan",1,18));
        users.add(new User("zhangsan",2,14));
        users.add(new User("zhangsan",3,12));
        System.out.println(users.size());
        System.out.println(users.toString());
        User lisi = new User("lisi", 4, 10);
        users.remove(lisi);
        System.out.println(users.size());
        users.remove(lisi);
        users.remove(2);  // 删除的只是指针，不是对象
        System.out.println(lisi.toString());
        System.out.println(users.size());
    }
}

```

## 3 List接口
特点：有序、有下标、元素可以重复  
方法：
* void add(int index, E element) 
  将指定的元素插入此列表中的指定位置（可选操作）。 
*  boolean addAll(int index, Collection<? extends E> c) 
  将指定集合中的所有元素插入到此列表中的指定位置（可选操作）。  
*  Object get(int index) 
  返回此列表中指定位置的元素。  
*  List<E> subList(int fromIndex, int toIndex) 
  返回此列表中指定的 fromIndex （含）和 toIndex之间的视图。 含头不含尾。 
* ListIterator<E> listIterator() 
  返回列表中的列表迭代器（按适当的顺序）。  
*  ListIterator<E> listIterator(int index) 
  从列表中的指定位置开始，返回列表中的元素（按正确顺序）的列表迭代器。  
* int indexOf(Object o) 
  返回此列表中指定元素的第一次出现的索引，如果此列表不包含元素，则返回-1。 
    
### 3.1 列表迭代器 listIterator
用于允许程序员沿任一方向遍历列表的列表的迭代器，在迭代期间修改列表，并获取列表中迭代器的当前位置。  
和普通迭代器的区别：可以向前遍历或向后遍历，也能删除、添加、修改元素。  
注意向后遍历的时候要先把指针移动到后面，默认一开始指针指向表头。

常用方法：
* void add(E e) 
  将指定的元素插入列表（可选操作）。  
*  boolean hasNext() 
  返回 true如果遍历正向列表，列表迭代器有多个元素。  
 * boolean hasPrevious() 
  返回 true如果遍历反向列表，列表迭代器有多个元素。  
*  E next() 
  返回列表中的下一个元素，并且前进光标位置。  
*  int nextIndex() 
  返回随后调用 next()返回的元素的索引。  
*  E previous() 
  返回列表中的上一个元素，并向后移动光标位置。  
*  int previousIndex() 
  返回由后续调用 previous()返回的元素的索引。  
*  void remove() 
  从列表中删除由 next()或 previous()返回的最后一个元素（可选操作）。  
*  void set(E e) 
  用 指定的元素替换由 next()或 previous()返回的最后一个元素（可选操作）。 

```java
package CollectionFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListDemo {
    public static void test(){
        // 创建集合对象
        List list = new ArrayList<>();
        // 添加元素
        list.add("苹果");
        list.add("苹果");
        list.add(0,"香蕉");
        list.add(0,"橘子");
        list.add("苹果");

        // 遍历
        // 使用for
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("==============================");
        // 增强for， 略

        // 普通迭代器 iterator()，略

        // 列表迭代器 listIterator
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        System.out.println("==============================");

        while(listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }
    }


}

``` 

### 3.2 集合存储包装类
集合不能存储基本类型，只能存储引用类型，所以存储int类型的对象时会进行自动装箱，删除操作时要注意转换为包装类，否则就是按索引删除。
```java

import java.util.ArrayList;
import java.util.List;


public class ListDemo {
    // 集合不能存储基本类型，所以存在自动装箱
    public static void testNum(){
        List list = new ArrayList<>();
        // 自动装箱
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);

        System.out.println(list.toString());

        list.remove(new Integer(20));  // 两种删除方式，不能直接使用list.remove(30);这是下标索引删除
        list.remove((Object)30);
        System.out.println(list.toString());
    }
}
```
### 3.3 List实现类
注意自定义类重写equal方法要点：  
 * 1、判断比较的两个对象是否是同一对象`if(this==obj)`；  
 * 2、判断比较的对象是否为空`if(obj==null)`；
 * 3、判断比较的对象是否是当前类的类型，`if(obj instanceof ClassName)`，如果是的话，类型强转并自定义等于指标进行判断；
 * 4、其他情况返回`false`。  
 
ArrayList(重点)  
* 数组结构实现，必须开辟连续空间。查询快，增删慢。  
* JDK1.2版本，运行效率快，线程不安全。  
* 源码分析：
    * 默认容量大小：`DEFAULT_CAPACITY=10`；
        * 如果没有添加任何元素，size为0，容量也为0.
        * 添加一个元素之后，size为1，容量为10；
        * 当添加了10个元素之后，再次添加新的元素，size为添加之前的元素个数，容量为size+size/2。即添加第11
        个元素时，容量变成了15.所以之后每一次添加，容量都变为原来的1.5倍。
    * 存放元素的数组：`elementData`
    * 实际元素个数：`size`
    * 添加元素add()
    ```
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
  
    private void ensureCapacityInternal(int minCapacity) {
          ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }
    private void ensureExplicitCapacity(int minCapacity) {
          modCount++;
    
          // overflow-conscious code
          if (minCapacity - elementData.length > 0)
              grow(minCapacity);
    }
    private void grow(int minCapacity) {
          // overflow-conscious code
          int oldCapacity = elementData.length;
          int newCapacity = oldCapacity + (oldCapacity >> 1);
          if (newCapacity - minCapacity < 0)
              newCapacity = minCapacity;
          if (newCapacity - MAX_ARRAY_SIZE > 0)
              newCapacity = hugeCapacity(minCapacity);
          // minCapacity is usually close to size, so this is a win:
          elementData = Arrays.copyOf(elementData, newCapacity);
    }
    ```  
  
Vector：  
* 数组结构实现，查询快，增删慢；  
* JDK1.0版本出现，相比ArrayList运行效率慢，线程安全。  
特殊方法：
* Enumeration<E> elements() 
  返回此向量的组件的枚举。  
  

LinkedList：  
* 链表结构实现，无需开辟连续空间。增删快，查询慢；  
* 源码分析：  
    * 属性
        * 集合大小：size；
        * 头结点：first；
        * 尾结点：last。
    * 方法
        
    * 类
        * Node:双指针节点。
        
## 4 泛型
Java泛型是JDK1.5中引进的一个新特性，其本质是参数化类型，把类型作为参数传递。常见形式有泛型
类、泛型接口、泛型方法。  
语法：`<T,……>`  T称为类型占位符，可用其他字母表示，仅表示一种引用类型。  
好处： 提高代码的重用性；防止类型转换异常，提高代码的安全性。  
注意：  
* 1、在泛型类或泛型方法中可以创建T对象的引用，但是不能`new T()`，因为不确定T的类型，
无法确保T的构造方法一定能用。
* 2、传递的泛型实际类型只能是引用类型。
* 3、不同泛型对象之间不能相互赋值
### 4.1 泛型类定义
语法：`类名 <T>`  
T是类型占位符，表示一种引用类型，如果编写多个使用逗号隔开。  
注意：1、泛型只能是引用类型；2、不同泛型对象之间不能相互赋值
```java

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
```
### 4.2 泛型接口
语法：`接口名<T>`  
注意，接口中静态常量默认有：`public static final`修饰。不能创建泛型静态常量；
```java
public interface GenericsInterface<T> {
    String name = "zhangsan";
    T server(T t);


}
```
接口的实现类可以确定泛型的类型或继续使用泛型。  
明确泛型的类型：  
```java
// 泛型接口实现类
public class GenInterfaceImplement implements GenericsInterface<String>{
    @Override
    public String server(String s) {
        System.out.println(s);
        return s;
    }
}
```
不指定泛型的类型：
```java
// 第一个T是泛型类的T，第二个T是传给实现接口的T。

public class GenInterfaceImplementGenerics<T> implements GenericsInterface<T>{
    @Override
    public T server(T t) {
        System.out.println(t);
        return t;
    }
}
```  

### 4.3 泛型方法
语法：`<T> 返回值类型`  
```java
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
```

### 4.4 泛型集合
概念：参数化类型、类型安全的集合，强制集合元素的类型必须一致。  
特点：  
* 编译时即可检查，而非运行时抛出异常；  
* 访问时，不必类型转换（拆箱）；
    * 泛型集合不指定类型，之间用`Object`类型时，若在遍历或者访问时，如果需要知道元素的类型，对其强转的时候可能出现类型转换异常。
* 不同泛型之间引用不能赋值，泛型不存在多态。  

## 5 Set接口
特点：无序、无下标、元素不可重复。  
方法：全部继承自`Collection`中的方法。  

### 5.1 HashSet 重点
概述：
* 基于`HashCode`计算元素存放位置；  
* 当存入元素的哈希码相同时，会调用`equals`进行确认，如果结果为`true`，则拒绝后者存入。  
* 存储结构：哈希表（数组+链表+红黑树）
* 存储过程：
    * 根据hashcode计算保存的位置，如果此位置为空，则直接保存，如果不为空执行第二步；
      ```    
        @Override
         /**
          * 同一姓名的划分到一个列表
          */
         public int hashCode() {
             return this.name.hashCode();
     
         }
        ```
  
    * 执行`equals()`方法，如果返回`true`，则认为是重复，否则，（可以重写`hashCode()`，将希望放在一个数组的对象形成链表）形成链表。
        ```
            @Override
            /**
             * id作为唯一识别号
             */
        
            public boolean equals(Object obj) {
                if(this == obj){
                    return true;
                }
                if(obj == null){
                    return false;
                }
                if(obj instanceof Person){
                    Person person = (Person)obj;
                    return this.id == person.id;
                }
                return false;
            }
        ```
* `HashSe`t实际上用的就是`HashMap`的`key`。
#### 补充：（重点）
```
    public static int hashCode(Object a[]) {
        if (a == null)
            return 0;

        int result = 1;

        for (Object element : a)
            result = 31 * result + (element == null ? 0 : element.hashCode());

        return result;
    }
```
其中用的`31`的原因：
* 31是一个质数，可以减少散列冲突；  
* 31可以提高执行效率:`31*i = (i<<5) - i`将乘法运算转换为移位运算，提高运算。

`HashSe`t实际上用的就是`HashMap`的`key`。
### 5.2 TreeSet

特点：
* 基于排列顺序实现元素不重复；  
* 实现了`SortedSet`接口，对集合元素自动排序。  
* 元素对象的类型必须实现`Comparable`接口，指定排序规则；  
    ```
      /**
       * 使用TreeSet的存储的对象的类必须实现Comparable接口
       */
      public static void treeSet(){
          // 创建集合
          TreeSet<Child> users = new TreeSet<>();
  
          // 添加
          Child zhangsan = new Child("abc", 18, 1);
          Child lisi = new Child("xyz", 12, 2);
          Child wangwu = new Child("hello", 13, 3);
          Child zhaoliu = new Child("zhaoliu", 14, 4);
          Child zhaoliu2 = new Child("zhaoliu", 15, 4);
          users.add(zhangsan);
          users.add(lisi);
          users.add(wangwu);
          users.add(zhaoliu);
          // 年龄小的在前面
          users.add(zhaoliu2);
          System.out.println(users.toString());
  
          // 可删除
          users.remove(new Child("zhaoliu", 15, 4));
          System.out.println(users.toString());
      }
  
     class Child implements Comparable<Child>{
     int age,id;
     String name;
 
     public Child() {
     }
 
     public Child( String name,int age, int id) {
         this.age = age;
         this.id = id;
         this.name = name;
     }
 
     public int getAge() {
         return age;
     }
 
     public void setAge(int age) {
         this.age = age;
     }
 
     public int getId() {
         return id;
     }
 
     public void setId(int id) {
         this.id = id;
     }
 
     public String getName() {
         return name;
     }
 
     public void setName(String name) {
         this.name = name;
     }
 
     @Override
     public String toString() {
         return "Person{" +
                 "name=" + name  +
                 ", age=" + age  +
                 ", id='" + id + '\'' +
                 '}';
     }
 
 
 
     @Override
     public int compareTo(Child o) {
         int n1 = this.getName().compareTo(o.getName());
         int n2 = this.age - o.getAge();
         // 先比较姓名再比较年龄
         return n1 == 0 ? n2 : n1;
     }
 
     // 自动生成的
     //    @Override
     //    public boolean equals(Object o) {
     //        if (this == o) {return true;}
     //        if (o == null || getClass() != o.getClass()) {return false;}
     //        Person person = (Person) o;
     //        return age == person.age &&
     //                id == person.id &&
     //                Objects.equals(name, person.name);
     //    }
     //
     //    @Override
     //    public int hashCode() {
     //        return Objects.hash(age, id, name);
     //    }
    }   
    ```  
  
  或者采用匿名内部类的方式实现`Comparable`接口：  
    ```
        /**
         * 采用匿名内部类的方式实现Comparator接口
         */
        public static void comparator(){
            TreeSet<Person> users = new TreeSet<>(new Comparator<Person>(){
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getAge() - o2.getAge();  // 按照年龄比较大小
    
    
                }
            });
            Person zhangsan = new Person("zhangsan", 18, 1);
            Person lisi = new Person("lisi", 12, 2);
            Person wangwu = new Person("wangwu", 13, 3);
            Person zhaoliu = new Person("zhaoliu", 14, 4);
            users.add(zhangsan);
            users.add(lisi);
            users.add(wangwu);
            users.add(zhaoliu);
            users.add(zhaoliu);
    
            System.out.println(users.toString());
    
        }
    ```
* 通过`CompareTo`方法确定是否为重复元素，返回为0，则判断为重复元素。  

存储结构：
* 红黑树：给二叉查找树上颜色（为了保证左右两边平衡），二叉查找树：一个节点最多有两个子节点，左孩子小于右孩子。

#### 补充 Comparator接口
Comparator接口：实现定制比较（相对于一个比较器）。  
Comparable：可比较的。

## 6 Map集合
### 6.1 Map父接口
Map接口的特点：   
* 用于存储任意键值对（`Key-Value`）；
* 键：无序、无下标、不允许重复（唯一）；
* 值：无序、无下标、允许重复。  

方法：  
* V put(K key, V value) 
  将指定的值与该映射中的指定键相关联（可选操作）。  
* V get(Object key) 
  返回到指定键所映射的值，或 null如果此映射包含该键的映射。  
* （重点）Set<K> keySet() 
  返回此地图中包含的键的Set视图。  
* Collection<V> values() 
  返回此地图中包含的值的Collection视图。  
* （重点）Set<Map.Entry<K,V>> entrySet() 
  返回此地图中包含的映射的Set视图。  

遍历方式：  
* 1、使用`keySet()`，返回的是`key`的集合；
    ```
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(key+":  "+map.get(key));
        }
    ```

* 2、使用`entrySet()`，返回的是`Entry`的集合，一个`Entry`就是一个键值对。效率更高。  

  ```
    Set<Map.Entry<String, String>> entries = map.entrySet();
    for (Map.Entry<String, String> entry : entries) {
        System.out.println(entry.getKey() +":   "+ entry.getValue());
    }
  ```

  
    
实现类
* 1、`HashMap`：JDK1.2版本，线程不安全，运行效率快，允许用`null`作为`key`或`value`；
* 2、 `HashTable`： JDK1.0版本，线程安全，允许效率慢，不允许使用`null`作为`key`或`value`；
* 3、 `Properties`：HashTable的子类，要求`key`和`value`都是`String`，通常用于配置文件的读取，与IO流密切西瓜；
* 4、`TreeMap`：实现`SortedMap`接口（`Map`的子接口），可以对`key`自动排序。

### 6.2 HashMap实现类
注意：  
* 1、无参构造的默认初始容量为16，默认加载因子0.75。
* 2、存储结构：哈希表（数组+链表+红黑树）。  
* 3、使用`key`的`hashCode()`和`equals()`作为重复元素的判断，默认比的是地址。如果要对`new`对象去重，比如重写方法。  
* 4、允许`null`作为`key`或`value`。

源码分析：  
* 1、属性：
    * 1、`DEFAULT_INITIAL_CAPACITY`：默认容量 16
    * 2、`DEFAULT_LOAD_FACTOR`：默认加载因子 0.75，当size达到容量的75%后进行扩容。
    * 3、`TREEIFY_THRESHOLD`：红黑树阈值 8；`MIN_TREEIFY_CAPACITY`: 最小树的容量 64，如果链表长度大于8，并且数组长度大于64，那么就
    把链表变成红黑树。  
    * 4、`UNTREEIFY_THRESHOLD` 阈值 6，如果链表长度小于6，重新调整为链表。
* 2、构造方法
    * 1、刚刚创建HashMap之后，没有添加元素之前，table=null，size=0。为了节省空间；
* 3、`put()`方法
    * 1、在添加第一个元素的时候，数组长度是16，根据hash值选择一个位置放入键值；
    * 2、当元素个数`size`超过`0.75*容量`时，扩容`resize()`，每次扩容都是原来的`2`倍，即第一次扩容之后容量为`32`，变为`2`倍的目的是减少调整元素的个数。
* 4、其他
    jdk1.8以前，链表是头插入，1.8以后是尾插入。    实现类
### 6.3 HashTable实现类
JDK1.0版本，线程安全，运行速率慢；不允许`null`作为`key`或`value`。
#### Properties子类
HashTable的子类，要求`key`和`value`都是`String`，通常用于配置文件的读取，与`IO`流紧密相关。

### 6.4 TreeMap类
`TreeMap`：实现`SortedMap`接口（`Map`的子接口），可以对`key`自动排序。所以在使用时，所存储的对象的类必须实现`Comparable`接口。类似于`TreeSet`。  
添加、删除、排序都是根据`compareTo()`方法进行判断的。  
遍历同父接口。  
TreeSet和TreeMap的区别：TreeSet就是TreeMap的`Key`。

## 7 Collections工具类
概念:集合工具类，定义了除了存取以外的集合常用方法。  

方法：  
*  static void reverse(List<?> list) 
   反转指定列表中元素的顺序。  
*   static void shuffle(List<?> list) 
   使用默认的随机源随机排列指定的列表 
*   static <T extends Comparable<? super T>>
   void sort(List<T> list) 
   根据其元素的natural ordering对指定的列表进行排序。  
*   static void swap(List<?> list, int i, int j) 
   交换指定列表中指定位置的元素。 
 *  static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) 
   使用二叉搜索算法搜索指定对象的指定列表。（先排序才能进行二分查找） 
   
   
注意：
* 二分查找针对的是有序集合，所以要先排序；  
* List转数组：`mylist.toArray(new ClassName[num])`，如
果`num < mylist.size()`，那么返回数组长度为实际长度，否则为指定数组长度；
* 数组转List：`Arrays.asList(myarray)`，注意基本类型的数组转成集合要用它的包装类。

   
   ```java
package CollectionFrame.CollectionsDemo;

import java.util.*;

/**
 * Collections工具类测试
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(1);
        list.add(32);
        list.add(20);

        // 排序
        System.out.println("=============排序之前====================");
        System.out.println(list.toString());
        Collections.sort(list);
        System.out.println("=============排序之后====================");
        System.out.println(list.toString());

        // 二分查找，返回负数表示没有找到
        int i = Collections.binarySearch(list, 45);
        System.out.println(i);

        // copy复制,必须要保证两个数组长度一致，所以先循环遍历一次源数组给目标数组赋初值。
        ArrayList<Object> dest = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            dest.add(0);
        }
        Collections.copy(dest,list);
        System.out.println(dest.toString());

        // 反转
        Collections.reverse(list);
        System.out.println(list.toString());

        // 打乱，洗牌
        Collections.shuffle(list);
        System.out.println(list.toString());

        // list转数组
        // 这里new Integer[0]的0不影响，只是用到了参数的类型，如果给定的参数长度比
        // 实际返回的数组长度小，那么返回的是实际长度，如果给定参数长度比实际大，返回
        // 的数组长度是给定长度。
        Integer[] array = list.toArray(new Integer[0]);

        // 数组转list
        String[] names = {"zhangsan","lisi","wangwu"};
        // 受限集合，该集合不能添加和删除
        List<String> list1 = Arrays.asList(names);
        // 基本类型的数据转成集合时要用它的包装类
        //  如果直接用基本数据类型，那么集合中只有一个元素，就是那个数组
        int[] numsInt = {100,1,2};
        List<int[]> ints = Arrays.asList(numsInt);
        // 用包装类
        Integer[] numsInter = {100,1,2};
        List<Integer> Inters = Arrays.asList(numsInter);

    }
}

```
    
# 总结
集合的概念：对象的容器，定义了对多个对象进行操作的常用方法，可类似于实现数组的功能。  
List集合：有序、有下标、元素可以重复：ArrayList、LinkedList、Vector；  
Set集合：无序、无下标、元素不可以重复：HashSet（无序）、TreeSet（有序，必须实现Comparable接口）；  
Map集合：存储一对数组，无序、无下标，键不可重复，值可重复：HashMap、HashTable、TreeMap（有序，必须实现Comparable接口）；  
Collections：集合工具类，定义了除了存取以外的集合常用方法。  
