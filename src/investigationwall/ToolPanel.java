package investigationwall;

import javax.swing.*;

public class ToolPanel extends JPanel{
    
    public MyButton btnMouse;
    public MyButton btnImage;
    public MyButton btnNote;
    public MyButton btnLine;
    public MyButton btnText;
    public MyButton btnPushpin;
    
    private GroupLayout toolPanelLayout;
    
            
    public ToolPanel()
    {
        initComponents();
    }

    private void initComponents()
    {
        
        this.btnMouse = new MyButton();
        this.btnImage = new MyButton();
        this.btnNote = new MyButton();
        this.btnLine = new MyButton();
        this.btnText = new MyButton();
        this.btnPushpin = new MyButton();

        this.btnMouse.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));  
        this.btnImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));
        this.btnNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));
        this.btnLine.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));      
        this.btnText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));       
        this.btnPushpin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,1,1,1));
        
        this.btnMouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ToolImages/mouse.png"))); // NOI18N
        this.btnImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ToolImages/image.png")));
        this.btnNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ToolImages/note.png")));
        this.btnLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ToolImages/line.png")));
        this.btnText.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ToolImages/text.png")));
        this.btnPushpin.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/ToolImages/pushpin.png")));

        this.toolPanelLayout = new GroupLayout(this);
        this.setLayout(this.toolPanelLayout);

        this.toolPanelLayout.setHorizontalGroup(
            this.toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMouse, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNote, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLine, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPushpin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        this.toolPanelLayout.setVerticalGroup(
            toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnMouse, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNote, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLine, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPushpin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 168, Short.MAX_VALUE))
        );
        
        

        this.setBackground(new java.awt.Color(80, 80, 100));
        
    }

    // public void openShapeSelectWindow(ShapeSelectWindow shapeSelect, int frameX, int frameY, boolean isClicked)
    // {
        
        
    //     this.btnNote.addMouseListener(new java.awt.event.MouseAdapter() {
    //         public void mouseClicked(java.awt.event.MouseEvent evt) {
    //             btnNoteClicked(shapeSelect, frameX, frameY, isClicked);
    //             System.out.println("Open shape select window");
    //         }
    //     });
        
        
    // }

    // private void btnNoteClicked(ShapeSelectWindow shapeSelect, int frameX, int frameY, boolean isClicked)
    // {
    //     if(isClicked == false)
    //     {
    //         shapeSelect.setBounds(frameX+100, frameY+230, 220, 300);
    //         // this.shapeSelect.setLocation(frameX+80, frameY+160);
    //         shapeSelect.setVisible(true);
    //         isClicked = true;
    //     }
    //     else
    //     {
    //         shapeSelect.setVisible(false);
    //         isClicked = true;
    //     }
    // }
}
