/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package painting;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *s
 * @author hanshen
 */
public class iwLabel extends Shape{
    public int w, h, x, y;
    public TextField TempTextField;
    public String str="Text";
    
    public iwLabel(PaintSpace p, int x, int y, int w, int h) {
        super(p, x, y, w, h);
        this.w=w;
        this.h=h;
        this.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                if (e.getClickCount() >= 2) {
                    int c_index;
                    System.out.println(e.getClickCount());
                    if (TempTextField != null) {
                        parent.remove(TempTextField);
                    }
                    TempTextField = new TextField(iwLabel.this.str);
                    //System.out.println(x);
                    //System.out.println(y);
                    TempTextField.setLocation(x,y);
                    TempTextField.setSize(w, 30);
                    parent.add(TempTextField);
                    c_index = parent.getLayer(iwLabel.this);
                    parent.setLayer(TempTextField, c_index+1);
                    
                    TempTextField.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            str = TempTextField.getText();
                            parent.remove(TempTextField);
                            parent.repaint();
                        }
                    });
                }
            }
        });
        
        
        
    }
   
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        w=super.w-100;
        h=super.h-100;
        Point op= super.getLocation();
        //x=op.x+(super.getWidth()-w)/2;
        //y=op.y+(super.getHeight()-h)/2;
        this.x=op.x+(super.w-w)/2;
        this.y=op.y+(super.h-h)/2;
        System.out.println(w);
        if(TempTextField!=null){
            TempTextField.setLocation(this.x,this.y);
        }
        g2d.setColor(super.shapeTextColor);
        g2d.drawString(str,62,68);
        
    }
}
