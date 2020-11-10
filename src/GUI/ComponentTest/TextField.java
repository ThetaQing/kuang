package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;
// 文本框
public class TextField extends JFrame {
    public TextField() throws HeadlessException {
        Container container = this.getContentPane();

        JTextField textField = new JTextField("hello");
        JTextField textField2 = new JTextField("world");

        container.add(textField,BorderLayout.NORTH);
        container.add(textField2,BorderLayout.SOUTH);

        this.setVisible(true);
        this.setBounds(100,100,300,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
