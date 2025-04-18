package investigationwall;

import java.awt.*;
import java.awt.event.*;
import java.security.KeyStore.Entry.Attribute;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.w3c.dom.events.MouseEvent;

import javax.swing.*;

public class MenuBar extends JMenuBar{

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;

    public JMenuItem newWall;
    private JMenuItem openWall;
    private JMenuItem saveWall;
    private JMenuItem saveWallAs;
    private JMenuItem exportAsImg;

    private JMenuItem selectAll;
    private JMenuItem copy;
    private JMenuItem paste;
    public JMenuItem delete;
    private JMenuItem undo;
    private JMenuItem redo;

    private JMenuItem about;

    

    int x;
    
    
    public MenuBar()
    {
        init();
    }
    
    private void init()
    {
          
        initFile();
        initEdit();
        initHelp();
        
        
    }

    private void initFile()
    {
        
        this.fileMenu = new JMenu("檔案");
        
        this.newWall = new JMenuItem("新增調查牆");
        this.openWall = new JMenuItem("開啟調查牆");
        this.saveWall = new JMenuItem("儲存調查牆");
        this.saveWallAs = new JMenuItem("另存調查牆");
        this.exportAsImg = new JMenuItem("匯出成圖片");

        this.fileMenu.add(newWall);
        this.fileMenu.add(openWall);
        this.fileMenu.add(saveWall);
        this.fileMenu.add(saveWallAs);
        this.fileMenu.add(exportAsImg);
        this.add(this.fileMenu);
        

    }

    private void initEdit()
    {
        this.editMenu = new JMenu("編輯");

        this.selectAll = new JMenuItem("全選");
        this.copy= new JMenuItem("複製");
        this.paste = new JMenuItem("貼上");
        this.delete = new JMenuItem("刪除");
        this.undo = new JMenuItem("復原");
        this.redo = new JMenuItem("取消復原");
        
        this.editMenu.add(selectAll);
        this.editMenu.add(copy);
        this.editMenu.add(paste);
        this.editMenu.add(delete);
        this.editMenu.add(undo);
        this.editMenu.add(redo);

        this.add(this.editMenu);
    }

    private void initHelp()
    {
        this.helpMenu = new JMenu("說明");

        this.about = new JMenuItem("關於調查牆");

        this.helpMenu.add(about);

        this.add(this.helpMenu);
    }

   

    
}
