package CommonUsedClass.InnerClass.AnonymousInner;
// 实现USB接口
public class Mouse implements USB{
    @Override
    public void service() {
        System.out.println("begin service……");
    }
}
