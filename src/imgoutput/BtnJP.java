package imgoutput;
import investigationwall.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import org.apache.commons.lang3.StringUtils;

public class BtnJP extends JPanel{
    JButton BtnTrue;
    JButton BtnFalse;
    BufferedImage BoxImage;
    
    BtnJP(Application parent, ImgOutput imgoutput)
    {
        super();
        BtnTrue = new JButton("完成");
        BtnFalse = new JButton("取消");
        this.setPreferredSize(new Dimension(111, 35));
        this.setBackground(new Color(240, 240, 240));
        this.add(BtnTrue);
        this.add(BtnFalse);
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        
        BtnTrue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                BoxImage = new BufferedImage(570, 430, BufferedImage.TYPE_3BYTE_BGR);
                BoxImage = imgoutput.boxJP.photoFrame.ScaleImage.getSubimage(
                           imgoutput.boxJP.photoFrame.HorizontalValue,
                           imgoutput.boxJP.photoFrame.VerticalValue, 570, 430);
                
                //檢查textfield結尾是否有"Format"
                if(!imgoutput.boxJP.preview.jtextfield.getText().endsWith(".jpg")&&
                  (!imgoutput.boxJP.preview.jtextfield.getText().endsWith(".png"))&&
                  (!imgoutput.boxJP.preview.jtextfield.getText().endsWith(".gif")))
                {
                    imgoutput.boxJP.preview.jtextfield.setText(
                            imgoutput.boxJP.preview.jtextfield.getText()+
                            imgoutput.Format);
                }
                //Destination, Name, Format 分別從textfield獲得資訊
                if(imgoutput.boxJP.checkBox.OutputAll.isSelected() == true)
                {
                    imgoutput.OutputAll_Name = StringUtils.substringAfterLast(imgoutput.boxJP.preview.jtextfield.getText(), "\\");
                    imgoutput.OutputAll_Dest = StringUtils.substringBefore(imgoutput.boxJP.preview.jtextfield.getText(), imgoutput.OutputAll_Name);
                    imgoutput.Format = "." + StringUtils.substringAfterLast(imgoutput.OutputAll_Name, ".");
                    imgoutput.OutputAll_Name = StringUtils.substringBeforeLast(imgoutput.OutputAll_Name, ".");
                }
                if(imgoutput.boxJP.checkBox.OutputScreen.isSelected() == true)
                {
                    imgoutput.OutputScreen_Name = StringUtils.substringAfterLast(imgoutput.boxJP.preview.jtextfield.getText(), "\\");
                    imgoutput.OutputScreen_Dest = StringUtils.substringBefore(imgoutput.boxJP.preview.jtextfield.getText(), imgoutput.OutputScreen_Name);
                    imgoutput.Format = "." + StringUtils.substringAfterLast(imgoutput.OutputScreen_Name, ".");
                    imgoutput.OutputScreen_Name = StringUtils.substringBeforeLast(imgoutput.OutputScreen_Name, ".");
                    imgoutput.OutputScreen_Name += "(1)";
                }
                
                //輸出
                if(imgoutput.outputState == OutputState.FileFrame)
                {
                    System.out.println(imgoutput.OutputAll_Name+", "+imgoutput.OutputScreen_Name);
                    
                    if(imgoutput.imgState == ImgState.All)
                        imgoutput.output(imgoutput.boxJP.photoFrame.ScaleImage,
                              imgoutput.OutputAll_Dest,imgoutput.OutputAll_Name);
                    
                    if(imgoutput.imgState == ImgState.Screen)
                        imgoutput.output(BoxImage,imgoutput.OutputScreen_Dest, imgoutput.OutputScreen_Name);
                    
                    if(imgoutput.imgState == ImgState.Both)
                    {
                        imgoutput.output(imgoutput.boxJP.photoFrame.ScaleImage,
                              imgoutput.OutputAll_Dest,imgoutput.OutputAll_Name);
                        
                        imgoutput.output(BoxImage,imgoutput.OutputScreen_Dest, imgoutput.OutputScreen_Name);
                    }
                    parent.mainFrame.setEnabled(true);
                    imgoutput.setVisible(false);
                }
                if(imgoutput.outputState == OutputState.Button)
                {
                    int num=0;
                    if(imgoutput.imgState == ImgState.All)
                        num = imgoutput.CheckName(imgoutput.OutputAll_Dest, imgoutput.OutputAll_Name);
                    if(imgoutput.imgState == ImgState.Screen)
                        num = imgoutput.CheckName(imgoutput.OutputScreen_Dest, imgoutput.OutputScreen_Name);
                    if(imgoutput.imgState == ImgState.Both)
                    {
                        num = imgoutput.CheckName(imgoutput.OutputAll_Dest, imgoutput.OutputAll_Name);
                        num = imgoutput.CheckName(imgoutput.OutputScreen_Dest, imgoutput.OutputScreen_Name);
                    }
                   if(num == 0)//"0":檢查無檔案名稱重複
                   {
                       if(imgoutput.boxJP.checkBox.OutputAll.isSelected())
                            imgoutput.output(imgoutput.boxJP.photoFrame.ScaleImage,
                                  imgoutput.OutputAll_Dest,
                                  imgoutput.OutputAll_Name);
                        
                       if(imgoutput.boxJP.checkBox.OutputScreen.isSelected())
                            imgoutput.output(BoxImage,imgoutput.OutputScreen_Dest, imgoutput.OutputScreen_Name);
                   
                       if(imgoutput.boxJP.checkBox.OutputAll.isSelected() == true &&
                       imgoutput.boxJP.checkBox.OutputScreen.isSelected() == true)
                        {
                            imgoutput.output(imgoutput.boxJP.photoFrame.ScaleImage,
                                  imgoutput.OutputAll_Dest,imgoutput.OutputAll_Name);

                            imgoutput.output(BoxImage,imgoutput.OutputScreen_Dest, imgoutput.OutputScreen_Name);
                        }
                   }
                       
                }
            }
        });
        
        BtnFalse.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                imgoutput.setVisible(false);
                //BtnJP.this.imgoutput.setVisible(false);
            }
        });
    }
}
