/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painting;

/**
 *
 * @author tony
 */
public enum State{
    drawOval, drawRect, drawTri, idle, actived, ready2Move, moving, mouse,
    resizing, ready2Resize, ready2DrawALine, drawingALine, rotate, drawPushpin,
    drawText
    
    , Entire, ImgOperate, Add, Open, Select, Pasting
    , Active, inActive, Moving, reSizing, NailOperate;
}
