package org.view;

import org.service.AddLoaction;
import org.dao.JDBC;
import org.domin.Location;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JDialog {
    private boolean flag;
    private Location loc;
   private MainFrame frame = new MainFrame(true);
    private void initFrame(){
        this.setSize(550,350);
        this.setTitle("请输入地点信息");
        this.setLayout(null);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
    }

    private void setView(){
        JLabel LOCAITON = new JLabel("地点名称：");
        LOCAITON.setBounds(40, 40, 70, 30);
        this.add(LOCAITON);

        JLabel XLOCATION = new JLabel("地点x坐标：");
        XLOCATION.setBounds(40, 80, 70, 30);
        this.add(XLOCATION);

        JLabel YLOCAITON = new JLabel("地点y坐标");
        YLOCAITON.setBounds(40, 120, 70, 30);
        this.add(YLOCAITON);

        JLabel DESCRIPTION = new JLabel("地点描述");
        DESCRIPTION.setBounds(40, 160, 70, 30);
        this.add(DESCRIPTION);

        JTextField NAME = new JTextField(10);
        NAME.setBounds(150, 40, 100, 30);
        this.add(NAME);

        JTextField XLOCATIONT = new JTextField(5);
        XLOCATIONT.setBounds(150, 80, 50, 30);
        this.add(XLOCATIONT);
        JTextField YLOCATIONT = new JTextField(5);
        YLOCATIONT.setBounds(150, 120, 50, 30);
        this.add(YLOCATIONT);

        JTextField DESCRIPTIONT = new JTextField(20);
        DESCRIPTIONT.setBounds(150, 160, 200, 30);
        this.add(DESCRIPTIONT);



        JButton Defect = new JButton("确认");
        Defect.setBounds(150,200, 80,40);
        Defect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String LocationName = NAME.getText();
                int LocationY = Integer.parseInt(YLOCATIONT.getText());
                int LocationX = Integer.parseInt(XLOCATIONT.getText());
                String Description = DESCRIPTIONT.getText();
                Location location = new Location(LocationName,LocationX,LocationY,Description);
                loc = location;
                if(LocationX >= 800 || LocationY >= 800 || LocationX < 0 || LocationY < 0){
                    JOptionPane.showMessageDialog(null,"位置信息有误");;
                    return;
                }
                if(AddLoaction.addLocation(location)){
                    JOptionPane.showMessageDialog(null,"添加成功");
                    dispose();
                    frame.dispose();
                    new MainFrame(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"该地点已存在或与存在的地点相隔过近");
                }
            }
        });
        this.add(Defect);
    }

    public AddFrame(){
        setView();
        initFrame();
        this.setVisible(true);
    }
    public Location getLoaction(){
        return loc;
    }
    public boolean isAdd(){
        return this.flag;
    }
}
