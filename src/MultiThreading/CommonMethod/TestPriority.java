package MultiThreading.CommonMethod;
// 测试线程优先级
public class TestPriority {
    public static void main(String[] args) {
        // 主线程的默认优先级
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority,"A");
        thread1.setPriority(Thread.MIN_PRIORITY);
        Thread thread2 = new Thread(myPriority,"B");
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();


    }
}

class MyPriority implements Runnable{
    @Override
    public void run() {
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程的优先级是"+Thread.currentThread().getPriority());
    }
}