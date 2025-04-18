/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investigationwall.imginput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

import investigationwall.*;

import java.util.Base64;
import painting.*;

public class Img extends ImgBox{
    BufferedImage Img;
    Image Scale;
    Image image;
    public Img(Application parent, PaintSpace space, int x, int y, int ImgWidth , int ImgHeight, State ImgOperate)
    {
        super(parent, space, x, y, ImgWidth , ImgHeight, ImgOperate);
        WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
        if(this.ImgOperate == State.Add)
        {
            try{
                Img = ImageIO.read(this.ImgFile);
            }catch(IOException ex){
                System.err.println(ex);
            }
            Scale = Img.getScaledInstance(this.ImgWidth, this.ImgHeight, Image.SCALE_SMOOTH);
            wPnl.database.SaveData(this.ImgOperate);
            super.ImgOperate = State.Active;
            super.showControlLine();
        }
        if(this.ImgOperate == State.Pasting)
        {
            System.out.println("Target: " + wPnl.database.Target);
            boolean flag;
            flag = wPnl.database.ImgCode[wPnl.database.Target].contains(":\\");
            if(flag == true)
            {
                File file = new File(wPnl.database.ImgCode[wPnl.database.Target]);
                try{
                    Img = ImageIO.read(file);
                }catch(IOException ex){}
                Scale = Img.getScaledInstance(this.ImgWidth, this.ImgHeight, Image.SCALE_SMOOTH);
                wPnl.database.SaveData(this.ImgOperate);
                super.ImgOperate = State.Active;
                super.showControlLine();
            }
            else
            {
                Base64.Decoder Decoder = Base64.getDecoder();
                byte[] bytes = Decoder.decode(wPnl.database.ImgCode[wPnl.database.Target]);
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                try{
                    Img = ImageIO.read(bais);
                }catch(IOException ex){}
                Scale = Img.getScaledInstance(this.ImgWidth, this.ImgHeight, Image.SCALE_SMOOTH);
                wPnl.database.SaveData(this.ImgOperate);
                super.ImgOperate = State.Active;
                super.showControlLine();
            }
        }
        if(this.ImgOperate == State.Open)
        {
            Base64.Decoder Decoder = Base64.getDecoder();
            byte[] bytes = Decoder.decode(wPnl.database.ImgCode[wPnl.database.LoadData_i]);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            try{
                Img = ImageIO.read(bais);
                Scale = Img.getScaledInstance(this.ImgWidth, this.ImgHeight, Image.SCALE_SMOOTH);
            }catch(IOException ex){}
            super.ImgOperate = State.inActive;
        }
        
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                               RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        /*if(this.state == State.Add || this.state == State.Copying || this.state == State.Open)
        {
            g2d.drawImage(Scale, 20, 20, this.ImgWidth, this.ImgHeight, null);
            g2d.setColor(Color.BLACK);//圖框
            g2d.setStroke(new BasicStroke(2f));//圖框
            g2d.drawRect(20, 20, this.ImgWidth + 1, this.ImgHeight + 1);//圖框
        }*/
        if(this.ImgOperate == State.reSizing)
        {
//            System.out.println("pass");
//            g2d.clearRect(20, 20, this.ImgWidth, this.ImgHeight);
//            this.repaint();
            Scale = Img.getScaledInstance(this.ImgWidth, this.ImgHeight, Image.SCALE_SMOOTH);
            g2d.drawImage(Scale, 20, 20, this.ImgWidth, this.ImgHeight, null);
            // g2d.setColor(Color.BLACK);//圖框
            // g2d.setStroke(new BasicStroke(2f));//圖框
            // g2d.drawRect(20, 20, this.ImgWidth + 1, this.ImgHeight + 1);//圖框
            
        }
        else
        {
            g2d.drawImage(Scale, 20, 20, this.ImgWidth, this.ImgHeight, null);
            // g2d.setColor(Color.BLACK);//圖框
            // g2d.setStroke(new BasicStroke(2f));//圖框
            // g2d.drawRect(20, 20, this.ImgWidth + 1, this.ImgHeight + 1);//圖框
        }
    }
}
