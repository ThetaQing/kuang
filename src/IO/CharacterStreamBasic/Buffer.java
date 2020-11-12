package IO.CharacterStreamBasic;

import java.io.*;

public class Buffer {
    public static void main(String[] args) {
        // 1 创建缓冲流
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/IO/readme.md"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/IO/CharacterStreamBasic/fos.md"));
            bw.newLine();
            String s = null;
            while((s = br.readLine())!= null){
                System.out.println(s);
                bw.newLine();
                bw.write(s);
            }
            bw.close();
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2 读取
    }
}
