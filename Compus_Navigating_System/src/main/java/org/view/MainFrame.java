package org.view;

import org.dao.LoadLoaction;
import org.dao.LoadRoad;
import org.domin.Line;
import org.domin.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {
    private List<int[]> lines = new ArrayList<>();
    static private final int XDIFF = 240;
    static private final int YDIFF = 45;
    private JTextArea Description = new JTextArea();
    final private boolean AUTHORITY;
    private void initFrame(){
        this.setSize(1000,1000);
        this.setTitle("校园导航系统");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
    private void initButton(){
         JButton newAdd = new JButton("新增地点");
         newAdd.setBounds(30 , 50 , 100 , 50);
         newAdd.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if(!AUTHORITY){
                     JOptionPane.showMessageDialog(null,"无权限");
                     return;
                 }
                 dispose();
                 new AddFrame();

             }
         });
         this.add(newAdd);
         JButton removeButton = new JButton("删除地点");
         removeButton.setBounds(30 , 120 , 100 , 50);
         removeButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if(!AUTHORITY){
                     JOptionPane.showMessageDialog(null,"无权限");
                     return;
                 }
                 dispose();
                 new DeleteFrame();
             }
         });
         this.add(removeButton);
        JButton open = new JButton("开路");
        open.setBounds(30 , 190 , 100 , 50);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!AUTHORITY){
                    JOptionPane.showMessageDialog(null,"无权限");
                    return;
                }
                dispose();
                new AddRoadFrame();
            }
        });
        this.add(open);

        JButton remove = new JButton("断路");
        remove.setBounds(30 , 260 , 100 , 50);
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!AUTHORITY){
                    JOptionPane.showMessageDialog(null,"无权限");
                    return;
                }
                dispose();
                new RemoveRoadFrame();
            }
        });
        this.add(remove);

        JButton serch = new JButton("查找最短路");
        serch.setBounds(30 , 330, 100 , 50);
        serch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SerchFrame();
            }
        });
        this.add(serch);

        JButton change = new JButton("切换账户");
        change.setBounds(800,900,100 , 50);
        change.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });
        this.add(change);
    }
    public MainFrame(boolean authority){
        loadRoad();
        loadLocation();
        AUTHORITY = authority;
        initArea();
        initButton();
        initFrame();
    }
    private void initArea(){
        JTextArea Map = new JTextArea();
        Map.setEditable(false);
        Map.setBounds(200,30,800,800);
        this.add(Map);

        Description.setEditable(false);
        Description.setBounds(30,600,150,200);
        this.add(Description);


    }

    private void addLocation(int id , Location location){
        JButton newLocation = new JButton(location.getName());
        newLocation.setBounds(location.getX() + 200 , location.getY() + 30 , 80 , 30);
        newLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Description.setText(location.getDescription());
            }
        });
        this.add(newLocation);
        revalidate();
    }
    private void loadLocation(){
        List<Location> locaitons = LoadLoaction.getLoaction();
        Iterator<Location> iterator = locaitons.iterator();
        while(iterator.hasNext()){
            Location location = iterator.next();
            addLocation(location.getId() , location);
        }
    }
    private void loadRoad(){
        HashMap<Location , Location> Roads = LoadRoad.getRoads();
        Iterator<Location> iterator = Roads.keySet().iterator();
            while(iterator.hasNext()){
                Location location1 = iterator.next();
                Location location2 = Roads.get(location1);
                int[] line = {location1.getX() + XDIFF , location1.getY()+YDIFF , location2.getX()+XDIFF,location2.getY()+YDIFF };
                Line l = new Line(line[0],line[1],line[2],line[3]);
                l.setSize(1000,1000);
                this.add(l);
            }
    }
}
