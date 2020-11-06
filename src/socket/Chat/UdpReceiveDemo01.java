package socket.Chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpReceiveDemo01 {
    public static void main(String[] args) {

        try {
            // 1、开启端口
            DatagramSocket socket = new DatagramSocket(8080);
            // 2、接收包

            while(true){
                byte[] msg = new byte[1024];  // 准备接收的包裹
                DatagramPacket packet = new DatagramPacket(msg, 0, msg.length);


                socket.receive(packet);  // 阻塞式接收包裹
                // 3、显示并判断
                byte[] data = packet.getData();
                String receiveData = new String(data, 0, data.length);
                System.out.println(receiveData);
                if (receiveData.contains("bye")) {
                    System.out.println("see you~");


                    break;

                }

            }
            System.out.println("end");
            socket.close();





        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
