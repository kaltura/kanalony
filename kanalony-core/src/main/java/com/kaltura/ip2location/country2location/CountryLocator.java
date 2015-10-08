package kaltura.country2location;

import com.kaltura.ip2location.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryLocator {
	
	private static Logger LOG = LoggerFactory.getLogger(CountryLocator.class);
	private static final String COUNTRIES_FILE_NAME = "/countriesCoordinates.txt";
	
	private final String countryPattern = "[\\w, \\-\\.\\(\\)\\']+";
	private Pattern cityFormat = Pattern.compile("\"(" + countryPattern + ")\",(\\-?[\\d\\.]+),(\\-?[\\d\\.]+)");
	
	
	/**
	 * There aren't many countries around the world, so loading it to memory shouldn't be a problem.
	 */
	private Map<String,Coordinate> countryToCoord = new HashMap<String,Coordinate>();
	
	public CountryLocator() {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = getClass().getResourceAsStream(COUNTRIES_FILE_NAME);
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				
				Matcher m = cityFormat.matcher(line);
				if (!m.matches()) 
					throw new RuntimeException("Failed to parse input file " + line);
				
				String country = m.group(1);
			   Coordinate cr = new Coordinate(country, Float.parseFloat(m.group(2)), Float.parseFloat(m.group(3)));
			   countryToCoord.put(country, cr);
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
	
	public Coordinate getCountryCoordinates(String country) {
		if(countryToCoord.containsKey(country)) {
			return countryToCoord.get(country);
		}
		
		return new Coordinate(country, 0, 0);
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		CountryLocator reader = new CountryLocator();
		Coordinate ilCoord = reader.getCountryCoordinates("ISRAEL");
		System.out.println(ilCoord.getName());
		System.out.println(ilCoord.getLatitude());
		System.out.println(ilCoord.getLongitude());
	}
}
