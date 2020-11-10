package ObjectOriented.Interface.StaticProxy;
//静态代理——线程的原理
//以结婚和婚庆公司为例
public class StaticProxy {
    public static void main(String[] args) {
        You you = new You();  // 真实对象
        WeedingCompany weedingCompany = new WeedingCompany(you);
        weedingCompany.happyMarry();
    }

}

// 真实角色，你去结婚
class You implements Marry{
    @Override
    public void happyMarry() {
        System.out.println("你要结婚啦~~~");
    }
}

// 代理角色，帮助你完成婚礼前和婚礼后的事情
class WeedingCompany implements Marry{
    // 代理谁？-->真实目标角色
    private Marry target;

    public WeedingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();  // 目标对象去完成结婚动作
        after();
    }
    private void before() {
        System.out.println("完成各种准备工作……");
    }

    private void after() {
        System.out.println("辛苦费辛苦费……");
    }
}
