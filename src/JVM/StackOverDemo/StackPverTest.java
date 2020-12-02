package JVM.StackOverDemo;

/**
 * @author SongQing
 * 栈溢出
 */
public class StackPverTest {
    public static void main(String[] args) {
        new StackPverTest().a();
    }
    public void a(){
        b();
    }
    public void b(){
        a();
    }
}
