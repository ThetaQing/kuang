package GUI.Application;

import javax.swing.*;
import java.net.URL;

// 数据中心
public class GluttonousSnakeData {
    // 相对路径
    // 绝对路径  / 相对于当前的项目
    public static URL headURL = GluttonousSnakeData.class.getResource("statics/header.png");
    public static ImageIcon header = new ImageIcon(headURL);

    public static URL upURL = GluttonousSnakeData.class.getResource("statics/up.png");
    public static URL downURL = GluttonousSnakeData.class.getResource("statics/down.png");
    public static URL leftURL = GluttonousSnakeData.class.getResource("statics/left.png");
    public static URL rightURL = GluttonousSnakeData.class.getResource("statics/right.png");

    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);

    public static URL bodyURL = GluttonousSnakeData.class.getResource("statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    public static URL foodURL = GluttonousSnakeData.class.getResource("statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);



}
