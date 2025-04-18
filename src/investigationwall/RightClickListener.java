
package investigationwall;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import painting.PaintSpace;

public class RightClickListener extends MouseAdapter{
    PaintSpace ps;
    public RightClickListener(PaintSpace ps) {
        this.ps=ps;
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }
    private void doPop(MouseEvent e) {
        RightClcikMenu menu = new RightClcikMenu(ps);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
