package org.view;

import org.service.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private String UserName;
    private String Password;
        public void setCommentMember(){
        ButtonGroup Group = new ButtonGroup();

        JLabel Title = new JLabel("校园导航系统");
        Title.setBounds(200,50,150,100);
        this.add(Title);

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

        JButton LoginButton = new JButton("登陆");
        LoginButton.setBounds( 100 , 300 , 80 , 50 );
        LoginButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserName = UserText.getText();
                Password = PasswordText.getText();
                if(Admin.isSelected()){
                    if(Login.loginAdmin(UserName,Password)){
                        JOptionPane.showMessageDialog(null,"登陆成功！");
                        new MainFrame(true);
                        dispose();
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "用户名或密码错误!");
                    return;
                }
                else{
                    if(Login.loginUser(UserName,Password)){
                        JOptionPane.showMessageDialog(null,"登录成功！");
                        new MainFrame(false);
                        dispose();
                        return;
                    }
                    JOptionPane.showMessageDialog(null,"用户名或密码错误！");
                }
            }
        });
        this.add(LoginButton);

        JButton RegisteButton = new JButton("注册");
        RegisteButton.setBounds(300,300,80,50);
        RegisteButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserName = UserText.getText();
                Password = PasswordText.getText();
                if(Admin.isSelected()){
                        if(Registe.adminRegiste(UserName,Password)){
                            JOptionPane.showMessageDialog( null ,  "注册成功！");
                            dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null , "注册失败");
                        }
                }
                else{
                        if(Registe.userRegiste(UserName , Password)){
                            JOptionPane.showMessageDialog(null , "注册成功！");
                            dispose();
                            return;
                        }
                        else{
                            JOptionPane.showMessageDialog(null , "注册失败");
                        }

                }
            }
        });
        this.add(RegisteButton);

        JButton Modify = new JButton("修改密码");
        Modify.setBounds(190,300,100,50);
        Modify.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ModifyFrame();
            }
        });
        this.add(Modify);

    }
    public void initFrame(){
        this.setTitle("登陆");
        this.setSize( 520 , 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public LoginFrame(){
        setCommentMember();
        initFrame();
    }

}
