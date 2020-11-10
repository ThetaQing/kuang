package MultiThreading.CommonMethod;
// 礼让测试，礼让不一定成功，看CPU调度
public class TestYield {
    public static void main(String[] args) {
        MyYeild myYeild1 = new MyYeild();
        new Thread(myYeild1,"a").start();
        new Thread(myYeild1,"b").start();
    }
}

class MyYeild implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"线程停止");
    }
}
