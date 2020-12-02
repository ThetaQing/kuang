package JVM.HeapDemo;



import java.util.ArrayList;
import java.util.Random;

/**
 * @author SongQing
 * 堆溢出测试
 */
public class HeapOverTest {
    byte[] array = new byte[1*1024*1024];
    public static void main(String[] args) {

        ArrayList<Object> list = new ArrayList<>();
        int count = 0;

        //String str = "hello";

        try {
            while(true){
                list.add(new HeapOverTest());
                count++;
                //str += str + new Random().nextInt(888888888)+new Random().nextInt(999999999);

            }

        }catch (Error e){
            System.out.println(count);
            e.printStackTrace();

        }

    }
}
