/**
 * 
 */
package JobPackage;

import DataLayer.DatabaseHandler;
import java.sql.ResultSet;
import java.util.Date;

/**
 * @author SamuelD
 *
 */
public class MappingRoute {
	private DatabaseHandler db;
	private String query;
	
	public MappingRoute(){
		this.db = new DatabaseHandler();
	}
	
	public ResultSet getRouteById(int id){
		this.query = "SELECT * FROM TB_ROUTE WHERE ROUTE_ID = " + id;
		return this.db.getRows(this.query);
	}
	public ResultSet getRouteByMapId(int mapId){
		this.query = "SELECT * FROM TB_ROUTE, APPARTENIR, TB_POI WHERE TB_ROUTE.ROUTE_ID = APPARTENIR.ROUTE_ID " +
				"AND APPARTENIR.POI_ID = TB_POI.POI_ID AND TB_POI.MAP_ID = " + mapId;
		return this.db.getRows(this.query);
	}
	public ResultSet getRoute(){
		this.query = "SELECT * FROM TB_ROUTE";
		return this.db.getRows(this.query);
	}
	public ResultSet getRouteByName(String name){
		this.query = "SELECT * FROM TB_ROUTE WHERE ROUTE_LABEL = '" + name + "'";
		return this.db.getRows(this.query);
	}
	public void setNewRoute(String text, String label){
		this.query = "INSERT INTO TB_ROUTE(ROUTE_DATE, ROUTE_LABEL, ROUTE_DESCRIPTION) VALUES('" + new Date(System.currentTimeMillis()) +"', '"+ label +"', '"+ text +"')";
	}
	public void addPoiToRoute(int routeId, int poiId){
		this.query ="INSERT INTO APPARTENIR(ROUTE_ID, POI_ID) VALUES(" + routeId + ", " + poiId + ")";
	}
}
