package investigationwall;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.beans.PropertyChangeSupport;

public class AttributeListener {
    public Application parent;
    public AttributeListener(Application parent){
        this.parent = parent;
        WallPanel obj = parent.wallTabController.wallVector.get(parent.howManyWall);
        
        obj.attributeRowTF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNumeric(obj.attributeRowTF.getText()))
                 {
                    obj.myPanel.paintSpace.activeShape.setShapeWidth(Integer.parseInt(obj.attributeRowTF.getText()));
                }else{
                    obj.attributeRowTF.setText(""+obj.myPanel.paintSpace.activeShape.getWidth());
                }
                obj.myPanel.paintSpace.repaint();
            }
        });
        obj.attributeColTF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(isNumeric(obj.attributeColTF.getText()))
                 {
                    obj.myPanel.paintSpace.activeShape.setShapeHeight(Integer.parseInt(obj.attributeColTF.getText()));
                }else{
                    obj.attributeColTF.setText(""+obj.myPanel.paintSpace.activeShape.getHeight());
                }
                obj.myPanel.paintSpace.repaint();
            }
        });
        obj.attributeXTF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(isNumeric(obj.attributeXTF.getText()))
                 {
                    obj.myPanel.paintSpace.activeShape.setShapeX(Integer.parseInt(obj.attributeXTF.getText()));
                }else{
                    obj.attributeXTF.setText(""+obj.myPanel.paintSpace.activeShape.getX());
                }
                obj.myPanel.paintSpace.repaint();
            }
        });
        obj.attributeYTF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNumeric(obj.attributeYTF.getText()))
                 {
                    obj.myPanel.paintSpace.activeShape.setShapeY(Integer.parseInt(obj.attributeYTF.getText()));
                }else{
                    obj.attributeYTF.setText(""+obj.myPanel.paintSpace.activeShape.getY());
                }
                obj.myPanel.paintSpace.repaint();
            }
        });
        obj.attributeAngleTF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isNumeric(obj.attributeAngleTF.getText()))
                 {
                    obj.myPanel.paintSpace.activeShape.setShapeAngle(Integer.parseInt(obj.attributeAngleTF.getText()));
                }else{
                    obj.attributeAngleTF.setText(""+obj.myPanel.paintSpace.activeShape.angleRad);
                }
                obj.myPanel.paintSpace.repaint();
            }
        });
        obj.attributeNoteBoderTF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Border");
                if(isNumeric(obj.attributeNoteBoderTF.getText())
                  && Integer.parseInt(obj.attributeNoteBoderTF.getText())<=30
                  && Integer.parseInt(obj.attributeNoteBoderTF.getText())>=0){
                    obj.myPanel.paintSpace.activeShape.shapeBoder=Integer.parseInt(obj.attributeNoteBoderTF.getText());
                }else{
                    obj.attributeNoteBoderTF.setText(""+obj.myPanel.paintSpace.activeShape.shapeBoder);
                }
                
                obj.myPanel.paintSpace.repaint();
            }
        });
        
        obj.LineColorLb.addPropertyChangeListener(new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent evt){
                    if(obj.LineColorLb.isOK){
                        obj.myPanel.paintSpace.activeShape.shapeOutLineColor=obj.LineColorLb.getBackground();
                        obj.LineColorLb.isOK=false;
                        System.err.println("777");
                        obj.myPanel.paintSpace.activeShape.repaint();
                    }
                    
                }
        });
        
        obj.BgColorLb.addPropertyChangeListener(new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent evt){
                    if(obj.BgColorLb.isOK){
                        obj.myPanel.paintSpace.activeShape.shapeBgColor=obj.BgColorLb.getBackground();
                        obj.BgColorLb.isOK=false;
                        obj.myPanel.paintSpace.activeShape.repaint();
                        obj.myPanel.paintSpace.activeShape.textField.setBackground(obj.BgColorLb.getBackground());
                    }
                }
        });
        
        obj.TextColorLb.addPropertyChangeListener(new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent evt){
                    if(obj.TextColorLb.isOK){
                        obj.myPanel.paintSpace.activeShape.shapeTextColor=obj.TextColorLb.getBackground();
                        obj.TextColorLb.isOK=false;
                        obj.myPanel.paintSpace.activeShape.repaint();
                        obj.myPanel.paintSpace.activeShape.textField.setForeground(obj.TextColorLb.getBackground());                      
                     }
                }
        });
    }
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }
    }
}
