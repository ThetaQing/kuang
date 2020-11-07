package socket.Chat;

public class TalkStudent {
    public static void main(String[] args) {
        // 开启两个线程
        new Thread(new TalkSender(6666,"localhost",9999)).start();
        new Thread(new TalkReceiver(8888,"teacher：")).start();
    }
}
