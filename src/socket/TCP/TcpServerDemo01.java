package socket.TCP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 服务端
public class TcpServerDemo01 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1、我得有一个地址
            serverSocket = new ServerSocket(9999);

            while(true){  // 循环监听
                // 2. 等待客户端连接过来
                socket = serverSocket.accept();  // 监听，一旦监听到就是来自客户端的socket

                // 3、读取客户端的消息
                is = socket.getInputStream();
                // 管道流,通过管道流将两节包裹起来，如果直接用string去写入的话，一旦出现中文就有可能乱码
                baos = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer))!= -1){
                    baos.write(buffer,0,len);
                }
                System.out.println(baos.toString());
            }



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源，先开后关
            // 标准写法，写判断再捕获
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null) {
                try {
                    is.close();
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
            if(serverSocket!= null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
