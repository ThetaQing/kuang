package MultiThreading.Application;
/*
* 推导lambda表达式
* 1、定义一个函数式接口
*2、实现类
* */
public class LambdaEvolution {

    // 3、静态内部类
    static class Like2 implements ILike{
        @Override
        public void lambda() {
            System.out.println(" I Like Lambda2.");
        }
    }
    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 4、局部内部类
        class Like3 implements ILike{
            @Override
            public void lambda() {
                System.out.println(" I Like Lambda3.");
            }
        }

        like = new Like3();
        like.lambda();

        // 5、匿名内部类,没有类的名称，必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println(" I Like Lambda4.");

            }
        };
        like.lambda();

        // 6、用lambda简化,小括号内填参数，有参数的时候可以把括号也取消
        like =()->{
            System.out.println("I Like Lambda5.");
        };
        like.lambda();

        // 有参数的时候，可以省略小括号
        ILove love = a -> {
            System.out.println("I love num:"+a);
        };
        love.love(521);
        // 简化花括号的前提是只有一条语句，如果有多行就必须用代码块（即花括号包含）
        love = a-> System.out.println("I love new num："+a);
        love.love(525);



    }
}
// 1、定义一个函数式接口
interface ILike{
    void lambda();
}
// 2、实现类
class Like implements ILike{
    @Override
    public void lambda() {
        System.out.println(" I Like Lambda.");
    }
}

interface ILove{
    void love(int param);
}