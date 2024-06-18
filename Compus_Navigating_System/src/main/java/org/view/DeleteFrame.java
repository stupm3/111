package org.view;

import org.service.RemoveLocation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFrame extends JFrame {
    private MainFrame frame = new MainFrame(true);
    public DeleteFrame() {
        initFrame();
        initButton();
    }
    private void initFrame(){
        this.setSize(300,200);
        this.setTitle("请输入地点信息");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }
    private void initButton(){
        JLabel LOCAITON = new JLabel("地点名称：");
        LOCAITON.setBounds(40, 40, 70, 30);
        this.add(LOCAITON);

        JTextField NAME = new JTextField(10);
        NAME.setBounds(150, 40, 100, 30);
        this.add(NAME);

        JButton Defect = new JButton("确认");
        Defect.setBounds(80,100, 80,20);
        Defect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = NAME.getText();
                if(RemoveLocation.removeLocation(name)){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    frame.dispose();
                    dispose();
                    new MainFrame(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"该地点不存在");
                }
            }
        });
        this.add(Defect);
    }
}
