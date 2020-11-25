package CollectionFrame;

import AnnotationAndReflection.Application.User;

import java.util.*;

public class ListDemo {
    public static void main(String[] args) {
        testNum();
    }
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

    public static void testArrayList(){
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("hello");
        arrayList.add("hello");

    }

    public static void testVector(){
        Vector<Object> vector = new Vector<>();
        vector.add("apple");
        vector.add("water");
        vector.add("world");

        // 枚举器遍历
        Enumeration<Object> elements = vector.elements();
        while (elements.hasMoreElements()) {
            String o = (String)elements.nextElement();
            System.out.println(o);
        }


    }

    public static void testLinkedList(){
        LinkedList<Object> linkedList = new LinkedList<>();
        User zhangsan = new User("zhangsan", 1, 18);
        User lisi = new User("lisi", 2, 12);
        User wangwu = new User("wangwu", 3, 13);
        User zhaoliu = new User("zhaoliu", 4, 14);
        linkedList.add(zhangsan);
        linkedList.add(lisi);
        linkedList.add(wangwu);
        linkedList.add(zhaoliu);

        ListIterator<Object> oLI = linkedList.listIterator();
        while (oLI.hasNext()) {
            System.out.println(oLI.next().toString());
        }

    }

}
