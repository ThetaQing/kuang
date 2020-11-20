package AnnotationAndReflection.Application;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int id;
    private transient int age;
    private static String country="China";
    public User() {
    }
    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public static String getCountry() {
        return country;
    }
    public static void setCountry(String country) {
        User.country = country;
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
                ", country=" + country +
                '}';
    }
}
