package socket.URL;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// 下载文件
public class UrlDown {
    public static void main(String[] args) {
        try {
            // 1、下载地址
            URL url = new URL("https://p2.music.126.net/KNgor5qR4QqNOcAEPktULw==/18589443091252768.jpg?param=200y200");  // 资源地址
            // 2、连接到这个资源,要用Http连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 3、得到流
            InputStream inputStream = urlConnection.getInputStream();
            // 4、解析流
            byte[] buffer = new byte[1024];
            int len;
            FileOutputStream fileOutputStream = new FileOutputStream(new File("./src/socket/URL/receive.jpg"));
            while((len = inputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,len);
            }

            fileOutputStream.close();
            inputStream.close();
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
