/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painting;
import investigationwall.*;
import investigationwall.imginput.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
/**
 *
 * @author tony
 */
public class PaintSpace extends JLayeredPane{
    public Img img;
    int ImgWidth = 150, ImgHeight = 0;
    
    Point fp, p;
    int W = 0, H = 0;
    int lW, lH;
           public State state;
           public Point framePoint;
           public MyPanel parent;
           int x, y;
           public int w = 200, h = 200;
           public Shape activeShape;
           Point lineStart=null;
           Point lineEnd=null;
           
           Shape preShape;
           public Vector<Shape> shapeVector = new Vector<Shape>();

                    
           public PaintSpace(MyPanel p) {
                     super();
                     parent = p;                   
                     framePoint=parent.app.mainFrame.getLocation();
                     framePoint.x+=90;
                     framePoint.y+=165;
                     this.addMouseListener(new RightClickListener(this));
                     this.setLayout(null);
                     this.setSize(500, 500);
                     this.setBackground(Color.WHITE);
                     this.setBorder(BorderFactory.createLineBorder(Color.black));
                     this.addMouseListener(new MouseAdapter()
                     {
                               public void mouseClicked(MouseEvent e)
                               {
                                   ResetActive(parent.app);
                                   //------------------------painting-----------
                                        if(state==State.drawPushpin){
                                                  x=e.getX();
                                                  y=e.getY();
                                                  PushPin r = new PushPin(PaintSpace.this, x, y, -40, -40);
                                                  activeShape=r;
                                        }
                                        if(state==State.drawText){
                                                  x=e.getX();
                                                  y=e.getY();
                                                  Shape r = new iwLabel(PaintSpace.this, x, y, 150, 30);
                                                  activeShape=r;
                                        }
                                        /*if(activeShape!=null){
                                                  activeShape.state=State.idle;
                                                  activeShape.hideControlPoints();
                                                  activeShape=null;
                                        }*/
                                        if((state == State.drawOval) || (state == State.drawRect)|| (state == State.drawTri)){
                                                  x = e.getX();
                                                  y = e.getY();
                                                  if(state == State.drawOval){
                                                            Oval r = new Oval(PaintSpace.this, x, y, w, h);
                                                            //System.out.println("drawOval");
                                                            activeShape = r;
                                                  }
                                                  else if(state == State.drawRect){
                                                            Rectangle r = new Rectangle(PaintSpace.this, x, y, w, h);
                                                            activeShape = r;                                                          
                                                  }
                                                  else if(state == State.drawTri){
                                                            Triangle r = new Triangle(PaintSpace.this, x, y, w, h);
                                                            activeShape = r;                                                          
                                                  }           
                                        }
                                        
                                        //------------------imginput------------
                                        else if(state == State.ImgOperate)
                                        {
                                            System.out.println("AddImage");
                                            WallPanel wPnl=parent.app.wallTabController.wallVector.get(parent.app.selectedIndex);
                                            
                                            wPnl.database.IncreaseArraySize(State.ImgOperate);
                                            x = e.getX();
                                            y = e.getY();
                                            img = new Img(parent.app, PaintSpace.this, x, y, ImgWidth, ImgHeight, State.Add);
                                            wPnl.database.Target = wPnl.database.Img_i - 1;
                                            img.index = wPnl.database.Target;
                                            wPnl.database.EntireBox[wPnl.database.Target] = img;
                                            //System.out.print("Target: ");
                                            //System.out.println(wPnl.database.Target);
                                            state = State.idle;
                                        }
                                        
                               }
                               
                               public void mousePressed(MouseEvent e){
                                   //---------imginput----------
                                   if(state == State.Select)
                                    {
                                        fp = e.getPoint();
                                        W = e.getX();
                                        System.out.println("fp: "+fp);
                                    }
                                  
                               }
                               
                               public void mouseReleased(MouseEvent e){
                                   //---------imginput----------
                                   if(state == State.Select)
                                    {
                                        Graphics g = PaintSpace.this.getGraphics();
                                        g.clearRect(fp.x, fp.y, W, H);
                                        lW = 0;
                                        lH = 0;
                                        PaintSpace.this.repaint();
                                    }
                                   //----------painting----------
                                   else if(state == State.drawingALine){
                                        ConnectLine cLine = new ConnectLine(PaintSpace.this, lineStart, lineEnd);
                                        state=State.ready2DrawALine;
                                        lineStart=null;
                                        lineEnd=null;
                                   }
                               }
                     });
                     
                     this.addMouseMotionListener(new MouseAdapter(){
                               public void mouseDragged(MouseEvent e){
                                   //imginput-----------------------
                                   if(state == State.Select)
                                    {
                                        Graphics g = PaintSpace.this.getGraphics();
                                        Graphics2D g2d = (Graphics2D)g;

                                        System.out.println("Point.x: "+e.getX()+", Point.y: "+e.getY());
                                        W = e.getX() - fp.x;
                                        H = e.getY() - fp.y;
                                        System.out.println("W: " + W + ", H: " + H);

                                        g.clearRect(fp.x, fp.y, lW, lH);

                                        Color color = new Color(124, 124, 124);
                                        g2d.setColor(color);

                                        //g2d.setXORMode(new Color(0x00FFFF));
                                        g.fillRect(fp.x, fp.y, W, H);

                                        lW = W;
                                        lH = H;

                                    }
                                   
                               }
                     });
           }
           
           public Point getFramePoint(){
                     return framePoint;
           }
           
           @Override
           protected void paintComponent(Graphics g)
           {
                     super.paintComponent(g);
                     //g.drawString("123", 100 , 100);
                     framePoint=parent.app.mainFrame.getLocation();
                     framePoint.x+=90;
                     framePoint.y+=165;
                     
           }
           
            public void ResetActive(Application parent)
            {
                WallPanel wPnl=parent.wallTabController.wallVector.get(parent.selectedIndex);
                if(activeShape!=null){
                    activeShape.state=State.idle;
                    activeShape.hideControlPoints();
                    activeShape=null;
                }
                for(int i = 0;i < wPnl.database.row;i++)
                {
                    wPnl.database.EntireBox[i].ImgOperate = State.inActive;
                    wPnl.database.EntireBox[i].hideControlLine();
                }
            }
           
}
