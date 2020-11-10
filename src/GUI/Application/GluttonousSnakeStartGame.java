package GUI.Application;

import javax.swing.*;

// 游戏的主启动类
public class GluttonousSnakeStartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        // 正常游戏都得在面板上
        frame.add(new GluttonousSnakeGamePanel());



        frame.setVisible(true);
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);  // 窗口大小不可变
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
