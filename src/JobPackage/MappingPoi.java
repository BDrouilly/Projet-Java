/**
 * TODO Add body to methods and table/fields name to queries
 */
package JobPackage;

import java.sql.*;

import DataLayer.DatabaseHandler;

/**
 * @author SamuelD
 * Mapping class between Poi and database
 */
public class MappingPoi {
	private String query;
	private DatabaseHandler db;
	
	public MappingPoi(){
		this.db = new DatabaseHandler();
	}
	public ResultSet getPoi() {
		this.query = "SELECT * FROM TB_POI";
		return db.getRows(this.query);
	}
	/**
	 * Returns rows for given id
	 * @param id
	 * @return results : ResultSet
	 */
	public ResultSet getPoiById(int id){
		this.query = "SELECT * FROM TB_POI WHERE POI_ID = " + id;
		return db.getRows(this.query);
	}
	/**
	 * return rows for given map id
	 * @param mapId : int
	 * @return results : ResultSet
	 */
	public ResultSet getPoiByMapId(int mapId){
		this.query = "SELECT * FROM TB_POI WHERE MAP_ID =" + mapId;
		return db.getRows(this.query);
	}
        
        public ResultSet getPoiByMapName(String mapName){
		this.query = "SELECT * FROM TB_POI WHERE MAP_ID =" + mapName;
		return db.getRows(this.query);
	}
        
	/**
	 * return rows for given coordinates
	 * @param x : int
	 * @param y : int 
	 * @return results : ResultSet
	 */
	public ResultSet getPoiByCoord(int x, int y){
		this.query = "SELECT * FROM TB_POI WHERE (POI_X = " + x + " AND POI_Y = " + y + ")";
		return db.getRows(this.query);
	}
	/**
	 * return rows for given horizontal coordinate
	 * @param x : int
	 * @return results : Resultset
	 */
	public ResultSet getPoiByX(int x){
		this.query = "SELECT * FROM TB_POI WHERE POI_X = " + x;
		return db.getRows(this.query);
	}
	/**
	 * return rows for given vertical coordinate
	 * @param y : int
	 * @return results : ResultSet
	 */
	public ResultSet getPoiByY(int y){
		this.query = "SELECT * FROM TB_POI WHERE POI_Y = " + y;
		return db.getRows(this.query);
	}
	/**
	 * return rows for given POI name
	 * @param name : String
	 * @return results : ResultSet
	 */
	public ResultSet getPoiByName(String name){
		this.query = "SELECT * FROM TB_POI WHERE POI_NAME = " + name;
		return db.getRows(this.query);
	}
	/**
	 * return rows for given POI label
	 * @param label : String
	 * @return results : ResultSet
	 */
	public ResultSet getPoiByLabel(String label){
		this.query = "SELECT * FROM TB_POI WHERE POI_LABEL = " + label;
		return db.getRows(this.query);		
	}        
	public ResultSet getPoiByRoute(int routeId){
		this.query = "SELECT * FROM APPARTENIR, TB_POI WHERE TB_POI.POI_ID = APPARTENIR.POI_ID AND ROUTE_ID = " + routeId;
		return db.getRows(this.query);	
	}
    public ResultSet SearchPOI(String search, String categorie, int mapID) {//fonction de recherche

        int i = 0; //compteur
        
        String sql = "SELECT POI_ID, POI_NAME FROM TB_POI INNER JOIN TB_MAP ON TB_POI.MAP_ID = TB_MAP.MAP_ID ";
        
        if (mapID > 0)
            sql += "WHERE MAP_ID=" + mapID + " AND ";
        else
            sql += "WHERE ";
                    
        sql += "POI_CATEGORIE=%" + categorie + "%" +  
                " AND ("; //debut de requete sql
        String mots[] = search.split(" ");//On separe la chaine en mots cles
        for (String mot : mots) {//pour chaque mot
            sql += " POI_NAME LIKE %" + mot + "% OR POI_DESCRIPTION LIKE %" + mot + "% OR"; //on ajoute la recherche Ã  la requete SQL en crÃ©ant des param avec le compteur
        }
        sql += " 1=0 )"; //on ajoute la fin de la requete, avec une condition or toujours fausse et une limite au nb de resultats

        System.out.println("requete de recherche utilisée:"+sql);
        return db.getRows(sql);//on execute la requete
  
    }  
	/**
	 * add a new POI into the database
	 * @param name : String
	 * @param x : int
	 * @param y : int
	 */
	public void setNew(String name, float x, float y, int mapId){
		this.query ="INSERT INTO TB_POI(MAP_ID, POI_DATE, POI_NAME, POI_X, POI_Y) " +
				"VALUES(" + mapId + ", '" + new Date(System.currentTimeMillis()).toString() + "', '" + name + "', " + x + ", " + y + ")";
		this.db.setRows(query);
	}
	/**
	 * Add a new POI into the database
	 * @param name : String
	 * @param x : int
	 * @param y : int
	 * @param label : String
	 * @param text : String
	 * @param link : String
	 * @param mapId : int
	 */
	public void setNew(String name, float x, float y, String label, String text, String link, int mapId){
		this.query ="INSERT INTO TB_POI(MAP_ID, POI_DATE, POI_NAME, POI_X, POI_Y, POI_LABEL, POI_DESCRIPTION, POI_LINK) " +
				"VALUES(" + mapId + ", '" + new Date(System.currentTimeMillis()).toString() + "', '" + name + "', " + x + ", " + y + ", '" + label + "', '" + text + "', '" + link + "')";
		this.db.setRows(query);
	}
	public void setNewPoiPicture(int poiId, String pictureUrl){
		this.query = "";
	}
	public void updateFullPoi(String name, int x, int y, String label, String text, int id){
		this.query = "UPDATE TB_POI SET name=" + name +",x="+ x +"y="+ y + ",label=" + label +  ",text=" + text + " WHERE POI_ID=" + id + ";";
                this.db.setRows(query);
        }
        public void deletePoi(int poiId){
            this.query = "DELETE FROM APPARTENIR WHERE POI_ID=" + poiId;
            this.db.setRows(query);
            this.query = "DELETE FROM TB_POI WHERE POI_ID=" + poiId;
            this.db.setRows(query);
	}
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @param db the db to set
	 */
	public void setDb(DatabaseHandler db) {
		this.db = db;
	}
}
