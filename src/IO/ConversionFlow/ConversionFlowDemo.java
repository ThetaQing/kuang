package IO.ConversionFlow;

import java.io.*;

public class ConversionFlowDemo {
    public static void main(String[] args) {
        // 1 创建
        try {
            FileInputStream fis = new FileInputStream("./src/IO/readme.md");
            BufferedInputStream bis = new BufferedInputStream(fis);

            InputStreamReader isr = new InputStreamReader(bis,"utf-8");  // 指定编码
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("./src/IO/ConversionFlow/fos.md"),"gbk");  // 指定编码
            int data = 0;
            while ((data=isr.read())!=-1){
                System.out.print((char)data);
                osw.write(data);

            }
            isr.close();
            osw.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
