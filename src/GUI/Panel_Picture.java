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
    
    private String URL = "";
    
    
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
        if (this.URL != "") {        
        System.out.println(this.URL);
        this.img = ImageIO.read(new File(this.URL));
        this.imgPOI = ImageIO.read(new File("./mark.png"));
        
        g.drawImage(this.img,0,0,this);
        }
 

        
         
       if(pois != null)
        {
            for (Poi poi : pois)
            {
                int x = (int)((poi.getCoordX()/ 100)*this.getWidth());
                int y = (int)((poi.getCoordY()/ 100)*this.getHeight());
                 g.drawImage(this.imgPOI, x, y,this);
            }
        }

        } catch (IOException e) {e.printStackTrace();}
  
}

    /**
     * @return the URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * @param URL the URL to set
     */
    public void setURL(String URL) {
        this.URL = URL;
    }
}
