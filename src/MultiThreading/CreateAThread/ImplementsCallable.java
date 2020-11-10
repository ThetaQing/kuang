package MultiThreading.CreateAThread;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

// 实现Callable方法
public class ImplementsCallable implements Callable<Boolean> {
    private String url;  // 网络图片地址
    private String name;  // 保存的文件名

    public ImplementsCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url,name);
        System.out.println("下载了文件名："+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ImplementsCallable implementsCallable1 = new ImplementsCallable("https://tse4-mm.cn.bing.net/th/id/OIP.pFHTlYZafc6v9ggWDagO3gHaHa?pid=Api&rs=1", "小狗1.jpg");
        ImplementsCallable implementsCallable2 = new ImplementsCallable("https://tse4-mm.cn.bing.net/th/id/OIP.pFHTlYZafc6v9ggWDagO3gHaHa?pid=Api&rs=1", "小狗2.jpg");
        ImplementsCallable implementsCallable3 = new ImplementsCallable("https://tse4-mm.cn.bing.net/th/id/OIP.pFHTlYZafc6v9ggWDagO3gHaHa?pid=Api&rs=1", "小狗3.jpg");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);  // 3个线程
        // 提交执行
        Future<Boolean> r1 = ser.submit(implementsCallable1);
        Future<Boolean> r2 = ser.submit(implementsCallable2);
        Future<Boolean> r3 = ser.submit(implementsCallable3);
        // 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();
        // 关闭服务
        ser.shutdownNow();

    }

    class WebDownLoader{  // 下载器
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

}



