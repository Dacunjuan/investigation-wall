package imgoutput;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author WIN10
 */
class CheckBox extends JPanel{
    JCheckBox OutputAll;
    JCheckBox OutputScreen;
    CheckBox(ImgOutput imgoutput)
    {
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setPreferredSize(new Dimension(570, 35));
        this.setBackground(new Color(192, 192, 192));
        
        OutputAll = new JCheckBox("匯出完整圖鈕",true);
        OutputAll.setBackground(new Color(192, 192, 192));
        OutputScreen = new JCheckBox("匯出框定圖鈕");
        OutputScreen.setBackground(new Color(192, 192, 192));
        
        this.add(OutputAll);
        this.add(OutputScreen);
        
        if(OutputAll.isSelected() == true)
        {
            imgoutput.imgState = ImgState.All;
            System.out.println(imgoutput.imgState);
        }
        OutputAll.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                if(OutputAll.isSelected() == true && OutputScreen.isSelected() == false)
                {
                    imgoutput.imgState = ImgState.All;
                    System.out.println(imgoutput.imgState);
                }
                if(OutputAll.isSelected() == false && OutputScreen.isSelected() == true)
                {
                    imgoutput.imgState = ImgState.Screen;
                    System.out.println(imgoutput.imgState);
                }
                if(OutputAll.isSelected() == true && OutputScreen.isSelected() == true)
                {
                    imgoutput.imgState = ImgState.Both;
                    System.out.println(imgoutput.imgState);
                }
            }
        });
        
        OutputScreen.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                if(OutputAll.isSelected() == true && OutputScreen.isSelected() == false)
                {
                    imgoutput.imgState = ImgState.All;
                    System.out.println(imgoutput.imgState);
                }
                if(OutputAll.isSelected() == false && OutputScreen.isSelected() == true)
                {
                    imgoutput.imgState = ImgState.Screen;
                    System.out.println(imgoutput.imgState);
                }
                if(OutputAll.isSelected() == true && OutputScreen.isSelected() == true)
                {
                    imgoutput.imgState = ImgState.Both;
                    System.out.println(imgoutput.imgState);
                }
            }
        });
    }
}
