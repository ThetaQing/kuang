package IO.FileClass;

import java.io.File;

// 递归查询、递归删除
public class Recursion {
    public static void main(String[] args) {
        recursiveTraversal("G:\\Code\\Java\\kuang\\src\\IO\\FileClass\\Dir1\\");
        recursiveDelete("G:\\Code\\Java\\kuang\\src\\IO\\FileClass\\Dir1\\");
    }
    // 递归遍历
    public static void recursiveTraversal(String path){

        File rootDir = new File(path);
        File[] rootFiles = rootDir.listFiles();
        for (File rootFile : rootFiles) {
            if (rootFile.isDirectory()) {
                recursiveTraversal(rootFile.getPath());
            }
            else{
                System.out.println(rootFile.getAbsolutePath());
            }
        }
    }
    // 递归删除
    public static void recursiveDelete(String path){
        File rootDir = new File(path);
        File[] rootFiles = rootDir.listFiles();
        for (File rootFile : rootFiles) {
            if (rootFile.isDirectory() ) {
                if(rootFile != null)
                    recursiveDelete(rootFile.getPath());
            }
            else{
                System.out.print("文件"+rootFile.getAbsolutePath());
                System.out.println("是否被删除？ "+rootFile.delete());
            }
        }
        System.out.println("文件夹：" + rootDir.getAbsolutePath());
        System.out.println("是否被删除？ "+rootDir.delete());

    }
}
