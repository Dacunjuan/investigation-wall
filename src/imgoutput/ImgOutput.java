package imgoutput;

import investigationwall.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.lang3.StringUtils;
public class ImgOutput extends JFrame {
    int initWidth = 600;
    int initHeight = 700;
    String currentdir = System.getProperty("user.dir");
    String OutputAll_Dest = currentdir.replace( "\\", "/" )+"/";
    String OutputAll_Name = "Unamed";
    String OutputScreen_Dest = OutputAll_Dest;
    String OutputScreen_Name = OutputAll_Name + "(1)";
    String Format = ".jpg";
    
    public ImgState imgState;
    int round = 1;
    JFileChooser FileChooser;
    public OutputState outputState = OutputState.Button;
    
    Title title = null;
    BoxJP boxJP  = null;
    BtnJP btnJP = null;
    
    public ImgOutput(Application parent)
    {
        super();
        this.setSize(initWidth, initHeight);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-initWidth)/2,
                      (Toolkit.getDefaultToolkit().getScreenSize().height-initHeight)/2);
        
        this.setLayout(new BorderLayout());
        if(title==null)
        {
            title = new Title();
        }
        if(boxJP == null)
        {
            boxJP = new BoxJP(parent, this);
        }
        if(btnJP == null)
        {
            btnJP = new BtnJP(parent, this);
        }

        this.add(title, BorderLayout.NORTH);
        this.add(boxJP, BorderLayout.CENTER);
        this.add(btnJP, BorderLayout.SOUTH);
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            {
                ImgOutput.this.setVisible(false);
                //setVisible(false);
            }
        });
        this.setResizable(false);
        this.setVisible(true);
    }
    
    void output(BufferedImage p, String Destination, String Name)
    {
        String str = StringUtils.substringAfter(Format, ".");
        try {
            ImageIO.write(p, str, new File(Destination + Name + Format));
        } catch (IOException ex) {}
    }
    void FileFrame(String Destination, String Name)
    {
        System.out.println("s:"+Destination);
        FileChooser = new JFileChooser();
        FileChooser.setDialogTitle("儲存圖片");
        FileChooser.setCurrentDirectory(new File(Destination));
        FileChooser.setSelectedFile(new File(Name+Format));
        switch((String)boxJP.formatMenu.getSelectedItem())
        {
            case "JPEG 檔案":
                FileChooser.setFileFilter(new FileNameExtensionFilter("JPEG (*.jpg)", "jpg"));
                break;
            case "PNG 檔案":
                FileChooser.setFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));
                break;
            case "GIF 檔案":
                FileChooser.setFileFilter(new FileNameExtensionFilter("GIF (*.gif)", "gif"));
                break;
        }
        int result = FileChooser.showDialog(null, "Save");
        if(result == JFileChooser.APPROVE_OPTION)
        {
            //設定textfield字串
            boxJP.preview.jtextfield.setText(FileChooser.getSelectedFile().getAbsolutePath());
            //檢查textfield字串中是否有Format
            if(!boxJP.preview.jtextfield.getText().endsWith(Format))
                boxJP.preview.jtextfield.setText(boxJP.preview.jtextfield.getText()+Format);//jtextfield
            //檢查檔案名稱是否有Format
            if(!FileChooser.getSelectedFile().getName().endsWith(Format))
            {//Name = Unamed
                Name = StringUtils.substringBeforeLast(FileChooser.getSelectedFile().getName() + Format, "." );
                Destination = StringUtils.substringBefore(boxJP.preview.jtextfield.getText(),
                                                       FileChooser.getSelectedFile().getName() + Format);
            }
            else
            {//Name = Unamed.jpg
                Name = StringUtils.substringBeforeLast(FileChooser.getSelectedFile().getName(), "." );
                Destination = StringUtils.substringBefore(boxJP.preview.jtextfield.getText(),
                                                       FileChooser.getSelectedFile().getName());
            }
            if(imgState == ImgState.All)
            {
                OutputAll_Dest = Destination;
                OutputAll_Name = Name;
            }
            if(imgState == ImgState.Screen)
            {
                OutputScreen_Dest = Destination;
                OutputScreen_Name = Name;
            }
            if(imgState == ImgState.Both)
            {
                if(round == 1)
                {
                    OutputAll_Dest = Destination;
                    OutputAll_Name = Name;
                }
                else
                {
                    OutputScreen_Dest = Destination;
                    OutputScreen_Name = Name;
                }
            }
            System.out.println("<filefrmae>");
            //this.flag = true;
            outputState = OutputState.FileFrame;
            //int mode = 0;
            CheckName(Destination, Name);
        }
    }
    int CheckName(String Destination, String Name)
    {
        int num = 0;
        File file = new File(Destination+Name+Format);
        if((file.exists()) && (!file.isDirectory()))
        {
            if(imgState == ImgState.All)
                OutputAllWarning();
            if(imgState == ImgState.Screen)
                OutputScreenWarning();
            if(imgState == ImgState.Both)
            {
                if(round == 1)
                {
                    OutputAllWarning();
                    round++;
                }
                else
                {
                    OutputScreenWarning();
                    round=1;
                }
            }
            num = 1;
            return num;
        }
        System.out.println(Destination+Name+Format);
        return num;
    }
    void OutputAllWarning()
    {
        int result=JOptionPane.showConfirmDialog(null,
                "圖片檔名: " + OutputAll_Name + Format+"已經存在\n"+"確定要取代嗎？",
                "確認訊息",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if(result==JOptionPane.NO_OPTION)
            FileFrame(OutputAll_Dest, OutputAll_Name);

        if((result==JOptionPane.YES_OPTION)&&(outputState == OutputState.Button))//完成鈕輸出
            output(boxJP.photoFrame.ScaleImage, OutputAll_Dest, OutputAll_Name);
    }
    void OutputScreenWarning()
    {
        int result=JOptionPane.showConfirmDialog(null,
                "截圖檔名: "+ OutputScreen_Name + Format+"已經存在\n"+"確定要取代嗎？",
                "確認訊息",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if(result==JOptionPane.NO_OPTION)
            FileFrame(OutputScreen_Dest, OutputScreen_Name);
        
        if((result==JOptionPane.YES_OPTION)&&(outputState == OutputState.Button))//完成鈕輸出
            output(boxJP.photoFrame.ScaleImage, OutputScreen_Dest, OutputScreen_Name);
    }
}