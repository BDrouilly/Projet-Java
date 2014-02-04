/**
 * 
 */
package JobPackage;

import java.sql.ResultSet;

/**
 * @author SamuelD
 *
 */
public class Poi {
	private MappingPoi poiMap;
	private int coordX;
	private int coordY;
	private String name;
	private String label;
	private String text;
	
	public Poi(int id) {
		poiMap = new MappingPoi();
		this.poiBuilder(this.poiMap.getPoiById(id));
	}
	
	private void poiBuilder(ResultSet result) {
		try {
			
		} catch (Exception e) {
			
		}
	}
}
