package MultiThreading.CommonMethod;
// 测试守护线程
// 案例:上帝守护我们
public class TestDaemon {
    public static void main(String[] args) {
        We we = new We();
        God god = new God();

        Thread threadWe = new Thread(we,"we");
        Thread threadGod = new Thread(god, "god");
        threadGod.setDaemon(true);  // 设置为守护线程
        threadWe.start();
        threadGod.start();
    }
}

// 我们
class We implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 365; i++) {
            System.out.println("我们开心地活着"+i);
        }
        System.out.println("---------GoodBye World--------------");

    }
}
// 上帝
class God implements Runnable{
    @Override
    public void run() {
        while(true)
            System.out.println("上帝保佑着你");

    }
}
