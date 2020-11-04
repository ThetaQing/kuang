package socket.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// 没有明确的客户端和服务端，这里只是做个区分
// 不需要连接服务端
public class UdpSendDemo01 {
    public static void main(String[] args) {

        try {
            // 1. 建立一个socket
            DatagramSocket socket = new DatagramSocket();

            // 2. 创建一个包
            // 2.1 需要发送的数据
            String msg = "你好啊，服务器！";
            //  2.2 服务器地址、端口号
            InetAddress localhost = InetAddress.getByName("localhost");
            int port = 9090;
            // 2.3 创建这个包
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, localhost, port);

            // 3. 发送这个包
            socket.send(packet);

            // 4. 关闭流
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
