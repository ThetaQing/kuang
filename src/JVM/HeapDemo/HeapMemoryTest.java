package JVM.HeapDemo;

/**
 * @author SongQing
 * 测试虚拟机内存
 * 默认情况下：分配的总内存是电脑内存的1/4，而初始化的内存是电脑内存的1/64.
 * OOM:
 * 1、尝试扩大堆内存看结果  -Xms1024m -Xmx1024m -XX:+PrintGCDetails
 * 2、如果还OOM，分析内存，看一下哪个地方出现了问题（专业工具）
 *
 * -Xms1024m  设置初始化内存分配大小
 * -Xmx1024m  设置最大分配内存
 * -Xms1024m -Xmx1024m -XX:+PrintGCDetails 扩充堆内存后，
 * PSYoungGen  305664K
 * ParOldGen   699392K
 * 共 981.5MB 等于总分配的空间大小
 * 所以元空间逻辑上存在，物理上不存在
 */
public class HeapMemoryTest {
    public static void main(String[] args) {
        // 返回虚拟机试图使用的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        // 返回jvm的总内存
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 1799.5MB左右，电脑内存7.9G，约1/4
        System.out.println("max memory:" + maxMemory + "字节\t" + (maxMemory/(double)1024/1024) + "MB");
        // 123MB左右，电脑内存7.9G，约1/64
        System.out.println("total memory:" + totalMemory + "字节\t" + (totalMemory/(double)1024/1024) + "MB");

        //

    }
}
