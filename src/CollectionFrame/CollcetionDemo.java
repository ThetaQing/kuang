package CollectionFrame;

import AnnotationAndReflection.Application.User;

import java.util.ArrayList;
import java.util.Iterator;

public class CollcetionDemo {
    public static void main(String[] args) {
        testCustomizeClass();

    }

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
