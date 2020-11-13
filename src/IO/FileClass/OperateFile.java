package IO.FileClass;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;

// 操作文件
public class OperateFile {
    public static void main(String[] args) {
        separator();
        //fileOpe();
        dirOpe();


    }
    // 打印两个分隔符
    public static void separator() {
        System.out.println("路径分割符： "+File.pathSeparator);
        System.out.println("文件分隔符： "+File.separator);

    }
    // 文件操作
    public static void fileOpe(){
        // 1 创建文件
        File file = new File("./src/IO/FileClass/test.txt");
        if (!file.exists()) {  // 如果文件不存在，创建该文件
            try {
                boolean newFile = file.createNewFile();
                System.out.println("是否创建成功？ "+newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 2 写入文件
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("hello");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3、获取文件信息
        // 获取绝对路径
        System.out.println(file.getName());//名字
        System.out.println(file.getAbsolutePath());//路径
        System.out.println(file.getPath());//写啥是啥
        System.out.println(file.getParent());//父母
        System.out.println(file.length());// 长度
        // 4 查询
        System.out.println(file.canWrite());//是否可写
        System.out.println(file.canExecute());  // 是否可执行
        System.out.println(file.isFile());
        // 5 删除文件
        //if (file.exists()) {
        //    System.out.println(file.delete());
        //}
        file.deleteOnExit();  // 使用jvm退出时删除
        try {
            Thread.sleep(5000);  // 休眠一会会
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");


    }
    // 文件夹操作
    public static void dirOpe(){
        // 创建
        File dir = new File("./src/IO/FileClass/Dir");
        if (!dir.exists()) {
            System.out.println("是否创建成功？"+dir.mkdir());  // 只能创建单级目录 mkdirs 创建多级目录

        }

        // 在该文件夹下创建文件


        File file = new File(dir.getPath() + "/1.txt");
        File file2 = new File(dir.getPath() + "/2.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 获取信息
        System.out.println("path = "+dir.getPath());
        System.out.println("name = "+dir.getName());
        System.out.println("absolute path = "+dir.getAbsolutePath());
        // 遍历查询
        String[] list = dir.list();
        System.out.println("list length = "+list.length);
        for (String s : list) {
            System.out.println("file = "+s);

        }


        // 删除
        if (dir.exists()) {
            System.out.println("是否删除文件夹？ "+dir.delete());  // 只能删除空目录
        }
        dir.deleteOnExit();

        File movies = new File("D:\\Movies\\");
        File[] movie = movies.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getPath().endsWith(".mp4")) {
                    return true;
                }
                return false;
                // 默认false表示所有文件都不返回
            }
        });
        for (File m : movie) {
            System.out.println(m.getName());
        }


    }
}
