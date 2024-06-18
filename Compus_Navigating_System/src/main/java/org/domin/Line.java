package org.domin;

import javax.swing.*;
import java.awt.*;

public class Line extends JComponent {
    private int SX;
    private int SY;
    private int EX;
    private int EY;
    public Line(int SX , int SY , int EX , int EY){
        this.SX = SX;
        this.SY = SY;
        this.EX = EX;
        this.EY = EY;
        this.setLayout(null);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 调用父类的paintComponent方法，用于绘制背景
        // 绘制线段
        g.drawLine(SX , SY , EX , EY);
    }
}
