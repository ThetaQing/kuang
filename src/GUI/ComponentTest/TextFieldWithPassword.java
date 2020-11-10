package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;
// 密码框
public class TextFieldWithPassword extends JFrame {
    public TextFieldWithPassword() throws HeadlessException {
        Container container = this.getContentPane();

        JPasswordField passwordField = new JPasswordField();  // ***
        passwordField.setEchoChar('*');

        container.add(passwordField);

        this.setVisible(true);
        this.setBounds(100,100,300,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
