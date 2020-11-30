package CollectionFrame.MapDemo;

import java.util.*;

/**
 * Map接口的基本使用
 * 特点：存储键值；键不能重复，值可以重复；无序。
 */
public class MapBasic {
    public static void main(String[] args) {
        treeMap();
    }

    public static void basic() {
        // 创建
        HashMap<String, String> map = new HashMap<>();
        // 添加
        map.put("cn","中国");
        // 添加重复key的元素，值会是后者，替换掉原来的
        map.put("cn","China");
        map.put("USA","美国");
        map.put("uk","英国");

        // 删除
        map.remove("USA");
        //遍历
        // 1、使用keySet
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(key+":  "+map.get(key));
        }
        // 2、 使用entrySet
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() +":   "+ entry.getValue());
        }
    }

    /**
     * 存储结构：哈希表（数组+链表+红黑树）
     */
    public static void hashMap(){
        HashMap<Person, String> students = new HashMap<>();
        // 刚刚创建HashMap之后，没有添加元素之前，table=null，size=0。为了节省空间

        Person zhangsan = new Person("zhangsan", 1);
        Person lisi = new Person("lisi", 2);
        Person wangwu = new Person("wangwu", 3);

        students.put(zhangsan,"北京");
        students.put(lisi,"南京");
        students.put(wangwu,"长沙");

        // 添加相同的new对象
        students.put(new Person("zhangsan",1),"上海");

        System.out.println(students.toString());
    }

    public static void treeMap(){
        TreeMap<Person, String> persons = new TreeMap<>();
        Person zhangsan = new Person("zhangsan", 1);
        Person lisi = new Person("lisi", 2);
        Person wangwu = new Person("wangwu", 3);
        persons.put(zhangsan,"beijing");
        persons.put(lisi,"shanghai");
        persons.put(wangwu,"changsha");

        System.out.println(persons.toString());


    }
}
class Person implements Comparable{
    int id;
    String name;

    public Person() {
    }

    public Person( String name,int id) {
        this.id = id;

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int age) {
        this.id = age;
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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Object o) {

        int re = this.id - ((Person) o).getId();
        return re;
    }
}

