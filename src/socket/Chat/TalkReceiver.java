package socket.Chat;

import java.io.IOException;
import java.net.*;

public class TalkReceiver implements Runnable{
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    private int port;
    private String msgFrom;

    public TalkReceiver(int port,String msgFrom) {
        this.port = port;
        this.msgFrom = msgFrom;
        try {
            socket = new DatagramSocket(this.port);  // 打开这个端口

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                byte[] buffer = new byte[1024];
                packet = new DatagramPacket(buffer,0,buffer.length);
                this.socket.receive(this.packet);
                String data = new String(packet.getData(),0,packet.getData().length);
                System.out.println(msgFrom + data);
                if(data.contains("bye"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        socket.close();


    }


}
