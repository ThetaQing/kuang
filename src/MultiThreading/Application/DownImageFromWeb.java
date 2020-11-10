package MultiThreading.Application;

// 联系Thread，实现多线程同步下载图片

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
// 练习Thread，实现多线程同步下载图片
public class DownImageFromWeb extends Thread{
    private String url;  // 网络图片地址
    private String name;  // 保存的文件名

    public DownImageFromWeb(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public DownImageFromWeb() {
    }

    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, name);
        System.out.println("下载的文件名为："+name);

    }
}

// 下载器
class WebDownLoader{
    // 下载方法
    public void downLoader(String url, String name){

        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现异常");
        }
    }
}