package imgoutput;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Preview extends JPanel{
    JLabel text;
    JTextField jtextfield;
    JButton preview;
    
    ImgOutput imgoutput;
    Preview(ImgOutput imgoutput, CheckBox checkBox)
    {
        super();
        this.setBackground(new Color(192, 192, 192));
        this.imgoutput = imgoutput;
        text = new JLabel("檔案路徑：");
        jtextfield = new JTextField(this.imgoutput.OutputAll_Dest + this.imgoutput.OutputAll_Name + this.imgoutput.Format);
        jtextfield.setPreferredSize(new Dimension(440, 30));
        preview = new JButton("預覽");
        
        this.add(text);
        this.add(jtextfield);
        this.add(preview);
        
        preview.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(imgoutput.imgState == ImgState.All)
                    Preview.this.imgoutput.FileFrame(imgoutput.OutputAll_Dest,
                                                     imgoutput.OutputAll_Name);
                if(imgoutput.imgState == ImgState.Screen)
                    Preview.this.imgoutput.FileFrame(imgoutput.OutputScreen_Dest,
                                                     imgoutput.OutputScreen_Name);
                
                if(imgoutput.imgState == ImgState.Both)
                {
                    Preview.this.imgoutput.FileFrame(imgoutput.OutputAll_Dest,
                                                     imgoutput.OutputAll_Name);
                    System.out.println("round");
                    Preview.this.imgoutput.FileFrame(imgoutput.OutputScreen_Dest,
                                                     imgoutput.OutputScreen_Name);
                }
            }
        });
    }
}
