package MultiThreading.Application;
//模拟龟兔赛跑
public class RaceBetweenTortoiseAndHare implements Runnable {

    private static String winner;  // 胜利者

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            // 模拟兔子睡觉
            if(Thread.currentThread().getName().equals("兔子") && i%99== 0)//隔多长时间睡觉
                try {
                    Thread.sleep(1);  // 睡多久
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            // 判断比赛是否结束
            boolean flag = gameOver(i);
            if(flag)
                break;
            System.out.println(Thread.currentThread().getName() + "-->跑了" + i + "步");

        }
    }

    // 判断是否完成比赛
    private boolean gameOver(int steps) {
        // 判断是否有胜利者
        if (winner != null) {// 已经存在胜利者
            return true;
        } {  // 是否到终点
            if(steps >= 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RaceBetweenTortoiseAndHare raceBetweenTortoiseAndHare = new RaceBetweenTortoiseAndHare();

        new Thread(raceBetweenTortoiseAndHare,"兔子").start();
        new Thread(raceBetweenTortoiseAndHare,"乌龟").start();

    }
}
