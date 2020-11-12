package IO.ByteStreamBasic;

import java.io.*;

/**
 * @author SongQing
 * @function 测试字节缓冲流
 */
public class BufferedStream {
    public static void main(String[] args) {
        try {
            // 输入缓冲流
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./src/IO/readme.md"));
            // 输出缓冲流
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./src/IO/Basic/receive.md"));
            byte[] buffer = new byte[1024];
            int len = 0;
            bos.write("# Hello\r".getBytes());  // 直接写入字节数组
            bos.flush();
            while((len = bis.read(buffer))!=-1){  // 从输入缓冲流中读取字节数组
                bos.write(buffer,0,len);
                bos.flush();  // 刷新到硬盘
            }
            bos.close();;
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
