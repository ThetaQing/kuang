package CommonUsedClass.InnerClass.MemberInner;

public class Main {
    public static void main(String[] args) {

        MemberInnerClass.Inner inner = new MemberInnerClass("zhangsan", 18,"Peking University").new Inner("CSU");
        inner.show();
    }
}
