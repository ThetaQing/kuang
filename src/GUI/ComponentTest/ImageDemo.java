package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageDemo extends JFrame {
    public ImageDemo() throws HeadlessException {
        // 获取图片的地址
        JLabel label = new JLabel("ImageIcon");
        URL url = ImageDemo.class.getResource("/tx.jpg");  // 如果是在同级目录就不用/


        ImageIcon imageIcon = new ImageIcon(url);
        label.setIcon(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);


        Container container = getContentPane();
        container.add(label);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,100,200,200);
    }

}
