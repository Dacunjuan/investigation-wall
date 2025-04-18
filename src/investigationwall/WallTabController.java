package investigationwall;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.MouseEvent.*;

import javax.swing.*;

import java.io.*;
import java.util.Vector;

public class WallTabController {
    public JButton btnClose;
    public WallPanel wallPanel;
    public Vector<WallPanel> wallVector = new Vector<WallPanel>();
    public void addNewWallTab(Application parent)
    {
        if(parent.howManyWall+1 < 20)
        {
            parent.howManyWall++;
            String title = "Wall ";
            wallPanel = new WallPanel(new BorderLayout(),parent);
            wallVector.add(wallPanel);
            new AttributeListener(parent);
            
            //System.out.println("Add " + title + parent.tabNum);
            
            parent.mainTabbedPane.addTab(title + parent.tabNum, wallPanel);

            int index = parent.mainTabbedPane.indexOfTab(title+parent.tabNum);
            JPanel pnlTabTitle = new JPanel(new GridBagLayout());
            pnlTabTitle.setOpaque(false);

            JLabel labelTitle = new JLabel(title+parent.tabNum);
            
            this.btnClose = new JButton();
            this.btnClose.setText("x");
            this.btnClose.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));

            GridBagConstraints tabTitlegbc = new GridBagConstraints();
            tabTitlegbc.gridx = 0;
            tabTitlegbc.gridy = 0;
            tabTitlegbc.weightx = 0;
            pnlTabTitle.add(labelTitle, tabTitlegbc);
            tabTitlegbc.gridx++;
            tabTitlegbc.weightx = 0;
            pnlTabTitle.add(wallPanel.labelSavedStar, tabTitlegbc);
            tabTitlegbc.gridx++;
            pnlTabTitle.add(this.btnClose, tabTitlegbc);
            parent.mainTabbedPane.setTabComponentAt(index, pnlTabTitle);

            System.out.println("there are " + parent.howManyWall + " walls");
            
            this.btnClose.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    closeWallTab(parent, labelTitle);
                }
            });

            labelTitle.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)
                    {
                        System.out.println("rename title");
                        renameTabTitle(parent, labelTitle); 
                    }
                    else
                    {
                        System.out.println("Switch tab");
                        int index = parent.mainTabbedPane.indexOfTab(labelTitle.getText());
                        parent.mainTabbedPane.setSelectedIndex(index);
                    }
                }
            });

        }
        else
        {
            JLabel upperLimitWarning = new JLabel("調查牆上限為20！");
            upperLimitWarning.setFont(new Font("新細明體", Font.BOLD, 16));
            JOptionPane.showConfirmDialog(parent.mainFrame, 
                                          upperLimitWarning,
                                          "警告",
                                          JOptionPane.DEFAULT_OPTION,
                                          JOptionPane.WARNING_MESSAGE);
        }
    }

    public void closeWallTab(Application parent, JLabel labelTitle)
    {
        //parent.mainTabbedPane.removeTabAt(index);
        System.out.println("there are " + parent.howManyWall + " walls");
        int index = parent.mainTabbedPane.indexOfTab(labelTitle.getText());
        if (index >= 0){
            String[] closeInfoSingle = {"儲存", "不儲存","取消"};
            int exitResult = exitConfirm(parent, index,closeInfoSingle);
            if (exitResult==0){
                String sb = "TEST HEHE";     //儲存檔案內部資料
                JFileChooser saveFileChooser = new JFileChooser();
                String currentdir = System.getProperty("user.dir");
                currentdir = currentdir.replace( "\\", "/" );
                saveFileChooser.setCurrentDirectory(new File(currentdir));
                saveFileChooser.setDialogTitle("Save Wall");
                int userSelection = saveFileChooser.showSaveDialog(parent.mainFrame);
                if(userSelection==JFileChooser.APPROVE_OPTION){
                    try (FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile()+".itw")){
                        fw.write(sb.toString());
                    }catch(Exception ex) {
                        ex.printStackTrace();
                    }
                    wallVector.remove(index);
                    parent.howManyWall--;
                    try{
                        parent.mainTabbedPane.removeTabAt(index);
                    }catch(Exception ex){
                        System.err.println(ex);
                    }
                }
            }else if(exitResult==1){
                wallVector.remove(index);
                parent.howManyWall--;
                try{
                    parent.mainTabbedPane.removeTabAt(index);
                }catch(Exception ex){
                    System.err.println(ex);
                }
                
            }
        }
        
    }

    public void renameTabTitle(Application parent, JLabel labelTitle)
    {
        JDialog renameTab = new JDialog(parent.mainFrame,"Rename Tab's Title",true);
        JPanel renamePnl = new JPanel();
        JLabel renameLbl = new JLabel("新標題 ： ");
        renameLbl.setFont(new Font("新細明體", Font.BOLD, 20));
        JTextField renameTf = new JTextField("",20);
        JButton renameOK = new JButton("OK");
        JButton renameCancel = new JButton("Cancel");
        renamePnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        renamePnl.setLayout(new GridLayout(2,2));
        renamePnl.add(renameLbl);
        renamePnl.add(renameTf);
        renamePnl.add(renameOK);
        renamePnl.add(renameCancel);
        
        renameTab.add(renamePnl);
        renameTab.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        renameTab.setBounds(parent.frameX+350, parent.frameY+200, 300, 150);
        
        renameOK.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(renameTf.getText().length() != 0){
                    int index = parent.mainTabbedPane.indexOfTab(labelTitle.getText());
                    parent.mainTabbedPane.setTitleAt(index, renameTf.getText());
                    labelTitle.setText(renameTf.getText());
                    renameTab.setVisible(false);
                }else{
                    JLabel emptyTitleWarning = new JLabel("標題字數不能為0!!");
                    emptyTitleWarning.setFont(new Font("新細明體", Font.BOLD, 16));
                    JOptionPane.showConfirmDialog(renameTab,
                    emptyTitleWarning,
                    "Warning",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                }
            }                        
        });
        renameCancel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                renameTab.setVisible(false);
            }     
        });
        
        renameTab.setVisible(true);
    }

    public void exitConfirmAll(Application parent)
    {
        boolean isCancel=false;     //再詢問儲存的時候是否按下Cancel
        String[] closeInfo = {"儲存", "不儲存", "全部不儲存", "取消"};
        for(int i=0;i<=parent.howManyWall;i++){
            int result=exitConfirm(parent, i, closeInfo);
            if (result==0){
                String sb = "TEST CONTENT";     //儲存檔案內部資料
                JFileChooser saveFileChooser = new JFileChooser();
                String currentdir = System.getProperty("user.dir");
                currentdir = currentdir.replace( "\\", "/" );
                saveFileChooser.setCurrentDirectory(new File(currentdir));
                saveFileChooser.setDialogTitle("Save Wall");
                int userSelection = saveFileChooser.showSaveDialog(parent.mainFrame);
                if(userSelection==JFileChooser.APPROVE_OPTION){
                    try (FileWriter fw = new FileWriter(saveFileChooser.getSelectedFile()+".itw")){
                        fw.write(sb.toString());
                    }catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }else if(userSelection==JFileChooser.CANCEL_OPTION){
                    i--;
                }
            }else if(result==2){
                break;
            }else if(result==3){
                isCancel=true;
                break;
            }else if(result==JOptionPane.CLOSED_OPTION){
                isCancel=true;
                break;
            }
        }
        if(isCancel==false)
            System.exit(0);
    }

    public int exitConfirm(Application parent, int i,String[] closeInfo)
    {
        int result=JOptionPane.showOptionDialog(parent.mainFrame,
                    "是否要變更儲存 "+parent.mainTabbedPane.getTitleAt(i)+" 再結束?",
                    "確認訊息",
                    0,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    closeInfo,
                    closeInfo[0]);
        // System.out.println("result = " + result);
        return result;
        
    }
    
    
}


