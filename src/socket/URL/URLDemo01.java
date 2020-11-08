package socket.URL;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo01 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http:localhost:8080//helloworld/index.jsp?username=kuangshen&password=123");
            System.out.println(url.getProtocol());  //协议：http
            System.out.println(url.getHost());  // IP
            System.out.println(url.getPort());  // 端口
            System.out.println(url.getPath());  // 路径
            System.out.println(url.getFile());  // 文件
            System.out.println(url.getQuery());  // 参数
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
