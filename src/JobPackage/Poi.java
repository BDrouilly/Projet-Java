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
	
	public Poi(String name, String label, String text, String link, int coordX, int coordY){
		this.poiMap = new MappingPoi();
		this.name = name;
		this.label = label;
		this.text = text;
		this.link = link;
		this.coordX = coordX;
		this.coordY = coordY;
	}
	public Poi(int id) {
		this.poiMap = new MappingPoi();
		this.poiBuilder(this.poiMap.getPoiById(id));
	}
	public Poi() {
		this.poiMap = new MappingPoi();
		this.name ="";
		this.coordX = 0;
		this.coordY = 0;
		this.label = "";
		this.text = "";
		this.link = "";
	}
	
	private void poiBuilder(ResultSet result) {
		try {
			result.next();
			this.name = result.getString("POI_NAME");
			this.coordX = result.getInt("POI_X");
			this.coordY = result.getInt("POI_Y");
			this.label = result.getString("POI_LABEL");
			this.text = result.getString("POI_DESCRIPTION");
			this.link = result.getString("POI_LINK");
		} catch (Exception e) {
			System.out.println("--- ERROR CREATING POI ---");
			e.printStackTrace();
		}
	}
	public void addThisToDb(){
		this.poiMap.
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
}
