package socket.TCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//客户端
public class TcpClientDemo01 {
    public static void main(String[] args) {

        Socket socket = null;
        OutputStream os = null;
        try {
            // 1、要知道的服务器地址、端口号
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            int port = 9999;

            // 2、创建一个socket连接
            socket = new Socket(serverIP,port);

            // 3、发送消息  IO 流
            os = socket.getOutputStream();
            os.write("hello,welcome to class".getBytes());

            // 关闭资源
            os.close();
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
