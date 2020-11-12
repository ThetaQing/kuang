package IO.CharacterStreamBasic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Print {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter("./src/IO/CharacterStreamBasic/fos.md");
            pw.println(97);  // 打印
            pw.println(true);

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
