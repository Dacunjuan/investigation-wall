
package investigationwall;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import painting.PaintSpace;

public class RightClcikMenu extends JPopupMenu{
    JMenuItem itemAllSelect;
    JMenuItem itemCopy;
    JMenuItem itemPaste;
    JMenuItem itemDelete;
    JMenuItem itemUndo;
    JMenuItem itemRedo;
    public RightClcikMenu(PaintSpace ps) {
        itemAllSelect = new JMenuItem("全選");
        itemCopy = new JMenuItem("複製");
        itemPaste = new JMenuItem("貼上");
        itemDelete = new JMenuItem("刪除");
        itemUndo = new JMenuItem("復原");
        itemRedo = new JMenuItem("取消復原");
        
        this.add(itemAllSelect);
        this.add(itemCopy);
        this.add(itemPaste);
        this.add(itemDelete);
        this.add(itemUndo);
        this.add(itemRedo);
        
        this.itemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(ps.activeShape!=null){
                    ps.activeShape.removeShape();
                    ps.repaint();
                }      
            }  
        });

    }
}
