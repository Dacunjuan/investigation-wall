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
public class Triangle extends Shape{
    int w, h, x, y;
    
    
    public Triangle(PaintSpace p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D)g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                                                                  RenderingHints.VALUE_ANTIALIAS_ON);
                     
                     g2d.setRenderingHints(rh);
        
        w=super.w-100;
        h=super.h-100;
        x=(super.getWidth()-w)/2;
        y=(super.getHeight()-h)/2;
        
        
        int [] xPoints={x+w/2, x, x+w};
        int [] yPoints={y+h-(int)Math.sqrt(Math.pow(w, 2)-Math.pow((w/2), 2)), y+h, y+h};
        int nPoints=3;
        System.err.println((int)Math.sqrt(Math.pow(w, 2)-Math.pow((w/2), 2)));
        g2d.setStroke(new BasicStroke(super.shapeBoder));
                     
        g2d.setColor(super.shapeBgColor);
        g2d.fillPolygon(xPoints, yPoints, nPoints);
        Color c  = g.getColor();
        g2d.setColor(super.shapeOutLineColor);
                     
        g2d.drawPolygon(xPoints, yPoints, nPoints);
        //g2d.drawString("Rect", 40, 55);
        g2d.setColor(c);
        g2d.setColor(Color.RED);
        g2d.fillRect(w/2+x, h/2+y, 2, 2);
        
        
        //g2d.drawPolygon(xPoints, yPoints, nPoints);
    }
    
    
}
