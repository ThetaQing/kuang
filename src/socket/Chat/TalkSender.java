package socket.Chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSender implements Runnable{

    DatagramSocket socket = null;
    BufferedReader reader = null;
    DatagramPacket packet = null;
    private int fromPort;
    private String toIP;
    private int toPort;
    //byte[] buffer = null;

    public TalkSender(int fromPort, String toIP, int toPort) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;
        try {
            //this.buffer = new byte[1024];
            this.socket = new DatagramSocket(this.fromPort);
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // 发送消息
        try {
            // 从控制台读取

            while(true){
                String data = this.reader.readLine();
                this.packet = new DatagramPacket(data.getBytes(),0,data.getBytes().length,new InetSocketAddress(toIP,toPort));
                // 3、发送包
                this.socket.send(packet);
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
