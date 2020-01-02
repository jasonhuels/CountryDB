package a5;

import java.util.List;

/**
 * Read from the Country database and print data on the countries
 * @author Jason Huels
 */
public class Main {
	public static void main(String[] args){
		CountryDB cdb = new CountryDB();
		List<Country> countries = cdb.getCountries();

		for(Country con : countries){
			System.out.println("Name: " + con.getName()
					+ "  Population: " + con.getPopulation()
					+ "  Median Age: " + con.getMedianAge() + " Languages: " + con.getLanguages());
		}
	}
}
