/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package investigationwall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ColorChooserWindow extends JDialog{
    
    public ColorChooserWindow(JFrame frame,String title, boolean isLocked,ChooseColorLabel colorLb) {
        super(frame,title,isLocked);
        Dimension myScreen = Toolkit.getDefaultToolkit().getScreenSize();

        this.setBounds(0, 0, 800, 500);
        this.setLocation(myScreen.width/2-this.getSize().width/2,
                         myScreen.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        JPanel colorChooserPnl = new JPanel(new BorderLayout());
        JPanel ButtonPnl = new JPanel();
        
        JColorChooser colorChooser = new JColorChooser();
        JButton colorChooserBtnOK = new JButton("OK");
        colorChooserBtnOK.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Color color = colorChooser.getColor();
                colorLb.setBackground(color);
                ColorChooserWindow.this.setVisible(false);
            }
        });
        JButton colorChooserBtnCanel = new JButton("Canel");
        colorChooserBtnCanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                ColorChooserWindow.this.setVisible(false);
            }
        });
        
        ButtonPnl.add(colorChooserBtnOK);
        ButtonPnl.add(colorChooserBtnCanel);
        colorChooserPnl.add(colorChooser,BorderLayout.CENTER);
        colorChooserPnl.add(ButtonPnl,BorderLayout.SOUTH);
        this.add(colorChooserPnl);
        
    }
}
