package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
// 普通按钮，图片按钮
public class NormalButton extends JFrame {
    public NormalButton() throws HeadlessException {
        Container container = getContentPane();
        // 将一个图片变为图标
        URL url = NormalButton.class.getResource("/tx.jpg");  // 获取图标
        ImageIcon icon = new ImageIcon(url);

        // 把这个图片放到按钮上
        JButton button01 = new JButton("JButton01");
        button01.setIcon(icon);
        button01.setToolTipText("图片按钮");  //按钮提示信息

        container.add(button01);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100,100,300,300);
    }
}
