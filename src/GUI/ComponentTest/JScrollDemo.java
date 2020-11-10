package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;

public class JScrollDemo extends JFrame {
    public JScrollDemo() throws HeadlessException {
        Container container = this.getContentPane();
        // 文本域
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setText("welcome");

        // 带有滚动条的面板
        //JScrollPane scrollPane = new JScrollPane(textArea);  // 直接指定显示对象
        JScrollPane scrollPane = new JScrollPane();  // 在面板里面添加文本域
        scrollPane.setViewportView(textArea);  // 创建对象，利用该方法放置组件对象

        container.add(scrollPane);



        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100,100,200,200);
    }

}
