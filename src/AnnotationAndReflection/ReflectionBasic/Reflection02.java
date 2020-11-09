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
