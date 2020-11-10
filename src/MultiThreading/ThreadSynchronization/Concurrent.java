package MultiThreading.ThreadSynchronization;
// 多个线程同时操作一个对象
// 多个线程操作同一个资源的情况下，出现问题
// 买火车票
// 并发

public class Concurrent implements Runnable{

    // 票数
    private int ticketNums = 10;
    @Override
    public void run() {
        while (true){
            if(ticketNums <= 0)
                break;
            try {
                Thread.sleep(200);  // 模拟延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNums--+"票");  // 获取当前进程的名字

        }
    }

    public static void main(String[] args) {
        Concurrent ticket = new Concurrent();
        // 多个线程操作同一个资源
        new Thread(ticket,"小明").start();
        new Thread(ticket,"小红").start();
        new Thread(ticket,"小芳").start();

    }
}
