package CollectionFrame.CollectionsDemo;

import java.util.*;

/**
 * Collections工具类测试
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(1);
        list.add(32);
        list.add(20);

        // 排序
        System.out.println("=============排序之前====================");
        System.out.println(list.toString());
        Collections.sort(list);
        System.out.println("=============排序之后====================");
        System.out.println(list.toString());

        // 二分查找，返回负数表示没有找到
        int i = Collections.binarySearch(list, 45);
        System.out.println(i);

        // copy复制,必须要保证两个数组长度一致，所以先循环遍历一次源数组给目标数组赋初值。
        ArrayList<Object> dest = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            dest.add(0);
        }
        Collections.copy(dest,list);
        System.out.println(dest.toString());

        // 反转
        Collections.reverse(list);
        System.out.println(list.toString());

        // 打乱，洗牌
        Collections.shuffle(list);
        System.out.println(list.toString());

        // list转数组
        // 这里new Integer[0]的0不影响，只是用到了参数的类型，如果给定的参数长度比
        // 实际返回的数组长度小，那么返回的是实际长度，如果给定参数长度比实际大，返回
        // 的数组长度是给定长度。
        Integer[] array = list.toArray(new Integer[0]);


        // 数组转list
        String[] names = {"zhangsan","lisi","wangwu"};
        // 受限集合，该集合不能添加和删除
        List<String> list1 = Arrays.asList(names);
        // 基本类型的数据转成集合时要用它的包装类
        //  如果直接用基本数据类型，那么集合中只有一个元素，就是那个数组
        int[] numsInt = {100,1,2};
        List<int[]> ints = Arrays.asList(numsInt);
        // 用包装类
        Integer[] numsInter = {100,1,2};
        List<Integer> Inters = Arrays.asList(numsInter);

    }
}
