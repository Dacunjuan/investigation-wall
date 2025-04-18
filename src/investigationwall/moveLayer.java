package investigationwall;

import investigationwall.imginput.Img;
import investigationwall.imginput.ImgBox;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import painting.Shape;
/**
 *
 * @author hanShen
 */
public class moveLayer {
    public int c_index;
    public WallPanel parent;
    moveLayer(WallPanel p){
        parent = p;       
    }
    public void moveUpLayer(JLayeredPane Pane , Shape component){
         this.c_index = Pane.getLayer(component)+1;
         System.out.print("Com index = ");
         System.out.println(c_index);
         Pane.setLayer(component, c_index,0);
         parent.attributeLayerIndex.setText(Integer.toString(c_index));
         this.c_index = Pane.getLayer(component.textField)+1;
         System.out.print("Com.tf index = ");
         System.out.println(c_index);
         Pane.setLayer(component.textField, c_index,0);
    }
    
    public void moveDownLayer(JLayeredPane Pane , Shape component){
         this.c_index = Pane.getLayer(component)-1;
         System.out.print("Com index = ");
         System.out.println(c_index);
         Pane.setLayer(component, c_index,0);
         parent.attributeLayerIndex.setText(Integer.toString(c_index));
         this.c_index = Pane.getLayer(component.textField)-1;
         System.out.print("Com.tf index = ");
         System.out.println(c_index);
         Pane.setLayer(component.textField, c_index,0);
         
    }
    
    public void moveUpLayer(JLayeredPane Pane , Img component){
         this.c_index = Pane.getLayer(component)+1;
         parent.attributeLayerIndex.setText(Integer.toString(c_index));
         System.out.print("ComImg index = ");
         System.out.println(c_index);
         Pane.setLayer(component, c_index,0);
         //component.hideControlLine();
         //Pane.remove(component);
    }  
    
    public void moveDownLayer(JLayeredPane Pane , Img component){
         this.c_index = Pane.getLayer(component)-1;
         parent.attributeLayerIndex.setText(Integer.toString(c_index));
         System.out.print("ComImg index = ");
         System.out.println(c_index);
         if(c_index!=0){
             Pane.setLayer(component,  c_index,0);
         }
    }
    
}