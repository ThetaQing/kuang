package socket.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo02 {
    public static void main(String[] args) {
        try {

            // 1 create a socket
            ServerSocket serverSocket = new ServerSocket(9000);
            //2. listen to client
            Socket socket = serverSocket.accept();  // 阻塞式监听，会一直等待客户端连接
            // 3. get instream
            InputStream is = socket.getInputStream();  // 读取文件流

            // 4. 文件输出 将这个文件写出
            FileOutputStream fos = new FileOutputStream(new File("G://Code//Java//kuang//src//socket//TCP//receive.png"));

            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer))!= -1){  // 读取比特，用buffer存储，len表示实际buffer中存储了多少个byte
                fos.write(buffer,0,len);  // 从buffer中将0-len个写入到输出流中
            }

            // 通知客户端完成接收
            OutputStream os = socket.getOutputStream();
            os.write("recieved".getBytes());


            // 5. 关闭资源
            fos.close();
            is.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
