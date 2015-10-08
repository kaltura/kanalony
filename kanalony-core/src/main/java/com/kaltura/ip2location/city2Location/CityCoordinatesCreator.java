package com.kaltura.ip2location.city2Location;

import com.kaltura.ip2location.DbAttribtues;
import com.kaltura.ip2location.Ip2LocationRecord;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CityCoordinatesCreator {
	
	private RandomAccessFile file = null;
	private DbAttribtues dbAttr = null;
	
	public void init(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "r");
		this.dbAttr = new DbAttribtues(file);
	}
	
	public void close() throws IOException {
		if(file != null)
			file.close();
	}
	
	protected Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	
	public void writeFile() throws IOException {
	     initMap();
	}

	private void initMap() throws IOException {
		
		long limit = this.dbAttr.getCount();
	     for(long cur = 0 ; cur < limit ; cur++) {
	    	 Ip2LocationRecord record = new Ip2LocationRecord(this.file, this.dbAttr, cur);
	    	 String country = record.getCountryLong();
	    	 String city = record.getCity();
	    	 
	    	 if((city == null) || (country == null)) {
	    		 continue;
	    	 }
	    	 
	    	 if(!map.containsKey(country)) {
	    		 map.put(country, new HashSet<String>());
	    	 }
	    	 
	    	 if(map.get(country).contains(city)) {
    			 continue;
	    	 } else {
				System.out.println("\"" + country + "\",\"" + city + "\"," + String.format("%.4f", record.getLatitude()) + "," +
						String.format("%.4f", record.getLongitude()) );
				map.get(country).add(city);
			}
    			 
	     }
	}
	
	/**
	 * This scripts takes IP2Location file and prints for each city & country its longitude & latitude 
	 * @param args IP2Location file path
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if(args.length != 1) {
			System.out.println("Wrong number of params");
			System.exit(-1);
		}
		
		CityCoordinatesCreator creator = new CityCoordinatesCreator();
		creator.init(args[0]);
		creator.writeFile();
		creator.close();
	}
}
