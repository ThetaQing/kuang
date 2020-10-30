package socket.TCP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClientDemo02 {
    public static void main(String[] args){

        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            //1、创建一个socket连接
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            // 2 create an output stream
            os = socket.getOutputStream();

            // 3. file stream
            // 3.1 read in file
            fis = new FileInputStream(new File("lychee.jpg"));
            // 3.2 write out the file
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer))!= -1){
                os.write(buffer,0,len);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
