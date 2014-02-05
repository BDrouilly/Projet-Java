/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author Alexis
 */
public class Panel_Picture extends JPanel {
    
    private Image img = null;
    
    @Override
    public void paintComponent(Graphics g) {
    
        try {
        this.img = ImageIO.read(new File("./Saint_Pierre_De_Manneville.jpg")); 
        g.drawImage(this.img,0,0,this);
        } catch (IOException e) {e.printStackTrace();}
  
}
}
