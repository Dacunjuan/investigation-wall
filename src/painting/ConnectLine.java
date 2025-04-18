/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painting;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
/*
 *
 * @author tony
 */
public class ConnectLine extends JComponent{
           int abStartX, abStartY, abEndX, abEndY;
           PaintSpace parent;
           int reStartX, reStartY, reEndX, reEndY;
           
           public ConnectLine(PaintSpace p, Point lineStart, Point lineEnd) {
                     super();
                     this.parent=p;
                     
//                     this.absolute_Start=lineStart;
//                     this.absolute_End=lineEnd;
                     
                     this.abStartX=lineStart.x;
                     this.abStartY=lineStart.y;
                     this.abEndX=lineEnd.x;
                     this.abEndY=lineEnd.y;
                     
                     this.reStartX=lineStart.x;
                     this.reStartY=lineStart.y;
                     this.reEndX=lineEnd.x;
                     this.reEndY=lineEnd.y;
                     
                     
                     this.setLocation(Integer.min(abStartX, abEndX), Integer.min(abStartY, abEndY));
                     this.setSize(Math.abs(abEndX-abStartX)+2, Math.abs(abEndY-abStartY)+2);
                     
                     this.reStartX-=getX();
                     this.reStartY-=getY();
                     this.reEndX-=getX();
                     this.reEndY-=getY();
                     
                     parent.add(this);
                     parent.setLayer(this, 100,0);
                     repaint();
           }
           
           public void setLineStart(Point s){
                    this.abStartX=s.x;
                    this.abStartY=s.y;
                    this.reStartX=s.x;
                    this.reStartY=s.y;
                    
                    this.setLocation(Integer.min(abStartX, abEndX), Integer.min(abStartY, abEndY));
                    this.setSize(Math.abs(abEndX-abStartX)+2, Math.abs(abEndY-abStartY)+2);
                    
                    this.reStartX=abStartX-getX();
                    this.reStartY=abStartY-getY();
                    this.reEndX=abEndX-getX();
                     this.reEndY=abEndY-getY();
           }
           
           public void setLineEnd(Point s){
                    this.abEndX=s.x;
                    this.abEndY=s.y;
                    this.reEndX=s.x;
                    this.reEndY=s.y;
                    
                    this.setLocation(Integer.min(abStartX, abEndX), Integer.min(abStartY, abEndY));
                    this.setSize(Math.abs(abEndX-abStartX)+2, Math.abs(abEndY-abStartY)+2);
                    
                    this.reStartX=abStartX-getX();
                    this.reStartY=abStartY-getY();
                    this.reEndX=abEndX-getX();
                    this.reEndY=abEndY-getY();
           }
                   
           @Override
           protected void paintComponent(Graphics g) {
                     super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
                     Graphics2D g2d = (Graphics2D)g;
                     RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                                                                  RenderingHints.VALUE_ANTIALIAS_ON);
                     
                     g2d.setRenderingHints(rh);
                     //g2d.drawRect(0, 0, reEndX-reStartX, reEndY-reStartY);
                     g2d.drawLine(reStartX, reStartY, reEndX, reEndY);
                     //g2d.drawRect(0, 0, Math.abs(lineEnd.x-lineStart.x), Math.abs(lineEnd.y-lineStart.y));
           }


}
