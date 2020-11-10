package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;

public class JPanelDemo extends JFrame {
    public JPanelDemo() throws HeadlessException {
        Container container = getContentPane();
        container.setLayout(new GridLayout(4,2,10,10));

        JPanel panel1 = new JPanel(new GridLayout(1,3));
        JPanel panel2 = new JPanel(new GridLayout(1,3));
        JPanel panel3 = new JPanel(new GridLayout(1,3));
        JPanel panel4 = new JPanel(new GridLayout(1,3));

        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel2.add(new JButton("1"));
        panel2.add(new JButton("1"));
        panel2.add(new JButton("1"));
        panel3.add(new JButton("1"));
        panel3.add(new JButton("1"));
        panel3.add(new JButton("1"));
        panel4.add(new JButton("1"));
        panel4.add(new JButton("1"));
        panel4.add(new JButton("1"));

        container.add(panel1);  // 添加面板
        container.add(panel2);  // 添加面板
        container.add(panel3);  // 添加面板
        container.add(panel4);  // 添加面板

        this.setVisible(true);
        this.pack();
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
