package MultiThreading.ThreadCommunication;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

// 生产者消费者模式——信号灯法
public class WithFlag {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}
// 话剧演员
class Player extends Thread{
    TV tv  = new TV();

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        // 表演
        for (int i = 0; i < 60; i++) {
            if(i%2==0)
                tv.play("广告");
            else
                tv.play("节目");
        }


    }
}
// 观众
class Watcher extends Thread{
    TV tv  = new TV();

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 60; i++) {
            tv.watch();
        }

    }
}
// 节目
class TV{
    String name;
    boolean flag = false;

    public TV() {
    }

    public synchronized void play(String name){
        if(!flag){  // 非表演时间
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了："+name);
        this.name = name;
        flag = !flag;
        this.notifyAll();


    }

    public synchronized void watch(){
        if(flag) {  // 表演时间
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了"+name);

        this.notifyAll();
        flag = !flag;

    }

}
