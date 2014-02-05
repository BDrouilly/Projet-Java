/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JobPackage;
import java.sql.*;
/**
 *
 * @author Alexis
 */
public class Map {
    
    private int ID;
    private String Name;
    private String URL;
    private MappingMap mapping;
    
    public Map(int ID) {
        this.ID = ID;
        mapping = new MappingMap();
        ResultSet result = mapping.getMapById(this.ID);
        try { // RECUPERE LE NOM ET L'URL
        result.next();
        this.Name = result.getString("Map_Name");
        this.URL = result.getString("Map_Picture");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }    
    public String getURL(){
        
        return this.URL;
    }
            
}
