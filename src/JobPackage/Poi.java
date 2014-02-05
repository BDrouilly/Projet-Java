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
	private String link;
	private int mapId;
	/**
	 * instanciate a Poi and initialize it with given values
	 * @param name : String
	 * @param label : String
	 * @param text : String
	 * @param link : String
	 * @param coordX : int
	 * @param coordY : int
	 * @param mapId : int
	 */
	public Poi(String name, String label, String text, String link, int coordX, int coordY, int mapId){
		this.poiMap = new MappingPoi();
		this.name = name;
		this.label = label;
		this.text = text;
		this.link = link;
		this.coordX = coordX;
		this.coordY = coordY;
		this.mapId = mapId;
	}
	/**
	 * instanciate a Poi with the database parameters of the given id
	 * @param id : int
	 */
	public Poi(int id) {
		this.poiMap = new MappingPoi();
		this.poiBuilder(this.poiMap.getPoiById(id));
	}
	/**
	 * instanciate a void Poi
	 */
	public Poi() {
		this.poiMap = new MappingPoi();
		this.name ="";
		this.coordX = 0;
		this.coordY = 0;
		this.label = "";
		this.text = "";
		this.link = "";
		this.mapId = 0;
	}
	/**
	 * initialize a Poi with database results from mapping object
	 * @param result : ResultSet
	 */
	private void poiBuilder(ResultSet result) {
		try {
			result.next();
			this.name = result.getString("POI_NAME");
			this.coordX = result.getInt("POI_X");
			this.coordY = result.getInt("POI_Y");
			this.label = result.getString("POI_LABEL");
			this.text = result.getString("POI_DESCRIPTION");
			this.link = result.getString("POI_LINK");
			this.mapId = result.getInt("MAP_ID");
		} catch (Exception e) {
			System.out.println("--- ERROR CREATING POI ---");
			e.printStackTrace();
		}
	}
	public void addThisToDb(){
		this.poiMap.setNew(this.name, this.coordX, this.coordY, this.label, this.text, this.link, this.mapId);
	}

	/**
	 * @return the coordX
	 */
	public int getCoordX() {
		return coordX;
	}

	/**
	 * @param coordX the coordX to set
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	/**
	 * @return the coordY
	 */
	public int getCoordY() {
		return coordY;
	}

	/**
	 * @param coordY the coordY to set
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the mapId
	 */
	public int getMapId() {
		return mapId;
	}
	/**
	 * @param mapId the mapId to set
	 */
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
}
