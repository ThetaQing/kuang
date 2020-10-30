package socket.IP;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class testInetAddress {
    public static void main(String[] args) {
        try {
            // 查询网站的IP地址
            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress);
            // 查询本地的IP地址有三种方法：InetAddress.getByName("127.0.0.1");  InetAddress.getByName("localhost");  InetAddress.getLocalHost();
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // InetSocketAddress  实现IP套接字地址
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
        System.out.println(inetSocketAddress.getAddress());  // 获取ip地址
        System.out.println(inetSocketAddress.getPort());  // 获取端口号


    }
}
