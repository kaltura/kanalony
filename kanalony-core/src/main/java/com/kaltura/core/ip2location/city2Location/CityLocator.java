package com.kaltura.core.ip2location.city2Location;

import com.kaltura.core.ip2location.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityLocator {
	
	private static Logger LOG = LoggerFactory.getLogger(CityLocator.class);
	
	/**
	 * There aren't many countries around the world, so loading it to memory shouldn't be a problem.
	 */
	private Map<String,Map<String, Coordinate>> cityLocation = new HashMap<String,Map<String, Coordinate>>();
	
	private static final String CITIES_FILE_NAME = "/citiesCoordinates.txt";
	
	private final String countryPattern = "[\\w, \\-\\.\\(\\)\\']+";
	private final String cityPattern = "[\\w, \\-\\'\\.\\&\\/\\(\\)]+";
	private Pattern cityFormat = Pattern.compile("\"(" + countryPattern + ")\",\"(" + cityPattern + ")\",(\\-?[\\d\\.]+),(\\-?[\\d\\.]+)");
	
	
	public CityLocator() {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = getClass().getResourceAsStream(CITIES_FILE_NAME);
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				
				Matcher m = cityFormat.matcher(line);
				if (!m.matches()) 
					throw new RuntimeException("Failed to parse input file");
				
				String country = m.group(1);
				String city = m.group(2);
					
			   Coordinate cr = new Coordinate(city, Float.parseFloat(m.group(3)), Float.parseFloat(m.group(4)));
			   
			   if(!cityLocation.containsKey(country))
				   cityLocation.put(country, new HashMap<String, Coordinate>());
			   
			   cityLocation.get(country).put(city, cr);
			}
			br.close();
		} catch (IOException e) {
			LOG.error("Failed to init from file.");
		} finally {
			try {
				if(is != null)
					is.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				LOG.error("Failed to close file.");
			}
		}
	}
	
	public Coordinate getCityCoordinates(String countryCode, String cityName) {
		if(!cityLocation.containsKey(countryCode) || !cityLocation.get(countryCode).containsKey(cityName))
			return new Coordinate(cityName, 0, 0);
		return cityLocation.get(countryCode).get(cityName);
	}
	
	
	public static void main(String[] args) {
		CityLocator reader = new CityLocator();
		Coordinate ilCoord = reader.getCityCoordinates("AFGHANISTAN", "CHARIKAR");
		System.out.println(ilCoord.getName());
		System.out.println(ilCoord.getLatitude());
		System.out.println(ilCoord.getLongitude());
	}
}
