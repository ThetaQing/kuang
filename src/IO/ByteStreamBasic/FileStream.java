package IO.ByteStreamBasic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author SongQing
 * @function 演示文件字节输入流
 */
public class FileStream {
    public static void main(String[] args) {
        try {
            FileInputStream fis =  new FileInputStream("./src/IO/readme.md");
            FileOutputStream fos = new FileOutputStream("./src/IO/ByteStreamBasic/fos.md");
            byte[] buffer = new byte[1024];
            int len;
            //while((len = fis.read(buffer))!= -1){
            //    String s = new String(buffer, 0, len);
                //System.out.println(s);  //

                //fos.write(buffer,0,len);  // 写出到文件
            //}
            while((len = fis.read()) != -1){
                System.out.print((char)len);  // 逐字节读取中文乱码

                fos.write((char)len);
                fos.flush();
            }

            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
