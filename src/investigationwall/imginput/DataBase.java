/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investigationwall.imginput;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.lang3.StringUtils;

import investigationwall.*;
import painting.*;


public class DataBase {
    File LoadFile = null;//OpenFile
    public int Img_i = 0, row = 0, Imgcol = 5;
    String[] ImgCode = null;
    public int Target = -1;
    public int LoadData_i;
    
    public ImgBox[] SelectBox;//預備
    public ImgBox[] EntireBox;
    public Point[] BufferedBox;
    int ActiveAmount = 0;
    String Tag;
    Img img;
    
    int Nail_i = 0, Nail_row = 0, Nail_col = 2;
    int Nail_Target = -1;
    
    ImgBox[] NailBox;
    
    Application parent;
    PaintSpace space;
    public DataBase(Application parent, PaintSpace space){
        this.parent = parent;
        this.space = space;
    }
    public void ClearData()
    {
        LoadFile = null;//OpenFile
        Img_i = 0; row = 0;
        ImgCode = null;
        Target = -1;
        LoadData_i = 0;
        
        SelectBox = null;
        EntireBox = null;
    }
    public void IncreaseArraySize(State Operate)
    {
        if(Operate == State.ImgOperate)
        {
            if(row == 0)
            {
                row++;
                System.out.println("i: "+Img_i+", row: "+row);

                EntireBox = new ImgBox[row];
                ImgCode = new String[row];
            }
            if(row != 0 && Img_i == row)
            {
                System.out.println("row++");
                row ++;
                System.out.println("i: "+Img_i+", row: "+row);

                ImgBox[] temp = new ImgBox[row];
                for(int i = 0;i < row - 1;i++)
                    temp[i] = EntireBox[i];

                EntireBox = null;
                EntireBox = new ImgBox[row];
                for(int i = 0;i < row;i++)
                    EntireBox[i] = temp[i];
                /*------------------------*/
                String[] temp2 = new String[row];
                for(int i = 0;i < row - 1;i++)
                    temp2[i] = ImgCode[i];

                ImgCode = null;
                ImgCode = new String[row];
                for(int i = 0;i < row;i++)
                    ImgCode[i] = temp2[i];
            }
        }
        else if(Operate == State.NailOperate)
        {
            if(Nail_row == 0)
            {
                Nail_row++;
                NailBox = new ImgBox[Nail_row];
            }
            if(Nail_row != 0 && Nail_i == Nail_row)
            {
                Nail_row++;
                ImgBox[] temp = new ImgBox[Nail_row];
                for(int i = 0;i < Nail_row - 1;i++)
                    temp[i] = NailBox[i];
                
                NailBox = null;
                NailBox = new ImgBox[Nail_row];
                for(int i = 0;i < Nail_row;i++)
                    NailBox[i] = temp[i];
            }
        }
    }
    public void ReduceArraySize(State Operate)
    {
        if(Operate == State.ImgOperate)
        {
            row --;
            Img_i--;

            ImgBox[] temp = new ImgBox[row];
            for(int i = 0;i < row;i++)
                temp[i] = EntireBox[i];

            EntireBox = null;
            EntireBox = new ImgBox[row];
            for(int i = 0;i < row;i++)
                EntireBox[i] = temp[i];
            /*------------------------*/
            String[] temp2 = new String[row];
            for(int i = 0;i < row;i++)
                temp2[i] = ImgCode[i];

            ImgCode = null;
            ImgCode = new String[row];
            for(int i = 0;i < row;i++)
                ImgCode[i] = temp2[i];
            /*------------------------*/

            for(int i=0;i<row;i++)//show
                System.out.println("<Exist>data["+i+"]x: " + EntireBox[i].x +", y: "+EntireBox[i].y+
                                        ", ImgWidth: "+EntireBox[i].ImgWidth+", ImgHeight: "+EntireBox[i].ImgHeight/*+
                                        ", ImgCode: "+ImgCode[i]*/);
        }
        else if(Operate == State.NailOperate)
        {
            
        }
    }
    public void CoverData(State Operate)
    {
        if(Operate == State.ImgOperate)
        {
            if(Target == row + 1)//If you delete last data
            {
                ReduceArraySize(State.ImgOperate);
            }
            else
            {
                WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
                for(int i = Target + 1;i < wPnl.database.row;i++)
                {
                    EntireBox[i-1] = EntireBox[i];
                    ImgCode[i-1] = ImgCode[i];
                }
                for(int i=0;i<row;i++)//show
                    System.out.println("<cover>data["+i+"]x: " + EntireBox[i].x+", y: "+EntireBox[i].y+
                                        ", ImgWidth: "+EntireBox[i].ImgWidth+", ImgHeight: "+EntireBox[i].ImgHeight+
                                        ", ImgCode: "+ImgCode[i]);
                ReduceArraySize(State.ImgOperate);
            }
        }
        else if(Operate == State.NailOperate)
        {
            
        }
    }
    public void SaveData(State ImgOperate)
    {
        if(ImgOperate == State.Add)
            ImgCode[Img_i] = parent.ImgFile.toString();
        if(ImgOperate == State.Pasting)
            ImgCode[Img_i] = ImgCode[Target];
        
        System.out.println("Now i: "+Img_i+", row: "+row);
        Img_i++;
    }
    public void ImageToBase()
    {
        System.out.println("ImageToBase");
        System.out.println("ImgCodeSize: "+ImgCode.length);
        
        for(int i = 0;i < row;i++)
        {
            File file = new File(ImgCode[i]);
            System.out.println("file: "+ file);
            try{
                BufferedImage imgx = ImageIO.read(file);
                Image Scale = imgx.getScaledInstance(EntireBox[i].ImgWidth, EntireBox[i].ImgHeight, Image.SCALE_SMOOTH);
                BufferedImage img = new BufferedImage(EntireBox[i].ImgWidth, EntireBox[i].ImgHeight, BufferedImage.TYPE_3BYTE_BGR);
                Graphics2D g2d = img.createGraphics();
                g2d.drawImage(Scale, 0, 0, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();//圖片轉位元組
                ImageIO.write(img, "jpg", baos);
                byte[] bytes = baos.toByteArray();
                
                Base64.Encoder Encoder = Base64.getEncoder();
                String str = Encoder.encodeToString(bytes);
                System.out.println("ImgCode["+i+"]= "+str);
                ImgCode[i] = str;
            }catch(IOException ex){}
        }
    }
    public void SaveFile()
    {
        
        System.out.println("Save Image");
        Tag = new String("Img");
        for(int i = 0;i < row;i++)//show
        {
            System.out.println("data["+i+"] x: "+ EntireBox[i].x + ", y: " + EntireBox[i].y + 
                    ", ImgWidth: " + EntireBox[i].ImgWidth + ", ImgHeight: " + EntireBox[i].ImgHeight + 
                    ", ImgCode: " + ImgCode[i]);
        }
        
        try{
            FileWriter fw = new FileWriter(new File("F:\\test\\filename.itw"));
            fw.write(Tag + ",");
            for(int i = 0;i < row;i++)
            {
                fw.write(Integer.toString(EntireBox[i].x) + ",");
                fw.write(Integer.toString(EntireBox[i].y) + ",");
                fw.write(Integer.toString(EntireBox[i].ImgWidth) + ",");
                fw.write(Integer.toString(EntireBox[i].ImgHeight) + ",");
                fw.write(ImgCode[i] + "\n");
            }
            fw.flush();
            fw.close();
        }catch(IOException ex){}
        System.out.println("done");
    }
    public void OpenFile()
    {
        JFileChooser FileChooser = new JFileChooser();
        FileChooser.setDialogTitle("匯入檔案");
        FileChooser.setCurrentDirectory(new File("F:\\test\\"));
        FileChooser.setFileFilter(new FileNameExtensionFilter("ITW (*.itw)", "itw"));
        int result = FileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            LoadFile = new File(FileChooser.getSelectedFile().getAbsolutePath());
            System.out.println("OpenFile:");
        }
        String temp;
        try{
            FileReader fr = new FileReader(LoadFile);
            BufferedReader reader = new BufferedReader(fr);
            String str;
            while((str = reader.readLine())!=null)
            {
                temp = StringUtils.substringBefore(str, ",");
                if(temp.equals("Img") || temp.equals("Shape") || temp.equals("Line"))
                {
                    Tag = temp;
                    str = StringUtils.substringAfter(str, ",");
//                    System.out.println("Tag:" + Tag);
//                    System.out.println("str:" + str);
                }
                
                if(Tag.equals("Img"))
                {
                    IncreaseArraySize(State.ImgOperate);
                    String[] data = new String[Imgcol];
                    int i = 0;
                    for(String st: str.split(","))
                    {
                        data[i] = st;
                        i++;
                    }
                    ImgCode[Img_i] = data[4];
                    for(i = 0;i < Imgcol - 1;i++)
                        System.out.print(data[i] + ",");
                    System.out.println(ImgCode[Img_i]);
                    
                    img = new Img(parent, space, Integer.valueOf(data[0]),
                                                 Integer.valueOf(data[1]),
                                                 Integer.valueOf(data[2]),
                                                 Integer.valueOf(data[3]),State.Open);
                    EntireBox[Img_i] = img;
                    this.Img_i++;
//                    data = null;
                }
                
                
            }
            /*int dot = 0;
            char c;
            for(int i = 0; i < str.length();i++)
            {
                c = str.charAt(i);
                if(c == ',')
                    dot++;
            }
            row = (dot + 1) / (col + 1);
            /*-------
            String[][] Data = new String[row][col];
            this.i = row;
            ImgData = new int[row][col];
            ImgCode = new String[row];
            EntireBox = new ImgBox[row];
            System.out.println("i: "+this.i+", row: "+row);
            /*-------
            int i = 0, j = 0;
            for(String st: str.split(","))
            {
                Data[i][j] = st;
                j++;
                if(j == col + 1)
                {
                    j = 0;
                    i++;
                }
            }
            
            /*for(i = 0;i < row;i++)
                for(j = 0;j < col + 1;j++)
                    System.out.println("Data["+i+"]["+j+"]: "+Data[i][j]);
            
            for(i = 0;i < row;i++)
                for(j = 0;j < col;j++)
                    ImgData[i][j] = Integer.parseInt(Data[i][j]);
            for(i = 0; i < row;i++)
                ImgCode[i] = Data[i][4];
            
            for(i = 0;i < row;i++)
                for(j = 0;j < col;j++)
                    System.out.println("ImgData["+i+"]["+j+"]: "+ImgData[i][j]);*/
            
        }catch(IOException ex){}
        /*for(int i = 0; i < row;i++)
        {
            LoadData_i = i;
            img = new Img(parent, space, ImgData[i][0], ImgData[i][1],
                                         ImgData[i][2], ImgData[i][3], State.Open);
            EntireBox[i] = img;
        }*/
        System.out.println("done");
    }
}
