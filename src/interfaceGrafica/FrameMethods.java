/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author JM
 */
public class FrameMethods {
    public static void setDefaultFrameConfig(Point location, JFrame frame){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(frame.getPreferredSize());
        frame.setLocation(location);
        frame.setVisible(true);
    }
    
    public static Font getFontPadraoToButtons(){
        return new Font("Aharoni", Font.PLAIN, 17);
    }
    
    public static Font getFontPadraoToTitles(){
        return new Font("MV Boli", Font.PLAIN, 36);
    }
    
    public static Font getFontPadraoToSubtitles(){
        return new Font("Calibri", Font.PLAIN, 24);
    }
    
    public static Color getColorPadrao(){
        return new Color(255,0,0);//new Color(153, 217,234);
    }
}
