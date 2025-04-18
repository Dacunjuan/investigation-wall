/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painting;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author tony
 */
public class PushPin extends Shape{
    private final BufferedImage image;
    int x, y, w, h;
    PaintSpace parent;
    public PushPin(PaintSpace p, int x, int y, int w, int h) {
          super(p, x, y, w, h);
          parent=p;
          
          BufferedImage i = null;
          try {
              i=ImageIO.read(new File("src/investigationwall/Images/the_pushpin.png"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          image=i;
          
    }

    @Override
    protected void paintComponent(Graphics g) {
          super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
          Graphics2D g2d = (Graphics2D)g;
          x=(super.getWidth()-w)/2;
          y=(super.getHeight()-h)/2;
          g2d.drawImage(image, 0, 0, this);
    }
    
}
