package IO.ByteStreamBasic;

import AnnotationAndReflection.Application.User;

import java.io.*;
import java.util.ArrayList;

/**
 * @author SongQing
 * @function 对象流，序列化、反序列化
 * 要求：要实现序列化的类必须实现Serializable接口
 */
//

public class ObjectStream {
    // 反序列化
    public static  void deserialization(){
        // 反序列化

        //1 创建对象输入流
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("./src/IO/ByteStreamBasic/receive.bin"));

        // 2 反序列化
        ArrayList list = (ArrayList) ois.readObject();  // 序列化是用的list,反序列化也是list
        Object o2 = ois.readObject();  // 一个对象不能读多次

        System.out.println(list.size());
        System.out.println(list.toString());
        System.out.println(o2.toString());

        } catch (IOException e) {
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3 关闭
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 序列化
    public static void serialization(){
        try {
            // 序列化
            //1 创建对象输出流
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/IO/ByteStreamBasic/receive.bin"));
            //2 序列化
            // 2.1 创建对象
            User songqing = new User("songqing",001,18);
            songqing.setCountry("Aisa");

            User huwei = new User("huwei",002,19);
            huwei.setCountry("Changsha");

            User xiaohu = new User("xiaohu", 003, 1);
            // 2.2 创建序列
            ArrayList<User> list = new ArrayList<>();
            list.add(songqing);
            list.add(huwei);

            // 2.3 将这个对象写入对象输出流
            oos.writeObject(list);  // 一次性写入多个对象
            oos.writeObject(xiaohu);  // 一次写入一个对象

            // 3 关闭
            oos.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        serialization();
        deserialization();

    }
}
