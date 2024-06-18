import javax.swing.*;
import java.awt.*;

public class M extends JPanel {
    // 定义线段的起点和终点坐标
    private int startX, startY, endX, endY;

    // 构造函数，接收线段的坐标作为参数
    public M(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    // 重写paintComponent方法来绘制图形
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 调用父类的paintComponent方法，用于绘制背景

        // 绘制线段
        g.drawLine(startX, startY, endX, endY);
    }

    public static void main(String[] args) {
        // 创建一个JFrame来包含我们的绘图面板
        JFrame frame = new JFrame("Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 创建一个DrawingPanel实例，并设置其大小和位置
        M drawingPanel = new M(50, 50, 200, 200);
        frame.add(drawingPanel);

        // 显示JFrame
        frame.setVisible(true);
    }
}