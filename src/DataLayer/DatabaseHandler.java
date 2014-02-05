/**
 * 
 */
package DataLayer;

import java.sql.*;

/**
 * @author SamuelD
 *
 */
public class DatabaseHandler {
	private String connectionString;
	private String user;
	private String pass;
	private Connection connection;
	
	public DatabaseHandler() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver --- Loaded ---");
			/**
			 * TODO : Add db name
			 */
			this.connectionString = "jdbc:mysql://localhost:3306/minisig";
			this.user = "root";
			this.pass = "";
			
			this.connection = DriverManager.getConnection(this.connectionString, this.user, this.pass);
			
			System.out.println("Connection --- Loaded ---");
		} catch (Exception e) {
			System.out.println("Database --- Fail loading or connecting ---");
			e.printStackTrace();
		}
	}
	/**
	 * return a selected ResultSet of rows
	 * @param query : String
	 * @return result : ResultSet
	 */
	public ResultSet getRows(String query) {
		ResultSet result = null;
		try {
			Statement state = this.connection.createStatement();
			System.out.println("--- Trying to execute Query : " + query);
			result = state.executeQuery(query);
		} catch (Exception e) {
			System.out.println("--- Fail querying --- Query = " + query);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * insert or update a row in a database
	 * @param query : String
	 */
	public void setRows(String query) {
		try {
			Statement state = this.connection.createStatement();
			System.out.println("--- Trying to execute Query : " + query);
			state.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("--- Fail Seting rows --- Query = " + query);
			e.printStackTrace();
		}
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}	
}
