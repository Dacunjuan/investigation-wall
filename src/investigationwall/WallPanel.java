package investigationwall;

import investigationwall.imginput.DataBase;
import investigationwall.imginput.Img;
import java.awt.*;
import java.awt.event.*;
import java.security.KeyStore.Entry.Attribute;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.MouseEvent.*;

import javax.swing.*;


public class WallPanel extends JPanel{
    public JLabel labelSavedStar = new JLabel("*");
    public boolean isSaved = false;
    public String savingName = null;
    public MyPanel myPanel;
    public Img clickComponentImg;
    
    public DataBase database;
    
    public JTextField attributeRowTF;
    public JTextField attributeColTF;
    public JTextField attributeXTF;
    public JTextField attributeYTF;
    public JTextField attributeAngleTF;
    public JTextField attributeNoteBoderTF;
    public JLabel attributeLayerIndex;
    
    public ChooseColorLabel LineColorLb;
    public ChooseColorLabel BgColorLb;
    public ChooseColorLabel TextColorLb;
    
    public JButton layerUp;
    public JButton layerDown;
    
    public WallPanel(BorderLayout layout,Application app)
    {
        super(layout);
        this.setPreferredSize(new Dimension(1100, 0));

        myPanel = new MyPanel(app);
        myPanel.setPreferredSize(new Dimension(1000, 1000));
        database = new DataBase(app, myPanel.paintSpace);
        
        JScrollPane mainScrollPane = new JScrollPane(myPanel);
        mainScrollPane.setPreferredSize(new Dimension(900, 0));

        JPanel attributePanel = new JPanel(new GridBagLayout());
        attributePanel.setBackground(new Color(150,150,180));
        GridBagConstraints attributeGBC = new GridBagConstraints();
        
        //寬度
        JLabel attributeRow = new JLabel("寬度：");
        attributeRow.setFont(new Font("新細明體", Font.BOLD, 16));
        attributeRowTF = new JTextField(8);
        //高度
        JLabel attributeCol = new JLabel("高度：");
        attributeCol.setFont(new Font("新細明體", Font.BOLD, 16));
        attributeColTF = new JTextField(8);
        //元件座標X
        JLabel attributeX = new JLabel("X：");
        attributeX.setFont(new Font("新細明體", Font.BOLD, 16));
        attributeXTF = new JTextField(8);
        //元件座標Y
        JLabel attributeY = new JLabel("Y：");
        attributeY.setFont(new Font("新細明體", Font.BOLD, 16));
        attributeYTF = new JTextField(8);
        //角度
        JLabel attributeAngle = new JLabel("角度：");
        attributeAngle.setFont(new Font("新細明體", Font.BOLD, 16));
        attributeAngleTF = new JTextField(8);
        //邊框粗細
        JLabel attributeNoteBoder = new JLabel("邊框粗細：");
        attributeNoteBoder.setFont(new Font("新細明體", Font.BOLD, 16));
        attributeNoteBoderTF = new JTextField(8);
        //線段顏色
        JLabel attributeLineColor = new JLabel("線段顏色：");
        attributeLineColor.setFont(new Font("新細明體", Font.BOLD, 16));
        LineColorLb = new ChooseColorLabel(app);
        //背景顏色
        JLabel attributeBgColor = new JLabel("背景顏色：");
        attributeBgColor.setFont(new Font("新細明體", Font.BOLD, 16));
        BgColorLb = new ChooseColorLabel(app);
        //文字顏色
        JLabel attributeTextColor = new JLabel("文字顏色：");
        attributeTextColor.setFont(new Font("新細明體", Font.BOLD, 16));
        TextColorLb = new ChooseColorLabel(app);
        //元件圖層
        JLabel attributeObjLayer = new JLabel("元件圖層：");
        attributeObjLayer.setFont(new Font("新細明體", Font.BOLD, 16));
        //LayerIndex
        attributeLayerIndex = new JLabel("LayerIndex");
        attributeLayerIndex.setFont(new Font("新細明體", Font.BOLD, 16));

    //ADD-----------------------------------------------------------------------
        //寬度
        attributeGBC.gridx = 0; attributeGBC.gridy = 0;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeRow,attributeGBC);

        attributeGBC.gridx = 1; attributeGBC.gridy = 0;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeRowTF, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 1;
        attributePanel.add(new JLabel(" "), attributeGBC);
        //高度
        attributeGBC.gridx = 0; attributeGBC.gridy = 2;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeCol,attributeGBC);

        attributeGBC.gridx = 1; attributeGBC.gridy = 2;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeColTF, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 3;
        attributePanel.add(new JLabel(" "), attributeGBC);
        //元件座標X
        attributeGBC.gridx = 0; attributeGBC.gridy = 4;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeX,attributeGBC);

        attributeGBC.gridx = 1; attributeGBC.gridy = 4;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeXTF, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 5;
        attributePanel.add(new JLabel(" "), attributeGBC); //line 414
        //元件座標Y
        attributeGBC.gridx = 0; attributeGBC.gridy = 6;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeY, attributeGBC);

        attributeGBC.gridx = 1; attributeGBC.gridy = 6;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeYTF, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 7;
        attributePanel.add(new JLabel(" "), attributeGBC);
        //角度
        attributeGBC.gridx = 0; attributeGBC.gridy = 8;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeAngle, attributeGBC);

        attributeGBC.gridx =1; attributeGBC.gridy = 8;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeAngleTF, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 9;
        attributePanel.add(new JLabel(" "), attributeGBC);
        //邊框粗細
        attributeGBC.gridx = 0; attributeGBC.gridy = 10;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeNoteBoder, attributeGBC);

        attributeGBC.gridx =1; attributeGBC.gridy = 10;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeNoteBoderTF, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 11;
        attributePanel.add(new JLabel(" "), attributeGBC);
        //線段顏色
        attributeGBC.gridx = 0; attributeGBC.gridy = 12;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeLineColor, attributeGBC);

        attributeGBC.gridx =1; attributeGBC.gridy = 12;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(LineColorLb, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 13;
        attributePanel.add(new JLabel(" "), attributeGBC);
        //背景顏色
        attributeGBC.gridx = 0; attributeGBC.gridy = 14;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeBgColor, attributeGBC);

        attributeGBC.gridx =1; attributeGBC.gridy = 14;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(BgColorLb, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 15;
        attributePanel.add(new JLabel(" "), attributeGBC);
        //文字顏色
        attributeGBC.gridx = 0; attributeGBC.gridy = 16;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeTextColor, attributeGBC);

        attributeGBC.gridx =1; attributeGBC.gridy = 16;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(TextColorLb, attributeGBC);

        attributeGBC.gridx = 0; attributeGBC.gridy = 17;
        attributePanel.add(new JLabel(" "), attributeGBC);

        //圖層
        attributeGBC.fill = GridBagConstraints.NONE;
        
        attributeGBC.gridx = 0; attributeGBC.gridy = 18;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeObjLayer, attributeGBC);
        
        attributeGBC.gridx = 1; attributeGBC.gridy = 18;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(attributeLayerIndex, attributeGBC);
        
        
        moveLayer layerScript = new moveLayer(this);
        layerUp = new JButton("上移一層");
        layerUp.addMouseListener(new MouseAdapter()
        {
                  public void mouseClicked(MouseEvent e)
                  {
                       //JComponent clickComponent = myPanel.paintSpace.clickComponent;
                       
                       painting.Shape clickComponent = myPanel.paintSpace.activeShape;
                       System.out.print("UP Target: ");
                       System.out.println(WallPanel.this.database.Target);
                       if(WallPanel.this.database.Target != -1){
                           clickComponentImg = (Img) WallPanel.this.database.EntireBox[WallPanel.this.database.Target];
                       }           
                       JLayeredPane layeredPane = myPanel.paintSpace;

                       if(layeredPane == null){
                           System.out.println("NULL layeredPane");
                       }
                       if(clickComponent == null){
                           System.out.println("comImg up");
                           
                           layerScript.moveUpLayer(layeredPane, clickComponentImg);
                       }else if(clickComponentImg == null){
                           System.out.println("shape up");
                           layerScript.moveUpLayer(layeredPane, clickComponent);
                       }
                  }
        });
        attributeGBC.gridx =0; attributeGBC.gridy = 19;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(layerUp, attributeGBC);
        
        layerDown = new JButton("下移一層");
        layerDown.addMouseListener(new MouseAdapter()
        {
                  public void mouseClicked(MouseEvent e)
                  {
                       //JComponent clickComponent = myPanel.paintSpace.clickComponent;
                       painting.Shape clickComponent = myPanel.paintSpace.activeShape;
                       System.out.print("DOWN Target: ");
                       System.out.println(WallPanel.this.database.Target);
                       if(WallPanel.this.database.Target != -1){
                           clickComponentImg = (Img) WallPanel.this.database.EntireBox[WallPanel.this.database.Target];
                       }  
                       JLayeredPane layeredPane = myPanel.paintSpace;

                       if(layeredPane == null){
                           System.out.println("NULL layeredPane");
                       }
                       if(clickComponent == null){
                           System.out.println("comImg Down");
                           layerScript.moveDownLayer(layeredPane, clickComponentImg);
                       }else if(clickComponentImg == null){
                           System.out.println("shape Down");
                           layerScript.moveDownLayer(layeredPane, clickComponent);
                       }                                          
                  }
        });
        attributeGBC.gridx =1; attributeGBC.gridy = 19;
        attributeGBC.gridwidth = 1; attributeGBC.gridheight = 1;
        attributePanel.add(layerDown, attributeGBC);

        JScrollPane attributeScrollPane = new JScrollPane(attributePanel, 
                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        attributeScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
        attributeScrollPane.setPreferredSize(new Dimension(300, 0));

        
        this.add(mainScrollPane, BorderLayout.CENTER);
        this.add(attributeScrollPane, BorderLayout.EAST);
    }
    
    
}
