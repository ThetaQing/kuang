package AnnotationAndReflection.Application;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// 通过反射操作注解
public class OperateAnnotation {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;  // 获得Class对象
        // 通过反射获取注解
        Annotation[] annotations = personClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        // 获得注解的value的值
        Table table = personClass.getDeclaredAnnotation(Table.class);
        System.out.println(table.value());

        // 获得类指定的注解
        try {
            Field name = personClass.getDeclaredField("name");  // 获取属性name的注解
            Attribution attribution = name.getAnnotation(Attribution.class);
            System.out.println(attribution.colunmName());
            System.out.println(attribution.length());
            System.out.println(attribution.type());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}


// 将数据库与类进行映射
@Table("db_student")
class Person{
    @Attribution(colunmName = "db_name",type = "varchar",length = 1)  // 数据库里面String是varchar
    private String name;
    @Attribution(colunmName = "db_id",type = "int",length = 2)
    private int id;
    @Attribution(colunmName = "db_age",type = "int",length = 3)
    private int age;
    public Person() {
    }

    public Person(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}

// 关于类名的注解，数据库中对应表名
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

// 关于属性的注解
//数据库中对应属性、字段、序号
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Attribution{
    String colunmName();
    String type();
    int length();
}