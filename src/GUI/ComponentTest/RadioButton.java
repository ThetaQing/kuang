package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
// 单选框，关键在于分组
public class RadioButton extends JFrame {
    public RadioButton() throws HeadlessException {
        Container container = getContentPane();
        // 将一个图片变为图标
        URL url = NormalButton.class.getResource("/tx.jpg");  // 获取图标
        ImageIcon icon = new ImageIcon(url);

        // 单选框
        JRadioButton radioButton1 = new JRadioButton("单选框1");
        JRadioButton radioButton2 = new JRadioButton("单选框2");
        JRadioButton radioButton3 = new JRadioButton("单选框3");

        // 由于单选框只能选择一个，所以一般会对其分组，若button在一个组，组内只有一个可以被选中
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        // 添加组件
        container.add(radioButton1,BorderLayout.NORTH);
        container.add(radioButton2,BorderLayout.CENTER);
        container.add(radioButton3,BorderLayout.SOUTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100,100,300,300);
    }
}
