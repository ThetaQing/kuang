package GUI.ComponentTest;

import javax.swing.*;
import java.awt.*;

public class IconDemo extends JFrame implements Icon{

    private int width;
    private int height;
    // 无参构造
    public IconDemo() throws HeadlessException {
    }
    // 有参构造
    public IconDemo(int width, int height) throws HeadlessException {
        this.width = width;
        this.height = height;
    }

    public void init()
    {
        IconDemo iconDemo = new IconDemo(15,15);
        // 把图标放在标签上也可以放在按钮上
        JLabel label = new JLabel("icon", iconDemo, SwingConstants.CENTER);
        Container container = getContentPane();
        container.add(label);

        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillOval(x,y,width,height);  // 画笔画一个圈

    }

    @Override
    public int getIconWidth() {
        return this.width;
    }

    @Override
    public int getIconHeight() {
        return this.height;
    }
}
