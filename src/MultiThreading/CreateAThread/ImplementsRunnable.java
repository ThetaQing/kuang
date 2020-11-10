package MultiThreading.CreateAThread;

// 创建线程方式2：实现Runnable接口，重写run方法，执行线程需要丢入Runnable接口实现类，调用start方法。
public class ImplementsRunnable implements Runnable{
    @Override
    public void run() {
        // run方法线程体
        for (int i = 0; i < 200; i++) {
            System.out.println("-----------: " + i);
        }
    }

    public static void main(String[] args) {
        System.out.println("Begin");
        // 创建Runnable接口的实现类对象
        ImplementsRunnable thread1 = new ImplementsRunnable();
        // 创建线程对象，通过线程对象来开启线程，代理
        Thread thread = new Thread(thread1);
        // 启动线程
        thread.start();


        System.out.println("End");

    }
}
