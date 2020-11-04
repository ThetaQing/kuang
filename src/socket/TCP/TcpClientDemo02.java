package socket.TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientDemo02 {
    public static void main(String[] args){

        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        InputStream inputStream =null;
        ByteArrayOutputStream baos = null;
        try {
            //1、创建一个socket连接
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
            // 2 create an output stream
            os = socket.getOutputStream();

            // 3. file stream
            // 3.1 读入文件
            fis = new FileInputStream(new File("G://Code//Java//kuang//src//socket//TCP//lychee.jpg"));
            // 3.2 将这个文件写出
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis.read(buffer))!= -1){
                os.write(buffer,0,len);

            }
            // 通知服务器已经传输完成
            socket.shutdownOutput();  // 传输完成
            // 确定服务端接收完毕
            inputStream = socket.getInputStream();
            // getBytes返回的是byte[]类型
            baos = new ByteArrayOutputStream();

            byte[] buffer2 = new byte[1024];
            int len2;
            while((len2=inputStream.read(buffer2))!=-1){
                baos.write(buffer2,0,len2);
            }
            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
