package JobPackage;

import java.sql.ResultSet;

public class Route {
	private int id;
	private String date;
	private String label;
	private String text;
	private MappingRoute mapRoute;
	
	public Route(){
		this.date = "";
		this.label = "";
		this.text = "";
	}
	public Route(int id){
		this.mapRoute = new MappingRoute();
		this.routeBuilder(id);
	}
	public Route(String date, String label, String description){
		this.date = date;
		this.label = label;
		this.text = description;
	}
	
	private void routeBuilder(int id){
		ResultSet result = this.mapRoute.getRouteById(id);
		try {
			this.id = result.getInt("ROUTE_ID");
			this.date = result.getString("ROUTE_DATE");
			this.label = result.getString("ROUTE_LABEL");
			this.text = result.getString("ROUTE_DESCRIPTION");
		} catch(Exception e) {
			System.out.println("--- Error creating a Route ---");
			e.printStackTrace();
		}
	}
	public void addPoi(int id){
		if(this.mapRoute instanceof MappingRoute ){
			mapRoute.addPoiToRoute(this.id, id);
		} else {
			mapRoute = new MappingRoute();
			mapRoute.addPoiToRoute(this.id, id);
		}
	}
	public void addThisToDb(){
		this.mapRoute.setNewRoute(this.text, this.label);
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
}
