/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JobPackage;

import java.sql.*;

import DataLayer.DatabaseHandler;

/**
 *
 * @author Alexis
 */
public class MappingMap {
    
    private String query;
    private DatabaseHandler dh;
    
    public MappingMap() {
        this.dh = new DatabaseHandler();
    }
    
    public ResultSet getMap() { // charge l'url de la map
        
         this.query = "SELECT * FROM TB_Map";
         return dh.getRows(this.query);
         
    }
    
    
    public ResultSet getMapById(int ID) { // charge l'url de la map
        
         this.query = "SELECT * FROM TB_Map WHERE Map_ID =" + ID;
         return dh.getRows(this.query);
         
    }
    
    
}
