package CollectionFrame.SetDemo;

import AnnotationAndReflection.Application.User;

import java.util.*;

/**
 * Set特点
 * 1、无序
 * 2、无下标
 * 3、不能重复
 *
 */
public class SetTest {
    public static void main(String[] args) {
        comparator();
    }

    /*Set基本使用*/
    public static void setBaisc() {

        // 创建
        HashSet<String> hashSet = new HashSet<>();
        // 添加
        hashSet.add("1");
        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("0");
        hashSet.add("4");

        System.out.println("size: "+hashSet.size());
        System.out.println(hashSet.toString());

        // 删除
        hashSet.remove("1");

        // 遍历
        // 增强for
        System.out.println("========增强for遍历============");

        for (String s : hashSet) {
            System.out.println(s);
        }
        // 迭代器
        System.out.println("========迭代器遍历============");
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 判断
        System.out.println(hashSet.contains("1"));
        System.out.println(hashSet.isEmpty());
    }

    public static void hashSet(){
        HashSet<Person> users = new HashSet<>();
        Person zhangsan = new Person("zhangsan", 18, 1);
        Person lisi = new Person("lisi", 12, 2);
        Person wangwu = new Person("wangwu", 13, 3);
        Person zhaoliu = new Person("zhaoliu", 14, 4);
        users.add(zhangsan);
        users.add(lisi);
        users.add(wangwu);
        users.add(zhaoliu);
        users.add(zhaoliu);

        System.out.println(users.size());
        // 默认的equals函数时可以添加
        users.add(new Person("zhangsan", 1, 5));
        users.add(new Person("zhangsan", 1, 5));
        users.add(new Person("zhangsan", 1, 6));
        users.add(new Person("zhangsan", 1, 7));

        // 如果不希望被添加，可以重写User类中的equals方法

        System.out.println(users.toString());

        System.out.println("===========企图删除一个id重复但其他不重复的对象=========");
        System.out.println(users.remove(new Person("999", 1, 1)));
        System.out.println("===========企图删除一个new对象，但信息完全一样=========");
        System.out.println(users.remove(new Person("zhaoliu", 14, 4)));
        System.out.println("===========企图删除一个new对象，但姓名不一样=========");
        System.out.println(users.remove(new Person("wangwu", 14, 4)));

        System.out.println(users.toString());


    }

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

}

class Person{
    int age,id;
    String name;

    public Person() {
    }

    public Person( String name,int age, int id) {
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
    /**
     * 同一姓名的划分到一个列表
     */
    public int hashCode() {
        System.out.println("调用hashcoud()");
        return this.name.hashCode();

    }

    @Override
    /**
     * id作为唯一识别号
     */

    public boolean equals(Object obj) {
        System.out.println("调用equals()");
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