package imgoutput;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.apache.commons.lang3.StringUtils;

public class FormatMenu extends JComboBox{
    JLabel text2;
    
    ImgOutput imgoutput;
    Preview preview;
    FormatMenu(ImgOutput imgoutput, Preview preview)
    {
        super();
        this.imgoutput = imgoutput;
        this.preview = preview;
        this.setPreferredSize(new Dimension(570, 25));
        this.addItem("JPEG 檔案");
        this.addItem("PNG 檔案");
        this.addItem("GIF 檔案");
        
        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String str = StringUtils.substringBeforeLast(FormatMenu.this.preview.jtextfield.getText(), ".");
                switch((String)FormatMenu.this.getSelectedItem())
                {
                    case "JPEG 檔案":
                        FormatMenu.this.imgoutput.Format = ".jpg";
                        FormatMenu.this.preview.jtextfield.setText(str+FormatMenu.this.imgoutput.Format);
                        break;
                    case "PNG 檔案":
                        FormatMenu.this.imgoutput.Format = ".png";
                        FormatMenu.this.preview.jtextfield.setText(str+FormatMenu.this.imgoutput.Format);
                        break;
                    case "GIF 檔案":
                        FormatMenu.this.imgoutput.Format = ".gif";
                        FormatMenu.this.preview.jtextfield.setText(str+FormatMenu.this.imgoutput.Format);
                        break;
                }
            }
        });
    }

}
