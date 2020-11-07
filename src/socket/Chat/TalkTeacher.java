package socket.Chat;

public class TalkTeacher {
    public static void main(String[] args) {
        new Thread(new TalkSender(7777,"localhost",8888)).start();
        new Thread(new TalkReceiver(9999,"student：")).start();

    }
}
