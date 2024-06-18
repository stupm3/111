package org.view;

import jdk.nashorn.internal.scripts.JO;
import org.service.ModiFy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyFrame extends JFrame {
    private String PASSWORD;
    private String USERNAME;
    private LoginFrame frame = new LoginFrame();
    private void initFrame(){
        this.setTitle("请输入账户信息");
        this.setSize( 520 , 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void setCommentMember(){
        ButtonGroup Group = new ButtonGroup();
        JLabel User = new JLabel("用户名");
        User.setBounds(150 , 159 , 40 , 26);
        this.add(User);

        JLabel PassWord = new JLabel("密码：");
        PassWord.setBounds(150 , 195 , 40 , 26);
        this.add(PassWord);


        JTextField UserText = new JTextField(16);
        UserText.setBounds(200 , 159 , 160 , 26);
        this.add(UserText);

        JPasswordField PasswordText = new JPasswordField(20);
        PasswordText.setBounds(200 , 195 , 160 , 26);
        this.add(PasswordText);


        JRadioButton Admin = new JRadioButton("管理员");
        Admin.setBounds(280 , 230 , 73 , 30);
        Group.add(Admin);

        JRadioButton Userr = new JRadioButton("用户");
        Userr.setBounds(200 , 230 , 73 , 30);
        Userr.setSelected(true);
        Group.add(Userr);

        this.add(Admin);
        this.add(Userr);

        JButton OK = new JButton("OK");
        OK.setBounds(200, 300 , 100 , 50);
        OK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                USERNAME = UserText.getText();
                PASSWORD = PasswordText.getText();
                if(Userr.isSelected()){
                    if(PASSWORD.equals("") || USERNAME.equals("")){
                        JOptionPane.showMessageDialog(null,"请输入正确的账户信息");
                    }
                    if(ModiFy.modifyUser(USERNAME,PASSWORD)){
                        JOptionPane.showMessageDialog(null,"修改成功");
                        dispose();
                        return;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"该账户不存在");
                    }
                }
                else if(Admin.isSelected()){
                    if(PASSWORD.equals("") || USERNAME.equals("")){
                        JOptionPane.showMessageDialog(null,"请输入正确的账户信息");
                    }
                    if(ModiFy.modifyUser(USERNAME,PASSWORD)){
                        JOptionPane.showMessageDialog(null,"修改成功");
                        dispose();
                        return;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"该账户不存在");
                    }
                    }
                }
            }
        );
        this.add(OK);
    }
    public ModifyFrame(){
        initFrame();
        setCommentMember();
    }
}
