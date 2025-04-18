/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investigationwall.imginput;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

import investigationwall.Application;
import investigationwall.WallPanel;
import painting.*;

public class ImgBox extends JComponent{
    public int index=-1;
    File ImgFile;
    int x, y;
    int ImgWidth, ImgHeight;
    float Img_Aver;
    int LineWidth = 0, LineHeight = 0;
    drawBorderLine line;
    drawBorderPoint SE;
    
    public State ImgOperate = null;
    
    Point fp;
    
    Application parent;
    PaintSpace space;
    public ImgBox(Application parent, PaintSpace space, int x, int y, int ImgWidth , int ImgHeight, State ImgOperate)
    {
        super();
        this.parent = parent;
        this.space = space;
        this.x = x;
        this.y = y;
        this.ImgWidth = ImgWidth;
        this.ImgHeight = ImgHeight;
        this.ImgOperate = ImgOperate;
        this.ImgFile = parent.ImgFile;
        this.LineWidth = ImgWidth + 41;
        this.LineHeight = ImgHeight + 41;
        
        
        
        System.out.println("ImgOperate: " + this.ImgOperate);
        
        
        
        if(this.ImgOperate == State.Add || this.ImgOperate == State.Pasting)
        {
            if(this.ImgOperate == State.Add)
                SetupImgSize();
            line = new drawBorderLine();
            line.setBackground(Color.black);
            this.space.add(line);
            line.setVisible(false);
            
            SE = new drawBorderPoint();
            SE.setBackground(Color.black);
            this.space.add(SE);
            SE.setVisible(false);
            
            this.space.add(this);
            this.setSize(LineWidth, LineHeight);
            this.setLocation(this.x - 20, this.y - 20);
        }
        if(this.ImgOperate == State.Open)
        {
            line = new drawBorderLine();
            line.setBackground(Color.black);
            this.space.add(line);
            line.setVisible(false);
            
            SE = new drawBorderPoint();
            SE.setBackground(Color.black);
            this.space.add(SE);
            SE.setVisible(false);
            
            this.space.add(this);
            this.setSize(this.ImgWidth + 41, this.ImgHeight + 41);
            this.setLocation(this.x - 20, this.y - 20);
        }
        
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e)
            {
                WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
                wPnl.database.Target = index;
                if(ImgBox.this.ImgOperate != State.Active)
                {
                    for(int i = 0;i < wPnl.database.row;i++)
                    {
                        wPnl.database.EntireBox[i].hideControlLine();
                        wPnl.database.EntireBox[i].ImgOperate = State.inActive;
                    }
                    ImgBox.this.showControlLine();
                    ImgBox.this.ImgOperate = State.Active;
                    
                    for(int i = 0; i < wPnl.database.row;i++)
                        if(ImgBox.this.getLocation().equals(wPnl.database.EntireBox[i].getLocation()))
                        {
                            // parent.tool.Textfield_x.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].x));
                            // parent.tool.Textfield_y.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].y));
                            // parent.tool.Textfield_Width.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].ImgWidth));
                            // parent.tool.Textfield_Height.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].ImgHeight));
                            // parent.wallTabController.wallPanel.database.Target = i;
                        }
                }
                
                System.out.println("State: " + wPnl.myPanel.paintSpace.state);
                if(ImgBox.this.ImgOperate == State.Active)
                    fp = e.getLocationOnScreen();
                
                
                
                /*int time = 0;
                for(int i = 0;i < parent.wallTabController.wallPanel.database.row;i++)
                    if(parent.wallTabController.wallPanel.database.EntireBox[i].ImgOperate == State.Active)
                        time++;*/
                
                //if(time == 1)
                //{
                    /*for(int i = 0;i < parent.wallTabController.wallPanel.database.row;i++)
                        if(parent.wallTabController.wallPanel.database.EntireBox[i].ImgOperate == State.Active)
                        {
                            parent.wallTabController.wallPanel.database.EntireBox[i].hideControlLine();
                            parent.wallTabController.wallPanel.database.EntireBox[i].ImgOperate = null;
                        }*/
                    /*for(int i = 0;i < parent.wallTabController.wallPanel.database.row;i++)
                        if(ImgBox.this.getLocation().equals(parent.wallTabController.wallPanel.database.EntireBox[i].getLocation()))
                        {
                            parent.wallTabController.wallPanel.database.EntireBox[i].showControlLine();
                            parent.wallTabController.wallPanel.database.EntireBox[i].ImgOperate = State.Active;
                        
                        
                            parent.tool.Textfield_x.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].x));
                            parent.tool.Textfield_y.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].y));
                            parent.tool.Textfield_Width.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].ImgWidth));
                            parent.tool.Textfield_Height.setText(Integer.toString(parent.wallTabController.wallPanel.database.EntireBox[i].ImgHeight));

                            /*for(int i = 0;i < parent.wallTabController.wallPanel.database.row;i++)
                                if(parent.wallTabController.wallPanel.database.EntireBox[i].x == ImgBox.this.x)
                                    parent.wallTabController.wallPanel.database.Target = i;
                        }*/
                    //System.out.println("["+parent.wallTabController.wallPanel.database.Target+"] x:"+parent.wallTabController.wallPanel.database.MarkBox.x+", y: "+ parent.wallTabController.wallPanel.database.MarkBox.y+
                    //    ", Width: "+parent.wallTabController.wallPanel.database.MarkBox.ImgWidth+", Height: "+parent.wallTabController.wallPanel.database.MarkBox.ImgHeight/*+", ImgCode: "+parent.wallTabController.wallPanel.database.ImgCode[i]*/);
                //}
                
                
                /*if(ImgBox.this.ImgOperate != State.P_Active)
                    space.ResetPaste(parent);
                
                if(parent.state == State.Entire)
                {
                    fp = e.getLocationOnScreen();
                }
                
                if(parent.wallTabController.wallPanel.database.PasteBox != null)
                {
                    fp = e.getLocationOnScreen();
                    System.out.println("pass");
                }
                //if(parent.wallTabController.wallPanel.database.PasteBox == null)
                if(parent.state != State.Entire && parent.wallTabController.wallPanel.database.PasteBox == null)
                {
                    System.out.println("pass");
                    //fp = e.getLocationOnScreen();
                    if(parent.wallTabController.wallPanel.database.MarkBox != null)
                    {
                        parent.wallTabController.wallPanel.database.MarkBox.hideControlLine();
                        parent.wallTabController.wallPanel.database.MarkBox.ImgOperate = null;
                        parent.wallTabController.wallPanel.database.MarkBox = null;
                    }
                    parent.wallTabController.wallPanel.database.MarkBox = ImgBox.this;
                    parent.wallTabController.wallPanel.database.MarkBox.showControlLine();
                    ImgOperate = State.Active;


                    parent.tool.Textfield_x.setText(Integer.toString(parent.wallTabController.wallPanel.database.MarkBox.x));
                    parent.tool.Textfield_y.setText(Integer.toString(parent.wallTabController.wallPanel.database.MarkBox.y));
                    parent.tool.Textfield_Width.setText(Integer.toString(parent.wallTabController.wallPanel.database.MarkBox.ImgWidth));
                    parent.tool.Textfield_Height.setText(Integer.toString(parent.wallTabController.wallPanel.database.MarkBox.ImgHeight));

                    for(int i = 0;i < parent.wallTabController.wallPanel.database.row;i++)
                        if(parent.wallTabController.wallPanel.database.EntireBox[i].x == ImgBox.this.x)
                            parent.wallTabController.wallPanel.database.Target = i;

                    System.out.println("["+parent.wallTabController.wallPanel.database.Target+"] x:"+parent.wallTabController.wallPanel.database.MarkBox.x+", y: "+ parent.wallTabController.wallPanel.database.MarkBox.y+
                        ", Width: "+parent.wallTabController.wallPanel.database.MarkBox.ImgWidth+", Height: "+parent.wallTabController.wallPanel.database.MarkBox.ImgHeight/*+", ImgCode: "+parent.wallTabController.wallPanel.database.ImgCode[i]);
                
                }*/
                
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    if(wPnl.database.EntireBox[i].x == ImgBox.this.x)
                    {
                        System.out.println("[" + i + "] ImgOperate: " + ImgBox.this.ImgOperate);
                    }
                }
                        
            }
            public void mouseReleased(MouseEvent e)
            {
                WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    if(wPnl.database.EntireBox[i].ImgOperate == State.Moving)
                    {
                        wPnl.database.EntireBox[i].showControlLine();
                        wPnl.database.EntireBox[i].ImgOperate = State.Active;
                    }
                }
            }
        });
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e)
            {
                WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
                //for(int i = 0;i < wPnl.database.row;i++)
                    //System.out.println("["+i+"]ImgOperate: " + wPnl.database.EntireBox[i].ImgOperate);
                
                int x = e.getLocationOnScreen().x - fp.x;
                int y = e.getLocationOnScreen().y - fp.y;
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    if(wPnl.database.EntireBox[i].ImgOperate == State.Active)
                    {
                        wPnl.database.EntireBox[i].hideControlLine();
                        Point op = wPnl.database.EntireBox[i].getLocation();

                        wPnl.database.EntireBox[i].setLocation((op.x + x), (op.y + y));//Jcomponent
                        //line.setLocation(op.x + x, op.y + y);//定位線
                        wPnl.database.EntireBox[i].x += x;
                        wPnl.database.EntireBox[i].y += y;

                        fp.x = e.getLocationOnScreen().x;
                        fp.y = e.getLocationOnScreen().y;
                        wPnl.database.EntireBox[i].ImgOperate = State.Moving;
                    }
                }
                
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    if(wPnl.database.EntireBox[i].ImgOperate == State.Moving)
                    {
                        wPnl.database.EntireBox[i].hideControlLine();
                        Point op = wPnl.database.EntireBox[i].getLocation();

                        wPnl.database.EntireBox[i].setLocation((op.x + x), (op.y + y));//component
                        //line.setLocation(op.x + x, op.y + y);//定位線
                        wPnl.database.EntireBox[i].x += x;
                        wPnl.database.EntireBox[i].y += y;

                        fp.x = e.getLocationOnScreen().x;
                        fp.y = e.getLocationOnScreen().y;
                    }
                }
                ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeRowTF.setText(Integer.toString(ImgBox.this.ImgWidth));
                ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeColTF.setText(Integer.toString(ImgBox.this.ImgHeight));

                ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeXTF.setText(Integer.toString(ImgBox.this.getLocation().x));
                ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeYTF.setText(Integer.toString(ImgBox.this.getLocation().y));
                int c_index = ImgBox.this.space.getLayer(ImgBox.this);
                ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeLayerIndex.setText(Integer.toString(c_index));
            }
        });
        SE.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e)
            {
                System.out.println("Pressed");
                if(ImgBox.this.ImgOperate == State.Active)
                    fp = e.getLocationOnScreen();
            }
            public void mouseReleased(MouseEvent e)
            {
                WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
                System.out.println("Redeased");
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    if(wPnl.database.EntireBox[i].ImgOperate == State.reSizing)
                    {
                        wPnl.database.EntireBox[i].showControlLine();
                        wPnl.database.EntireBox[i].ImgOperate = State.Active;
                    }
                }
            }
        });
        
        SE.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e)
            {
                WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
                //System.out.println("Dragged");
                int x = e.getLocationOnScreen().x - fp.x;
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    if(wPnl.database.EntireBox[i].ImgOperate == State.Active)
                    {
                        wPnl.database.EntireBox[i].hideControlLine();
                        wPnl.database.EntireBox[i].ImgWidth += x;
                        ImgreSizing(wPnl.database.EntireBox, i);

                        System.out.println("["+i+"]ImgWidth: " + wPnl.database.EntireBox[i].ImgWidth);
                        System.out.println("["+i+"]ImgHeight: " + wPnl.database.EntireBox[i].ImgHeight);
                        wPnl.database.EntireBox[i].setSize(wPnl.database.EntireBox[i].LineWidth,
                                                           wPnl.database.EntireBox[i].LineHeight);
                        fp.x = e.getLocationOnScreen().x;
                        wPnl.database.EntireBox[i].ImgOperate = State.reSizing;
                    }
                }
                
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    if(wPnl.database.EntireBox[i].ImgOperate == State.reSizing)
                    {
                        wPnl.database.EntireBox[i].hideControlLine();
                        wPnl.database.EntireBox[i].ImgWidth += x;
                        ImgreSizing(wPnl.database.EntireBox, i);

                        System.out.println("["+i+"]ImgWidth: " + wPnl.database.EntireBox[i].ImgWidth);
                        System.out.println("["+i+"]ImgHeight: " + wPnl.database.EntireBox[i].ImgHeight);
                        wPnl.database.EntireBox[i].setSize(wPnl.database.EntireBox[i].LineWidth,
                                                           wPnl.database.EntireBox[i].LineHeight);
                        fp.x = e.getLocationOnScreen().x;
                    }
                }
            }
        });//*/
    }
    public void SetupImgSize()
    {
        try{
            BufferedImage img = ImageIO.read(this.parent.ImgFile);
            this.Img_Aver = (float)(img.getWidth()) / this.ImgWidth;//圖片大小
            this.ImgHeight = (int)(img.getHeight() / this.Img_Aver);
        }catch(IOException ex){}
        this.LineHeight = this.ImgHeight + 41;
    }
    public void ImgreSizing(ImgBox[] EntireBox, int i)
    {
        final WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
        Boolean check;
        check = wPnl.database.ImgCode[i].contains(":\\");
        if(check == true)
        {
            File file = new File(wPnl.database.ImgCode[i]);
            try{
                BufferedImage img = ImageIO.read(file);
                EntireBox[i].Img_Aver = (float)(img.getWidth()) / EntireBox[i].ImgWidth;
                EntireBox[i].ImgHeight = (int)(img.getHeight() / EntireBox[i].Img_Aver);
            }catch(IOException ex){}
            EntireBox[i].LineWidth = EntireBox[i].ImgWidth + 41;
            EntireBox[i].LineHeight = EntireBox[i].ImgHeight + 41;
        }
        else
        {
            Base64.Decoder Decoder = Base64.getDecoder();
            byte[] bytes = Decoder.decode(wPnl.database.ImgCode[i]);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            try{
                BufferedImage img = ImageIO.read(bais);
                EntireBox[i].Img_Aver = (float)(img.getWidth()) / EntireBox[i].ImgWidth;
                EntireBox[i].ImgHeight = (int)(img.getHeight() / EntireBox[i].Img_Aver);
            }catch(IOException ex){}
            EntireBox[i].LineWidth = EntireBox[i].ImgWidth + 41;
            EntireBox[i].LineHeight = EntireBox[i].ImgHeight + 41;
        }
    }
    public void showControlLine()
    {
        line.setSize(LineWidth, LineHeight);
        
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        line.setLocation(x, y);
        line.setVisible(true);
        
        SE.setSize(5, 5);
        x = this.getLocation().x;
        y = this.getLocation().y;
        SE.setLocation(x + LineWidth, y + LineHeight);
        SE.setVisible(true);
        
        ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeRowTF.setText(Integer.toString(ImgWidth));
        ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeColTF.setText(Integer.toString(ImgHeight));

        ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeXTF.setText(Integer.toString(ImgBox.this.getLocation().x));
        ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeYTF.setText(Integer.toString(ImgBox.this.getLocation().y));

        //ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeAngleTF.setText(Double.toString(Math.round(ImgBox.this.angleRad*1.0) / 1.0));

        //ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeNoteBoderTF.setText(Integer.toString(ImgBoxBoder));
        int c_index = space.getLayer(this);
        ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).attributeLayerIndex.setText(Integer.toString(c_index));

        //ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).LineColorLb.setBackground(ImgBoxOutLineColor);
        //ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).BgColorLb.setBackground(ImgBoxBgColor);
        //ImgBox.this.space.parent.app.wallTabController.wallVector.get(ImgBox.this.space.parent.app.selectedIndex).TextColorLb.setBackground(ImgBoxTextColor);
    }
    public void hideControlLine()
    {
        SE.setVisible(false);
        
        line.setVisible(false);
    }
}
