package a5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read data from the Countries database
 * @author Jason Huels & Cara Tang
 */
public class CountryDB {
	private static final String DB_NAME = "Countries";
	private static final String DB_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/" + DB_NAME;
	private static final String USERNAME = "233jstudent";
	private static final String PASSWORD = "tnedutsj332";
	private static final String GET_COUNTRIES_SQL = "SELECT * FROM COUNTRY";
	private static final String GET_COUNTRY_LANGS_SQL = "SELECT * FROM COUNTRY_LANGUAGE WHERE CountryId = ?";

	private ArrayList<Country> countries;

	/**
	 * Create a CountryDB object
	 * Read from the Countries database and populate the countries list
	 */
	public CountryDB() {
		countries = readCountries();
	}

	/**
	 * Create and return a connection to the database
	 * @return connection to the countries database
	 */
	private Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return connection;
	}

	/**
	 * Read country info from the Country table.
	 * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
	 * @return the list of countries read
	 */
	private ArrayList<Country> readCountries() {
		ArrayList<Country> countries = new ArrayList<>();
		try (
				Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_COUNTRIES_SQL);
				ResultSet rs = stmt.executeQuery()
				) {
			while (rs.next()) {
				countries.add(new Country(rs.getInt("Id"),
						rs.getString("Name"),
						rs.getLong("Population"),
						rs.getDouble("MedianAge")));
			}
		}
		catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		return countries;
	}

	/**
	 * @return list of countries read from the country database
	 */
	public ArrayList<Country> getCountries(){
		readLanguages();
		return countries;
	}

	/**
	 *  Read the Languages of the countries from the database
	 */
	private void readLanguages() {
		try (
				Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_COUNTRY_LANGS_SQL)
		) {
			for(Country con : countries) {
				stmt.setInt(1, con.getId());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					con.addLanguage(rs.getString("Language"));
				}
				rs.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
