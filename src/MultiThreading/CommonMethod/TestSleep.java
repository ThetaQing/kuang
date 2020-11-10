package MultiThreading.CommonMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

// 模拟网络延时:放大问题的发生性！！！，见TestThread4
// 模拟倒计时
public class TestSleep {
    //倒计时
   public void countDown(int seconds) throws InterruptedException {
       System.out.println(String.format("%d秒倒计时开始",seconds));
       while(seconds>0){
           System.out.println(seconds);
           Thread.sleep(1000);
           seconds--;
       }
       System.out.println("倒计时结束");

   }
   // 打印系统当前时间
    public void printSystemTime(){
        Date startTime = new Date(System.currentTimeMillis());  // 获取当前时间
        while(true){
            try {
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));  // 打印时间
                Thread.sleep(1000);  // 休眠1s
                startTime = new Date(System.currentTimeMillis());  // 更新时间

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
       new TestSleep().printSystemTime();


    }
}
