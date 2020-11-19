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
