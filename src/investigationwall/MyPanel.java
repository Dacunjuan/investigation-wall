package investigationwall;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import painting.PaintSpace;

import java.awt.event.MouseEvent;

public class MyPanel extends JPanel{
    
    //private JLabel lab;
    private PanAttribute myAttribute;

    public PaintSpace paintSpace;
    public Application app;
    
    public Dimension shapeSize = new Dimension(0,0);
    public Point shapeLocation = new Point(0,0);    
    public double shapeAngle;
    public int shapeBoder;
    public Color shapeLineColor;
    public Color shapeBgColor;
    
    
    public MyPanel(Application app){
        super();
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        //this.addMouseListener(new RightClickListener()); 移動到PaintSpace
        
        paintSpace = new PaintSpace(this);
        this.add(paintSpace);
        //this.app.wallTabController.wallVector.get(app.selectedIndex).attributeRowTF.setText("123");
//        WallPanel obj = app.wallTabController.wallVector.get(app.selectedIndex);
//        obj.attributeRowTF.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                   // System.err.println("456");
//            }
//        });
    }
    
    
}
