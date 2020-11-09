package AnnotationAndReflection.Application;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

// 通过反射获取泛型
public class OperateGeneric {

    // 定义两个泛型
    public void withGenericPara(Map<String,User> map, List<User> list){
        System.out.println("参数是泛型的函数");
    }
    public Map<String,User> withGenericReturn(){
        System.out.println("返回值是泛型的函数");
        return null;
    }



    public static void main(String[] args) {
        // 获取泛型函数
        try {
            System.out.println("=============参数类型为泛型===================");
            Method withGenericPara = OperateGeneric.class.getMethod("withGenericPara", Map.class, List.class);
            Type[] genericParameterTypes = withGenericPara.getGenericParameterTypes();  // 获得泛型的参数类型
            for (Type genericParameterType : genericParameterTypes) {
                System.out.println("##: "+genericParameterType);
                if(genericParameterType instanceof ParameterizedType){  // 如果这个参数是一种参数化类型
                    Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();  // 获得真实参数类型
                    for (Type actualTypeArgument : actualTypeArguments) {
                        System.out.println(actualTypeArgument);
                    }

                }
            }

            System.out.println("=============返回值类型为泛型===================");
            Method withGenericReturn = OperateGeneric.class.getMethod("withGenericReturn",null);
            Type genericReturnType = withGenericReturn.getGenericReturnType();  // 获取返回值泛型
            System.out.println(genericReturnType);
            if(genericReturnType instanceof  ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
