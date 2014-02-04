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
public class Map {
    
    private String query;
    private DatabaseHandler dh;
    
    public Map() {
        this.dh = new DatabaseHandler();
    }
    
    public ResultSet LoadMap(int ID) {
        
         this.query = "SELECT Map_Picture FROM TB_Map WHERE Map_ID =" + ID; // recupère l'ID selectionner
         return dh.getRows(this.query);
         
    }
    
}
