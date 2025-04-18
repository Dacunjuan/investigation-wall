package imgoutput;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author WIN10
 */
public class Title extends JPanel{
    JLabel text;
    Title()
    {
        text = new JLabel("匯出所選圖片格式存放於電腦中");
        this.add(text);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(240, 240, 240));
    }
}
