package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;

// 下拉框
public class ComboboxByAddItem extends JFrame {
    public ComboboxByAddItem() throws HeadlessException {

        Container container = this.getContentPane();

        JComboBox status = new JComboBox();

        status.addItem(null);
        status.addItem("即将上映");
        status.addItem("已下架");
        status.addItem("正在热映");

        container.add(status);

        this.setVisible(true);
        this.setBounds(100,100,300,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
