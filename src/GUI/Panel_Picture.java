/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import JobPackage.Poi;
import JobPackage.Poi.*;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


/**
 *
 * @author Alexis
 */
public class Panel_Picture extends JPanel {
    
    private static Image img = null;
    private static Image imgPOI = null;
    
    private static ArrayList<Poi> pois = null;
    
    
    public void setPois(ArrayList<Poi> poi)
    {
        this.pois = poi;
    }
    
    public void setMap(Image img)
    {
        this.img = img;
    }
    
    @Override
    public void paintComponent(Graphics g) {
    
        try {
        this.img = ImageIO.read(new File("./Saint_Pierre_De_Manneville.jpg"));
        this.imgPOI = ImageIO.read(new File("./addmark.PNG")); 
        g.drawImage(this.img,0,0,this);
        
        if(pois != null)
        {
            for (Poi poi : pois)
            {
                g.drawImage(this.imgPOI, poi.getCoordX(), poi.getCoordY(),this);
            }
        }

        } catch (IOException e) {e.printStackTrace();}
  
}
}
