package CommonUsedClass.InnerClass.AnonymousInner;

public class Anonymous {
    public static void main(String[] args) {
        /*创建一个接口类型的变量
        *
        Mouse mouse = new Mouse();
        mouse.service();*/

        /* 使用局部内部类
        class Anony implements USB{
            @Override
            public void service() {
                System.out.println("begin From Anony……");
            }
        }
        new Anony().service();*/

        // 匿名内部类 相同于创建了一个局部内部类
        new USB(){
            @Override
            public void service() {
                System.out.println("begin from 匿名内部类……");
            }
        }.service();



    }

}
