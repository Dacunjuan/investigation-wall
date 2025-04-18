package investigationwall;

import javax.swing.*;
import javax.swing.JFrame.*;

import java.awt.*;
import java.awt.event.*;
import java.nio.MappedByteBuffer;
import java.awt.Component.*;
public class MainFrame extends JFrame{
    public int Width = 1000;
    public int Height = 1000;

    public MainFrame(String title)
    {
        Dimension myScreen = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setTitle(title);

        this.setBounds(0, 0, 1100, 700);
        this.setLocation(myScreen.width/2-this.getSize().width/2,
                         myScreen.height/2-this.getSize().height/2);
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        
        this.setBackground(new java.awt.Color(131, 131, 131));
        this.setPreferredSize(new java.awt.Dimension(1050, 600));
        
        
        
        
        // this.addComponentListener(new java.awt.event.ComponentAdapter() {
        //     public void componentMoved(java.awt.event.ComponentEvent evt) {
        //         formComponentMoved(evt);
        //     }
        // });
        // this.addWindowFocusListener(new java.awt.event.WindowFocusListener(){
        //     public void windowGainedFocus(java.awt.event.WindowEvent evt) {
        //         formWindowGainedFocus(evt);
        //     }
        //     public void windowLostFocus(java.awt.event.WindowEvent evt) {
        //     }
        // });
    }
    
   
}
