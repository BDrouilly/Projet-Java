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
import javax.swing.ImageIcon;


/**
 *
 * @author Alexis
 */
public class Panel_Picture extends JPanel {
    
    private static Image img = null;
    private static Image imgPOI = null; 
    private static Image imgHighlight = null; 
    private static Image imgHighlight2 = null; 

    
    private static ArrayList<Poi> pois = null;
    
    private Poi selectedPoi;
    
    private String URL = "";
    
    private float x, y;
    
    
    public void setPois(ArrayList<Poi> poi)
    {
        this.pois = poi;
    }
    
    public void setMap(Image img)
    {
        this.img = img;
    }
    
    public void setSelected(Poi poi)
    {
        this.selectedPoi = poi;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        try { 
        if (this.URL != "") {        
            System.out.println(this.URL);
            this.img = ImageIO.read(new File(this.URL));
            this.imgPOI = ImageIO.read(new File("./mark.png"));
            this.imgHighlight = ImageIO.read(new File("./mark_tour.png"));
            this.imgHighlight2 = ImageIO.read(new File("./mark_tour.png"));

            g.drawImage(this.img,0,0,this);
        }
 

        
         
       if(pois != null)
        {
            for (Poi poi : pois)
            {
                x = ((poi.getCoordX())*this.getWidth())/100;
                y = ((poi.getCoordY())*this.getHeight())/100;
                //System.out.println("Affichage de coord... " + (float)this.getHeight()+ poi.getCoordX();

                
                g.drawImage(this.imgPOI, (int)x-15, (int)y-30, 30, 30, this);
                System.out.println("Affichage de POI... x="+x+"///y="+y+"////name="+poi.getName());
                
                if(selectedPoi.getId() == poi.getId())
                {
                    g.drawImage(this.imgHighlight, (int)x-15, (int)y-15, 30, 30, this);
                }
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
