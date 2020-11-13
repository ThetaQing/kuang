package IO.Properties;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
    public static void main(String[] args) {
        // 创建集合
        Properties properties = new Properties();

        // 添加数据
        properties.setProperty("name","zhangsan");
        properties.setProperty("age","18");

        //  遍历
        // 1 keySet
        Set<Object> set = properties.keySet();
        for (Object o : set) {
            System.out.println("keySet  "+o.toString());
        }
        // 2 entrySet
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println("entrySet  "+entry.toString());
        }
        // 3 stringPropertyNames
        Set<String> strings = properties.stringPropertyNames();
        for (String string : strings) {
            System.out.println("stringPropertyNames  "+string + "属性的值为："+properties.getProperty(string));
        }

        // 流相关的方法
        // list
        try {
            PrintWriter pw = new PrintWriter("./src/IO/Properties/list.txt");
            properties.list(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // store
        try {
            FileOutputStream fos = new FileOutputStream("./src/IO/Properties/store.txt");
            properties.store(fos,"version 1");
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // load
        Properties properties2 = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./src/IO/Properties/list.txt");
            properties2.load(fis);
            strings = properties2.stringPropertyNames();
            for (String string : strings) {
                System.out.println("stringPropertyNames  "+string + "属性的值为："+properties2.getProperty(string));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
