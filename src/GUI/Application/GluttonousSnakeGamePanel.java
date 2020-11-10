package GUI.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

// 游戏面板
public class GluttonousSnakeGamePanel extends JPanel implements KeyListener, ActionListener {
    // 定义蛇的数据结构
    int length;  // 蛇的长度
    int[] snakeX = new int[600]; //蛇的x坐标25*25
    int[] snakeY = new int[500]; //蛇的y坐标25*25
    String fx;  // 初始方向向右
    // 游戏当前的状态，开始、停止
    boolean isStart;
    // 游戏失败状态
    boolean isFail;
    // 食物坐标
    int foodx;
    int foody;
    // 积分
    int score;
    Random random = new Random();  // 随机数种子
    // 定时器
    Timer timer = new Timer(100,this);  // 100ms执行一次，监听的是当前面板

    // 构造器
    public GluttonousSnakeGamePanel() {
        init();
        // 获得焦点和键盘事件
        this.setFocusable(true);  // 获得焦点
        this.addKeyListener(this);  // 获得键盘监听事件，监听器在该类中实现，如果是另外写的一个类，就new一个类就好了
        timer.start();  // 游戏一开始定时器就启动
    }
    // 定时器

    // 初始化方法
    public void init(){
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100;  // 脑袋的坐标
        snakeX[1] = 75; snakeY[1] = 100;  // 第一个身体的坐标
        snakeX[2] = 50; snakeY[2] = 100;  // 第二个身体的坐标
        fx = "R";
        isStart = false;  // 默认不开始

        // 初始化食物在这个界面上
        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);

        isFail = false;  // 默认游戏进行中
        score = 0;  // 成绩初始化为0
    }


    // 绘制面板.，游戏中的所有东西都使用该画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // 清屏功能
        // 绘制静态面板
        this.setBackground(Color.white);
        GluttonousSnakeData.header.paintIcon(this,g,25,11);  // 头部广告栏画上去，this表示画在当前面板

        g.fillRect(25,75,850,600);  // 画游戏界面

        // 画食物,食物先于脑袋画要好看一些
        GluttonousSnakeData.food.paintIcon(this,g,foodx,foody);

        // 画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 12));  // 设置字体
        g.drawString("得分: "+score, 800, 100);  // 提示信息

        // 把小蛇画上去，蛇头方向判断
        if(fx.equals("R"))
            GluttonousSnakeData.right.paintIcon(this,g,snakeX[0],snakeY[0]);  // 蛇头初始化向右
        else if(fx.equals("L"))
            GluttonousSnakeData.left.paintIcon(this,g,snakeX[0],snakeY[0]);  // 左
       else if(fx.equals("U"))
            GluttonousSnakeData.up.paintIcon(this,g,snakeX[0],snakeY[0]);  // 上
       else if(fx.equals("D"))
            GluttonousSnakeData.down.paintIcon(this,g,snakeX[0],snakeY[0]);  // 下
        // 身体用for循环
        for (int i = 1; i <length; ++i)
        {
            GluttonousSnakeData.body.paintIcon(this,g,snakeX[i],snakeY[i]);  // 身体的坐标
        }

        if (!isStart) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));  // 设置字体
            g.drawString("按下空格键开始游戏", 300, 300);  // 提示信息
        }
        if(isFail){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));  // 设置字体
            g.drawString(String.format("得分%d，按下空格重新开始",score), 200, 400);  // 提示信息
        }

    }

    // 键盘监听
    @Override
    public void keyPressed(KeyEvent e) {
        // 首先监听空格
        int keyCode = e.getKeyCode();  // 获取键盘按键编码
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail){// 如果游戏已结束
                init();  // 重新开始游戏
            }
            else {
                isStart = !isStart;  // 取反
            }
            repaint();  // 如果状态发生变化，需要重新绘制

        }
        // 小蛇移动
        if(keyCode == KeyEvent.VK_UP){
            fx = "U";
        }else if(keyCode == KeyEvent.VK_DOWN){
            fx = "D";
        }else if(keyCode == KeyEvent.VK_LEFT){
            fx = "L";
        }else if(keyCode == KeyEvent.VK_RIGHT){
            fx = "R";
        }
    }
    // 事件监听---需要通过固定事件来刷新
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && !isFail){  // 开始状态，小蛇动起来,非失败状态

            //吃食物判断
            if(snakeX[0] == foodx && snakeY[0] == foody)
            {
                length += 1;
                score += 1;
                // 再次随机生成食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }
            // 身体移动：就是后一节往前一节走
            for(int i = length - 1; i > 0; --i){  // 后一节移到前一节的位置
                snakeX[i] = snakeX[i - 1];  // 向前移动一节
                snakeY[i] = snakeY[i - 1];
            }

            // 头部转动
            if(fx == "R") {
                snakeX[0] += 25;
                if(snakeX[0] > 850) snakeX[0] = 25;  // 边界检查
            }else if(fx == "L"){
                snakeX[0] -= 25;
                if(snakeX[0] < 25) snakeX[0] = 850;  // 边界检查
            }else if(fx == "U"){
                snakeY[0] -= 25;
                if(snakeY[0] < 75) snakeY[0] = 650;  // 边界检查
            }else if(fx == "D"){
                snakeY[0] += 25;
                if(snakeY[0] > 650) snakeY[0] = 75;  // 边界检查
            }
            // 失败判定，撞到自己就算失败
            for(int i = 1; i < length; ++i){
                if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i])
                    isFail = true;
            }

            repaint();

        }
        timer.start();  // 定时器开启

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }


}

