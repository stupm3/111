package org.view;

import org.service.Serch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class SerchFrame extends JFrame {
    private JButton ok = new JButton("确认");
    private JTextField start = new JTextField(10);
    private JTextField end = new JTextField(10);
    private String source;
    private String target;
    private MainFrame frame = new MainFrame(true);
    private void initFrame(){
        this.setSize(300,200);
        this.setTitle("请输入地点信息");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }
    private void initTestFild(){
        JLabel Start = new JLabel("起点名称：");
        Start.setBounds(40, 20, 70, 30);
        this.add(Start);

        JLabel End = new JLabel("终点名称");
        End.setBounds(40, 60, 70, 30);
        this.add(End);


        start.setBounds(150, 20, 100, 30);
        this.add(start);

        end.setBounds(150, 60, 100, 30);
        this.add(end);
    }
    private void initJButton(){
        ok.setBounds(80, 120, 100, 50);
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                source = start.getText();
                target = end.getText();
                if((source.equals("")||target.equals("")) || source.equals(target)){
                    JOptionPane.showMessageDialog(null,"请输入正确的地名");
                    dispose();
                    return;
                }
                new Serch(source,target);
                String path = Serch.getResult(Serch.getPath());
                if(path.isEmpty()){
                    JOptionPane.showMessageDialog(null,"两点间无路");
                    dispose();
                    return;
                }
                JOptionPane.showMessageDialog(null,"两点间的最短路径为：" + path);
                frame.dispose();
                dispose();
                new MainFrame(true);
            }
        });
        this.add(ok);
    }
    public SerchFrame(){
        initFrame();
        initTestFild();
        initJButton();
    }
}
