package IO.CharacterStreamBasic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStream {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("./src/IO/readme.md");
            FileWriter fw = new FileWriter("./src/IO/CharacterStreamBasic/fos.md");
            int data = 0;
            while((data = fr.read())!= -1){
                System.out.print((char)data);
                fw.write(data);
            }
            fr.close();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
