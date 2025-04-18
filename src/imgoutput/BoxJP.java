
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

class BoxJP extends JPanel{
    SelectText selectText = null;
    FormatMenu formatMenu = null;
    CheckBox checkBox = null;
    PhotoFrame photoFrame = null;
    Slider slider = null;
    Preview preview = null;
    
    ImgOutput imgoutput;
    BoxJP(Application parent, ImgOutput imgoutput)
    {
        super();
        this.imgoutput = imgoutput;
        this.setBackground(new Color(192, 192, 192));
        if(selectText == null)
            selectText = new SelectText();
        if(checkBox == null)
            checkBox = new CheckBox(imgoutput);
        if(photoFrame == null)
            photoFrame = new PhotoFrame(parent);
        if(slider == null)
            slider = new Slider(parent.mainFrame, photoFrame);
        if(preview == null)
            preview = new Preview(imgoutput, checkBox);
        if(formatMenu == null)
            formatMenu = new FormatMenu(imgoutput, preview);
        
        this.add(selectText);
        this.add(formatMenu);
        this.add(checkBox);
        this.add(photoFrame);
        this.add(slider);
        this.add(preview);
    }
    
}
