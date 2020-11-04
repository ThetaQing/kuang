package socket.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

// 没有明确的服务端概念，这里只是为方便区分

// 接收端：等待发送端的发送
public class UdpReceivedDemo01 {
    public static void main(String[] args) {

        try {
            // 1. 开放端口
            DatagramSocket socket = new DatagramSocket(9090);

            // 2. 接收
            // 2.1 创建接收的包容器
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
            // 2.2 接收
            socket.receive(packet);  // 阻塞接收

            System.out.println(packet.getAddress().getHostAddress());
            System.out.println(new String(packet.getData()));  // 显示
            // 3. 关闭
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
