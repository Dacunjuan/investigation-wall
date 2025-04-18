package investigationwall;

import java.awt.*;
import javax.swing.*;

public class FunctionPanel extends JPanel{

    public MyButton btnNewWall;
    public MyButton btnSaveWall;
    public MyButton btnOpenWall;
    public MyButton btnExportAsImg;
    private MyButton btnUndo;
    private MyButton btnRedo;
      
    private GroupLayout functionPanelLayout;



    public FunctionPanel()
    {
        init();

    }

    private void init()
    {
        initFunctionButton();
        initFunctionPanel();
        
    }
    
    private void initFunctionPanel()
    {
        this.setBackground(new java.awt.Color(80, 80, 100));

        this.functionPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(functionPanelLayout);
        functionPanelLayout.setHorizontalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNewWall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnSaveWall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpenWall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportAsImg, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 648, Short.MAX_VALUE))
        );
        functionPanelLayout.setVerticalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSaveWall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenWall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportAsImg, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(functionPanelLayout.createSequentialGroup()
                        .addComponent(btnNewWall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(btnRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void initFunctionButton()
    {
        this.btnNewWall = new MyButton();
        this.btnSaveWall = new MyButton();
        this.btnOpenWall = new MyButton();
        this.btnExportAsImg = new MyButton();
        this.btnUndo = new MyButton();
        this.btnRedo = new MyButton();

        this.btnNewWall.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/FunctionImages/NewWall.png")));
        this.btnSaveWall.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/FunctionImages/Save.png")));
        this.btnOpenWall.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/FunctionImages/OpenWall.png")));
        this.btnExportAsImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/FunctionImages/Export.png")));
        this.btnUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/FunctionImages/Undo.png")));
        this.btnRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("Images/FunctionImages/Redo.png")));
    }



}
