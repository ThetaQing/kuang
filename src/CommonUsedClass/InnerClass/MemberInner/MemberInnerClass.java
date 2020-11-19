package CommonUsedClass.InnerClass.MemberInner;

// 成员内部类
public class MemberInnerClass {
    private String name;
    private int age;
    private String school;
    public MemberInnerClass() {
    }

    public MemberInnerClass(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }





    class Inner{
        private String school;

        public Inner() {
        }

        public Inner(String school) {
            this.school = school;
        }

        public void show(){
            System.out.println(name);
            System.out.println(age);
            System.out.println("内部类学校： "+school);
            System.out.println("外部类名字： " + MemberInnerClass.this.school);
        }
    }

}
