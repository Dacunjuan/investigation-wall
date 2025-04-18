/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investigationwall.imginput;

import java.awt.*;
import java.awt.BasicStroke;
import javax.swing.JComponent;
public class drawBorderPoint extends JComponent{
    
    public drawBorderPoint()
    {
        super();
        this.setBackground(Color.black);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
    }
}
