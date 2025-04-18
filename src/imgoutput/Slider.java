package imgoutput;
import investigationwall.MainFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
class Slider extends JPanel{
    JSlider jslider;
    float AverageW, AverageH;
    Graphics2D g2d;
    
    MainFrame parent;
    PhotoFrame photoFrame;
    Slider(MainFrame parent, PhotoFrame photoFrame)
    {
        super();
        this.parent = parent;
        this.photoFrame = photoFrame;
        this.setPreferredSize(new Dimension(570,25));
        this.setBackground(new Color(192, 192, 192));
        
        jslider= new JSlider();
        jslider.setPreferredSize(new Dimension(300,20));
        jslider.setBackground(new Color(192, 192, 192));
        jslider.setValue(100);
        this.add(jslider);
        
        AverageW = (float) (parent.Width - 800) / 100;
        AverageH = (float)(parent.Height - 600) / 100;
        
        jslider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e)
            {
                Slider.this.photoFrame.newW = (int)(800 + jslider.getValue() * AverageW);
                Slider.this.photoFrame.newH = (int)(600 + jslider.getValue() * AverageH);
                Slider.this.photoFrame.ScaleImage = new BufferedImage(Slider.this.photoFrame.newW,
                                                                      Slider.this.photoFrame.newH,
                                                                      BufferedImage.TYPE_3BYTE_BGR);
                g2d = Slider.this.photoFrame.ScaleImage.createGraphics();
                g2d.drawImage(Slider.this.photoFrame.Img, 0, 0, Slider.this.photoFrame.newW,
                                                                Slider.this.photoFrame.newH, null);
                resetPanel(Slider.this.photoFrame.ImgPanel,
                           Slider.this.photoFrame.ImgLabel,
                           Slider.this.photoFrame.icon,
                           Slider.this.photoFrame.ScaleImage,
                           Slider.this.photoFrame.newW,
                           Slider.this.photoFrame.newH);
            }
        });
    }
    void resetPanel(JPanel ImgPanel,
                   JLabel ImgLabel,
                   ImageIcon icon,
                   BufferedImage ScaleImage,
                   int newW, int newH)
    {
        ImgPanel.remove(ImgLabel);
        icon.setImage(ScaleImage);
        ImgLabel.setIcon(icon);
        ImgPanel.add(ImgLabel);
        ImgPanel.setPreferredSize(new Dimension(newW, newH));
        ImgPanel.revalidate();
    }
}
