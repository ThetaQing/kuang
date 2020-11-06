package socket.Chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class UdpSenderDemo01 {
    public static void main(String[] args) {
        new Thread();


        try {
            // 1、 创建socket
            DatagramSocket socket = new DatagramSocket(8888);
            // 2、创建包
            // 准备数据信息

            byte[] buffer = new byte[1024];
            // 从控制台读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String data = reader.readLine();
                DatagramPacket packet = new DatagramPacket(data.getBytes(),0,data.getBytes().length,new InetSocketAddress("localhost",8080));
                // 3、发送包
                socket.send(packet);
                if(data.contains("bye")){
                    System.out.println("see you~");
                    break;
                }

            }
            // 4 close
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
