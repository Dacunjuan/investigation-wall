package investigationwall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class ChooseColorLabel extends JPanel{
    
    public boolean isOK=false;
    
    public ChooseColorLabel(Application app){
        super();
        this.setBackground(Color.RED);
        Border border = new LineBorder(Color.BLACK, 3);
        this.setBorder(border);
        
        JLabel ChooserListener = new JLabel();
        ChooserListener.setPreferredSize(new Dimension(30,20));
        ChooserListener.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                isOK=true;
                ColorChooserWindow colorChoose = new ColorChooserWindow(app.mainFrame,"Color Chooser",true,ChooseColorLabel.this);
                colorChoose.setVisible(true);
            }
        });
        
        this.add(ChooserListener);
    }
}
