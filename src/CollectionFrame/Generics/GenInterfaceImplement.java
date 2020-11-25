package CollectionFrame.Generics;
// 泛型接口实现类
public class GenInterfaceImplement implements GenericsInterface<String>{
    @Override
    public String server(String s) {
        System.out.println(s);
        return s;
    }
}
