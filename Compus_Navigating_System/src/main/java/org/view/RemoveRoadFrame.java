package org.view;

import org.service.AddRoad;
import org.service.RemoveRoad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveRoadFrame extends JFrame {
    private MainFrame mainFrame = new MainFrame(true);
    private String name1;
    private String name2;
    private JTextField NAME1 = new JTextField(10);
    private JTextField NAME2 = new JTextField(10);

    private void initFrame(){
        this.setSize(300,200);
        this.setTitle("请输入地点信息");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }
    private void initview(){
        JLabel LOCAITON1 = new JLabel("地点1名称：");
        LOCAITON1.setBounds(40, 40, 70, 30);
        this.add(LOCAITON1);


        NAME1.setBounds(150, 40, 100, 30);
        this.add(NAME1);

        JLabel LOCATION2 = new JLabel("地点2名称：");
        LOCATION2.setBounds(40, 80, 70, 30);
        this.add(LOCATION2);

        NAME2.setBounds(150, 80, 100, 30);
        this.add(NAME2);

    }
    private void initButton(){
        JButton ok = new JButton("确认");
        ok.setBounds(90,120, 70,30);
        ok.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                name1 = NAME1.getText();
                name2 = NAME2.getText();
                if(name1.equals(name2)){
                    JOptionPane.showMessageDialog(null,"重复的地名");
                    return;
                }
                if(RemoveRoad.removeRoad(name1,name2)){
                    mainFrame.dispose();
                    JOptionPane.showMessageDialog(null,"删除成功");
                    new MainFrame(true);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"两点间无路径或地点不存在");
                }
            }
        });
        this.add(ok);
    }
    public RemoveRoadFrame(){
        initFrame();
        initview();
        initButton();
    }

}

