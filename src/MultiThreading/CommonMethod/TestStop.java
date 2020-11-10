package MultiThreading.CommonMethod;
// 测试stop
// 1、建议线程正常停止 --> 利用次数，不建议死循环
// 2、建议使用标志位  --> 设置一个标志位
// 3、不要使用stop、destroy等过时的或JDK不建议使用的方法
public class TestStop implements Runnable{

    // 1、设置标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run…………"+i++);
        }
    }
    // 设置一个公开的方法停止线程,转换标志位
    public void  stop(){
        flag = false;
        System.out.println("Thread over");
    }


    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("主线程…………"+i);
            if(i>1900)
                testStop.stop();
        }



    }
}
