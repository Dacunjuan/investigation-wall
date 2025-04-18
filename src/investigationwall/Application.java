package investigationwall;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.JFrame.*;
import javax.swing.plaf.TabbedPaneUI;

import java.awt.*;
import java.awt.event.*;
import java.nio.MappedByteBuffer;
import java.awt.Component.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import painting.State;
import imgoutput.ImgOutput;


public class Application {
    public MainFrame mainFrame;
    private MenuBar menuBar;

    private JPanel framePanel;
    private FunctionPanel functionPanel;
    private ToolPanel toolPanel;
    private JPanel mainPanel;
    

    public JTabbedPane mainTabbedPane;



    private MyButton btnSave;

    private GroupLayout framePanelLayout;
    private GroupLayout toolPanelLayout;
    private GroupLayout functionPanelLayout;
    private GroupLayout mainPanelLayout;
    private GroupLayout mainFrameLayout;

    public WallTabController wallTabController;


    public int frameX;
    public int frameY;
    public int tabNum = 0;
    public int howManyWall = -1;
    public int selectedIndex = -1;

    boolean isClicked = false;
    ShapeSelectWindow shapeSelect;

    String title = "Wall";
    
    public File ImgFile;
    public JFileChooser FileChooser;

    public Application()
    {
        init();
        
        
    }

    private void init()
    {
        this.mainFrame = new MainFrame("Investigation Wall");
        this.framePanel = new JPanel();
        this.menuBar = new MenuBar();
        this.shapeSelect = new ShapeSelectWindow(this);
        this.functionPanel = new FunctionPanel();
        this.toolPanel = new ToolPanel();
        this.mainPanel = new JPanel();
        this.mainTabbedPane = new JTabbedPane();


        this.mainFrameLayout = new GroupLayout(this.mainFrame.getContentPane());

        this.wallTabController = new WallTabController();

        initMainFrame();

        initFramePanel();

        // initFunctionPanel();

        
        // initToolPanel();

        

        // initFunctionPanelLayout();

        initToolButton();
       
        // initToolPanelLayout();
        
        initMainPanel();
        // initFramePanelLayout();
        initMainFrameLayout();

        
        this.menuBar.newWall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // addNewPage();
                // Application.this.howManyWall++;
                tabNum++;
                // wallTabController.addNewWallTab(mainTabbedPane, tabNum, howManyWall);
                wallTabController.addNewWallTab(Application.this);
                
                
            }
            
        });
        this.menuBar.delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(howManyWall!=-1){
                    WallPanel obj = wallTabController.wallVector.get(selectedIndex);
                    if(obj.myPanel.paintSpace.activeShape!=null){
                        obj.myPanel.paintSpace.activeShape.removeShape();
                        obj.myPanel.paintSpace.repaint();
                    } 
                }else{
                    System.out.println("There's no wall");
                }
                     
            }  
        });


        this.functionPanel.btnNewWall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // addNewPage();
                // Application.this.howManyWall++;
                tabNum++;
                // wallTabController.addNewWallTab(mainTabbedPane, tabNum, howManyWall);
                wallTabController.addNewWallTab(Application.this);
            }
        });
        
        this.functionPanel.btnExportAsImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WallPanel wPnl = Application.this.wallTabController.wallVector.get(Application.this.selectedIndex);
                wPnl.myPanel.paintSpace.ResetActive(Application.this);
                new ImgOutput(Application.this);
            }
        });
        
        this.mainTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mainTabbedPaneStateChanged(evt);
            }
        });
        
        this.toolPanel.btnMouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(howManyWall!=-1){
                    WallPanel obj = wallTabController.wallVector.get(selectedIndex);
                    obj.myPanel.paintSpace.state = State.mouse;
                }else{
                    System.out.println("There's no wall");
                }
            }
        });
        
        this.toolPanel.btnImage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                FileChooser = new JFileChooser();
                FileChooser.setDialogTitle("匯入圖片");
                FileChooser.setCurrentDirectory(new File(""));
                FileChooser.setFileFilter(new FileNameExtensionFilter("JPEG (*.jpg)", "jpg"));
                FileChooser.setFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));
                FileChooser.setFileFilter(new FileNameExtensionFilter("GIF (*.gif)", "gif"));
                int result = FileChooser.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    Application.this.ImgFile = new File(FileChooser.getSelectedFile().getAbsolutePath());
                    System.out.println(Application.this.ImgFile);
                    // space.ResetActive(parent);
                    WallPanel obj = wallTabController.wallVector.get(selectedIndex);
                    obj.myPanel.paintSpace.state = State.ImgOperate;
                    System.out.println("State: "+obj.myPanel.paintSpace.state);
                }
            }
        });
        
        this.toolPanel.btnLine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(howManyWall!=-1){
                    WallPanel obj = wallTabController.wallVector.get(selectedIndex);
                    obj.myPanel.paintSpace.state = State.ready2DrawALine;
                    System.err.println(obj.myPanel.paintSpace.state);
                }else{
                    System.out.println("There's no wall");
                }
            }
        });
        
        this.toolPanel.btnPushpin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(howManyWall!=-1){
                    WallPanel obj = wallTabController.wallVector.get(selectedIndex);
                    obj.myPanel.paintSpace.state = State.drawPushpin;
                }else{
                    System.out.println("There's no wall");
                }
            }
        });
        
        this.toolPanel.btnText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(howManyWall!=-1){
                    WallPanel obj = wallTabController.wallVector.get(selectedIndex);
                    obj.myPanel.paintSpace.state = State.drawText;
                }else{
                    System.out.println("There's no wall");
                }
            }
        });



    }
    private void mainTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt)
    {
        this.selectedIndex = this.mainTabbedPane.getSelectedIndex();
        if(Application.this.howManyWall!=1){
            WallPanel obj = wallTabController.wallVector.get(selectedIndex);
            obj.myPanel.paintSpace.state = State.mouse;
        }
        //System.out.println("The selected index is " + selectedIndex);
    }
    private void initMainFrame()
    {
        // Dimension myScreen = Toolkit.getDefaultToolkit().getScreenSize();
        
        // this.mainFrame.setLayout(new GridLayout(3, 5));
        // this.mainFrame.setSize(500, 500);
        // this.mainFrame.setLocation(myScreen.width/2-150, myScreen.height/2-150);
        
        // this.mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // this.mainFrame.setBackground(new java.awt.Color(131, 131, 131));
        // this.mainFrame.setPreferredSize(new java.awt.Dimension(1050, 600));
        this.mainFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });
        this.mainFrame.addWindowFocusListener(new java.awt.event.WindowFocusListener(){
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        this.mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                if(Application.this.howManyWall!=-1){
                    System.out.println("There are still walls");
                    Application.this.wallTabController.exitConfirmAll(Application.this);
                }else{
                    System.out.println("There is no wall");
                    System.exit(0);
                }
            }
        });


    }

    private void initFramePanel() // The panel includes toolPanel, functionPane, and mainPanel
    {
        this.framePanel.setBackground(new java.awt.Color(40, 40, 60));

        this.framePanelLayout =  new javax.swing.GroupLayout(framePanel);
        this.framePanel.setLayout(framePanelLayout);
        this.framePanelLayout.setHorizontalGroup(
            framePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(framePanelLayout.createSequentialGroup()
                .addComponent(toolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(functionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        this.framePanelLayout.setVerticalGroup(
            framePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, framePanelLayout.createSequentialGroup()
                .addComponent(functionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(framePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        
    }

 
    private void initToolButton()
    {
        
        this.toolPanel.btnNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShapeMouseClicked(evt);
            }
        });

        
    }

    private void initMainPanel() //The panel includes mainTabbedPane
    {
        this.mainPanel.setBackground(new java.awt.Color(40, 40, 60));
        
        this.mainPanelLayout = new GroupLayout(mainPanel);
        this.mainPanel.setLayout(mainPanelLayout);
        this.mainPanelLayout.setHorizontalGroup(
            this.mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        this.mainPanelLayout.setVerticalGroup(
            this.mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane)
        );
    }

   
    private void initMainFrameLayout()
    {
        
        this.mainFrame.getContentPane().setLayout(this.mainFrameLayout);
        this.mainFrame.getContentPane().setLayout(mainFrameLayout);
        
        this.mainFrameLayout.setHorizontalGroup(
            this.mainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );
        this.mainFrameLayout.setVerticalGroup(
            this.mainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            
            );
        this.mainFrame.setJMenuBar(this.menuBar);
        this.mainFrame.setVisible(true);

        
    }

    
    private void btnShapeMouseClicked(java.awt.event.MouseEvent evt)
    {
        if(isClicked == false)
        {
            this.shapeSelect.setBounds(frameX+100, frameY+230, 220, 300);
            // this.shapeSelect.setLocation(frameX+80, frameY+160);
            this.shapeSelect.setVisible(true);
            this.isClicked = true;
            
        }
        else
        {
            this.shapeSelect.setVisible(false);
            this.isClicked = true;
        }
        //System.out.println("Open the shape select window");
    }

    
    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        frameX = mainFrame.getX();
        frameY = mainFrame.getY();
        
        //System.out.println("X:" +  frameX + "  Y:" + frameY);

    }//GEN-LAST:event_formComponentMoved

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        shapeSelect.setVisible(false);
        isClicked = false;

        
        
    }//GEN-LAST:event_formWindowGainedFocus
}
