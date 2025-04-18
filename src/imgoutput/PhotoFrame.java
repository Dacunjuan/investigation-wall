package imgoutput;
import investigationwall.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.LineBorder;
class PhotoFrame extends JScrollPane{
    JPanel ImgPanel;
    BufferedImage Img;
    BufferedImage ScaleImage;
    ImageIcon icon;
    JLabel ImgLabel;
    Graphics2D g2d;
    Point p;
    int newW, newH;
    int VerticalValue = 0, HorizontalValue = 0;
    //Wall parent;
    PhotoFrame(Application parent)
    {
        super();
        //this.parent = parent;
        ImgPanel = new JPanel();
        ImgPanel.setPreferredSize(new Dimension(parent.mainFrame.Width, parent.mainFrame.Height));
        Img = new BufferedImage(parent.mainFrame.Width, parent.mainFrame.Height, BufferedImage.TYPE_3BYTE_BGR);
        WallPanel wPnl = parent.wallTabController.wallVector.get(parent.selectedIndex);
        wPnl.myPanel.paint(Img.createGraphics());
        ScaleImage = new BufferedImage(parent.mainFrame.Width, parent.mainFrame.Height, BufferedImage.TYPE_3BYTE_BGR);
        ScaleImage = Img;
        icon= new ImageIcon(ScaleImage);
        ImgLabel = new JLabel(icon);
        ImgPanel.add(ImgLabel);

        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        this.setBorder(new LineBorder(Color.BLACK, 2, true));
        this.setPreferredSize(new Dimension(570, 430));
        this.setViewportView(ImgPanel);
        
        ImgPanel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e)
            {
                p = e.getLocationOnScreen();//讀取滑鼠當下位置
            }
        });
        newW = parent.mainFrame.Width;
        newH = parent.mainFrame.Height;
        ImgPanel.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e)
            {
                PhotoFrame.this.getVerticalScrollBar().getModel().setExtent(430);
                PhotoFrame.this.getHorizontalScrollBar().getModel().setExtent(570);
                int H_extent = PhotoFrame.this.getHorizontalScrollBar().getModel().getExtent();
                int V_extent = PhotoFrame.this.getVerticalScrollBar().getModel().getExtent();
                if((H_extent != 570)||(V_extent!=430))
                    H_extent = 570; V_extent = 430;
                int HLimit, VLimit;
                HLimit = newW - H_extent;
                int Hnum = p.x - e.getLocationOnScreen().x;
                HorizontalValue += Hnum;
                if(HorizontalValue < 0) HorizontalValue = 0;
                if(HorizontalValue > HLimit) HorizontalValue = HLimit;
                PhotoFrame.this.getHorizontalScrollBar().setValue(HorizontalValue);
                p.x = e.getLocationOnScreen().x;
                
                VLimit = newH - V_extent;
                int Vnum = p.y - e.getLocationOnScreen().y;
                VerticalValue += Vnum;
                if(VerticalValue < 0) VerticalValue = 0;
                if(VerticalValue > VLimit) VerticalValue =VLimit;
                PhotoFrame.this.getVerticalScrollBar().setValue(VerticalValue);
                p.y = e.getLocationOnScreen().y; 
            }
        });
    }
}
