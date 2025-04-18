/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package painting;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author hanshen
 */
public class RotatedTextArea extends JTextArea{
    public double angle = 0;
    //int w;
    //int h;
    
    @Override
    public void paintComponent(Graphics g) {
    //double angle = -Math.PI/3;
    //double mid = Math.min(this.getWidth(), this.getHeight())/2.0;
    Graphics2D g2d = (Graphics2D)g;
    int localW,localH;
    localW = this.getWidth()/2;
    localH = this.getHeight()/2;
    //g2d.rotate(Math.toRadians(angle), this.getWidth()/2, this.getHeight()/2);
    g2d.rotate(Math.toRadians(angle), localW, localH);
    super.paintComponent(g2d);
    g2d.rotate(-Math.toRadians(angle), localW, localH);
    
    
  }
    
}
