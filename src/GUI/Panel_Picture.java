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
           // this.img = ImageIO.read(new File("./Saint_Pierre_De_Manneville.jpg"));
           // System.out.println("ça marche ?");
        //if (URL != null) {
            System.out.println(this.URL);
        this.img = ImageIO.read(new File(this.URL));
        g.drawImage(this.img,0,0,this);
        //}
        
      //  else {
     //       URL = "./Saint_Pierre_De_Manneville.jpg"; this.img = ImageIO.read(new File(this.URL));}
       // this.imgPOI = ImageIO.read(new File("./addmark.PNG")); 
        
        
            
        if(pois != null)
        {
            for (Poi poi : pois)
            {
                g.drawImage(this.imgPOI, poi.getCoordX(), poi.getCoordY(),this);
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
