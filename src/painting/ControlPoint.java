/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painting;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author tony
 */
public class ControlPoint extends JComponent{

           public ControlPoint() {
                     super();
                    // this.setBackground(Color.red);
           }

           @Override
           protected void paintComponent(Graphics g) {
                     super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
                     Graphics2D g2d = (Graphics2D)g;
                     
                     RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                                                                  RenderingHints.VALUE_ANTIALIAS_ON);
                     
                     g2d.setRenderingHints(rh);
                     
                     //g2d.rotate(Math.toRadians(45), (this.getWidth())/2, (this.getHeight())/2);
                     
                     //g.setPaintMode();
                     g2d.setColor(getBackground());
                     
                     g2d.fillOval(0, 0, this.getWidth(), this.getHeight());
           }

          
           
           
    
}
