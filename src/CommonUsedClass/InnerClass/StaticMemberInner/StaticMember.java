package CommonUsedClass.InnerClass.StaticMemberInner;


// 静态成员内部类
public class StaticMember {
    private String name;
    private int age;
    private String school;
    public StaticMember() {
    }

    public StaticMember(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;

    }

    // 静态内部类，级别和外部类相同
    static class Inner{
        private String school;

        public Inner() {
        }

        public Inner(String school) {
            this.school = school;
        }

        public void show(){
            // 只能通过创建外部类的对象才能访问外部类的属性
            StaticMember sm = new StaticMember("zhangsan",18,"PekingUniversity");

            System.out.println("内部类学校： "+school);
            System.out.println("外部类名字： " +sm.school);
        }
    }

}
