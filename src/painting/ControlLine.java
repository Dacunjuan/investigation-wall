/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painting;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author tony
 */
public class ControlLine extends JComponent{

           public ControlLine() {
                     super();
                     this.setBackground(Color.red);
           }

    @Override
    protected void paintComponent(Graphics g) {
           super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
           Graphics2D g2d = (Graphics2D)g;
           //g2d.rotate(Math.toRadians(45), 150, 150);
           //g2d.rotate(Math.toRadians(30), (this.getWidth()-1)/2, (this.getHeight()-1)/2);
           g2d.setColor(Color.red);
          //System.out.println(this.getWidth());
          //System.out.println(this.getHeight());
           g2d.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
           
    }
          
          
}
