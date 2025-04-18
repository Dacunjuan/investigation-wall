/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investigationwall.imginput;
import java.awt.*;
import java.awt.BasicStroke;
import javax.swing.JComponent;

import investigationwall.*;
import painting.PaintSpace;

public class Select extends JComponent{
    int MinX = 0, MinY = 0;
    int MaxWidth = 0, MaxHeight = 0;
    int[][] Data;
    int SelectWidth = 0, SelectHeight = 0;
    int dist = 20;
    public Select(Application parent, PaintSpace space)
    {
        super();
        this.setBackground(Color.black);
        System.out.println("(Select)");
        
        WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
        
        if(wPnl.database.EntireBox != null)
        {
            MinX = wPnl.database.EntireBox[0].x;
            MinY = wPnl.database.EntireBox[0].y;
            int num = 0;
            for(int i = 0;i < wPnl.database.row;i++)
            {
                if(MinX >= wPnl.database.EntireBox[i].x)
                    MinX = wPnl.database.EntireBox[i].x;
                if(MinY >= wPnl.database.EntireBox[i].y)
                    MinY = wPnl.database.EntireBox[i].y;
                
                num = wPnl.database.EntireBox[i].x + wPnl.database.EntireBox[i].ImgWidth;
                if(MaxWidth <= num)
                    MaxWidth = num;
                num = wPnl.database.EntireBox[i].y + wPnl.database.EntireBox[i].ImgHeight;
                if(MaxHeight <= num)
                    MaxHeight = num;
            }
            /*--------------------------*/
            
        }
        
        
        /*if(parent.ImgData != null)
        {
            Data = new int[parent.row][parent.col - 1];
            for(int i = 0;i < parent.row;i++)//String to Int
                for(int j = 0; j < parent.col - 1;j++)
                    Data[i][j] = Integer.parseInt(parent.ImgData[i][j]);
            
            for(int i = 0;i < parent.row;i++)//check
            {
                for(int j = 0; j < parent.col - 1;j++)
                    System.out.println("data["+i+"]["+j+"]: "+Data[i][j]);
                System.out.println();
            }
            /*--------------------------
            System.out.println("/*--------------------------");
            MinX = Data[0][0];
            MinY = Data[0][1];
            int num = 0;
            for(int i =0;i < parent.row;i++)
            {
                if(MinX >= Data[i][0])//找最小X值
                    MinX = Data[i][0];
                if(MinY >= Data[i][1])//找最小Y值
                    MinY = Data[i][1];
                num = Data[i][0] + Data[i][2];//MaxWidth
                if(MaxWidth <= num)
                    MaxWidth = num;
                
                num = Data[i][1] + Data[i][3];//MaxHeight
                if(MaxHeight <= num)
                    MaxHeight = num;
            }
            /*----------------------*/
            System.out.println("MinX: " + MinX + ", MinY: " + MinY);
            System.out.println("MaxWidth: " + MaxWidth + ", MaxHeight: " + MaxHeight);
            
            SelectWidth = MaxWidth - MinX + dist * 2 + 1;
            SelectHeight = MaxHeight - MinY + dist * 2 + 1;
        //}
        
        space.add(this);
        this.setSize(SelectWidth, SelectHeight);
        this.setLocation(MinX - 20, MinY - 20);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        //System.out.println("<>"+(MaxWidth-MinX + 40)+", "+(MaxHeight-MinY + 40));
        //g2d.drawRect(0, 0, MaxWidth + 20, MaxHeight + 20);
        g2d.drawRect(0, 0, SelectWidth - 1, SelectHeight - 1);
        g2d.setStroke(new BasicStroke(1));
    }
    
}
