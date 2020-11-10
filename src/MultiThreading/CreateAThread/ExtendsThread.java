package MultiThreading.CreateAThread;

// 创建线程方式一：继承Thread类，重写run方法，调用start开启线程
// 注意：线程开启不一定马上执行，由CPU调度安排
public class ExtendsThread extends Thread{
    @Override
    public void run() {
        // run方法线程体
        for (int i = 0; i < 200; i++) {
            System.out.println("-----------: " + i);
        }

    }
}
