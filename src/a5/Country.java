package a5;

import java.util.ArrayList;

/**
 * A country in the world
 * @author Jason Huels & Cara Tang
 */
public class Country {
	private int id;
	private String name;
	private long population;
	private double medianAge;
	private ArrayList<String> languages;
	
	/**
	 * Create a Country object with the given properties
	 */
	public Country(int id, String name, long population, double medianAge) {
		this.id = id;
		this.name = name;
		this.population = population;
		this.medianAge = medianAge;
		languages = new ArrayList<>();
	}

	/**
	 * @return country id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return country's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return country's population
	 */
	public long getPopulation() {
		return population;
	}

	/**
	 * @return country's medianAge
	 */
	public double getMedianAge() {
		return medianAge;
	}

	/**
	 * @return country's languages
	 */
	public String getLanguages(){
		String result;
		if(languages.isEmpty()) result = "No language listed.";
		else result = languages.toString();
		return result;
	}

	/**
	 * Adds a language to the languages array
	 */
	public void addLanguage(String language){languages.add(language);}
}
