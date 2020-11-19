package CommonUsedClass.InnerClass.LocalInner;

public class Local {
    private String name = "zhangsan";
    private int age = 18;

    public void show(){
        // 定义局部变量
        String address = "shenzhen";
        String name = "lisi";
        class Inner{
            private String phone = "110";
            private void show(){
                System.out.println("外部类中的名字： "+Local.this.name);
                System.out.println("内部类中的名字： "+name);
                System.out.println("age = "+age);
                System.out.println("address = "+address);
            }
        }
        Inner inner = new Inner();  // 创建一个对象
        inner.show();
    }
}
