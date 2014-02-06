package miniSIG;

import java.sql.*;
import GUI.*;
import JobPackage.*;
import java.sql.*;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import GUI.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import DataLayer.*;
import java.awt.image.*;

public class test {

    
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        GUI.Window fen = new GUI.Window();
        fen.setVisible(true);
	/*public static void main(String[] args) {
            MappingPoi mpoi = new MappingPoi();
            ResultSet rs;
            Window fen = new Window();
            fen.setVisible(true);
            mpoi.SearchPOI("le rez", "cate", 1);
	}*/
        
        
        
        
        
       /* Uber technique pour chopper des images depuis la BDD !!!!
         ResultSet rs;
        InputStream in;
        DatabaseHandler dbh = new DatabaseHandler();
        
        rs = dbh.getRows("select * from tb_map;");
        try{
        if(rs.next())
        {
                 byte[] imageB = null;

                     imageB = rs.getBytes("MAP_PICTURE");
        
        Image img = Toolkit.getDefaultToolkit().createImage(imageB);
        Panel_Picture.setMap(img);*/

}
}
