package com.kaltura.core.ip2location;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Ip2LocationRecord {
	
	private Integer ip = 0;
	private String countryShort = null;
	private String countryLong = null;
	private String region = null;
	private String city = null;
	private String isp = null;
	private Float latitude = null;
	private Float longitude = null;
	private String domain = null;
	private String zipcode = null;
	private String timezone = null;
	private String netspeed = null;
	private String iddCode = null;
	private String areaCode = null;
	private String weatherCode = null;
	private String weatherName = null;
	private String mcc = null;
	private String mnc = null;
	private String mobileBrand = null;
	private String elevation = null;
	private String usageType = null;
	
	private int[] _COUNTRY_POSITION             = new int[]{0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
	private int[] _REGION_POSITION              = new int[]{0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
	private int[] _CITY_POSITION                = new int[]{0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
	private int[] _ISP_POSITION                 = new int[]{0, 0, 3, 0, 5, 0, 7, 5, 7, 0, 8, 0, 9, 0, 9, 0, 9, 0, 9, 7, 9, 0, 9, 7, 9};
	private int[] _LATITUDE_POSITION            = new int[]{0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
	private int[] _LONGITUDE_POSITION           = new int[]{0, 0, 0, 0, 0, 6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
	private int[] _DOMAIN_POSITION              = new int[]{0, 0, 0, 0, 0, 0, 0, 6, 8, 0, 9, 0, 10,0, 10, 0, 10, 0, 10, 8, 10, 0, 10, 8, 10};
	private int[] _ZIPCODE_POSITION             = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 7, 7, 7, 0, 7, 0, 7, 7, 7, 0, 7};
	private int[] _TIMEZONE_POSITION            = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 7, 8, 8, 8, 7, 8, 0, 8, 8, 8, 0, 8};
	private int[] _NETSPEED_POSITION            = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 11,0, 11,8, 11, 0, 11, 0, 11, 0, 11};
	private int[] _IDDCODE_POSITION             = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 12, 0, 12, 0, 12, 9, 12, 0, 12};
	private int[] _AREACODE_POSITION            = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10 ,13 ,0, 13, 0, 13, 10, 13, 0, 13};
	private int[] _WEATHERSTATIONCODE_POSITION  = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 14, 0, 14, 0, 14, 0, 14};
	private int[] _WEATHERSTATIONNAME_POSITION  = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 15, 0, 15, 0, 15, 0, 15};
	private int[] _MCC_POSITION                 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 16, 0, 16, 9, 16};
	private int[] _MNC_POSITION                 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10,17, 0, 17, 10, 17};
	private int[] _MOBILEBRAND_POSITION         = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11,18, 0, 18, 11, 18};
	private int[] _ELEVATION_POSITION           = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 19, 0, 19};
	private int[] _USAGETYPE_POSITION           = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 20};

	public Ip2LocationRecord(RandomAccessFile f, DbAttribtues dbAttr, long mid) throws IOException {
		int column = (int)dbAttr.getColumn();
		long baseaddr = dbAttr.getAddr();
		int dbType = dbAttr.getType();

		f.seek(baseaddr + (mid) * column * 4);
		this.ip = f.readInt();

        if (_COUNTRY_POSITION[dbType] != 0) {
            this.countryShort = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _COUNTRY_POSITION, mid)) + 1);
            this.countryLong =  Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _COUNTRY_POSITION, mid)) + 4);
        }

        if (_REGION_POSITION[dbType] != 0)
            this.region = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _REGION_POSITION, mid)) + 1);
        if (_CITY_POSITION[dbType] != 0)
            this.city = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _CITY_POSITION, mid)) + 1);
        if (_ISP_POSITION[dbType] != 0)
            this.isp = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _ISP_POSITION, mid)) + 1);

        if (_LATITUDE_POSITION[dbType] != 0)
            this.latitude = Utils.readFloat(f, calcOffset(dbAttr, _LATITUDE_POSITION, mid));
        if (_LONGITUDE_POSITION[dbType] != 0)
            this.longitude = Utils.readFloat(f, calcOffset(dbAttr, _LONGITUDE_POSITION, mid));

        if (_DOMAIN_POSITION[dbType] != 0)
            this.domain = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _DOMAIN_POSITION, mid)) + 1);

        if (_ZIPCODE_POSITION[dbType] != 0)
            this.zipcode = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _ZIPCODE_POSITION, mid)) + 1);

        if (_TIMEZONE_POSITION[dbType] != 0)
            this.timezone = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _TIMEZONE_POSITION, mid)) + 1);
                
        if (_NETSPEED_POSITION[dbType] != 0)
            this.netspeed = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _NETSPEED_POSITION, mid)) + 1);

        if (_IDDCODE_POSITION[dbType] != 0)
            this.iddCode = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _IDDCODE_POSITION, mid)) + 1);

        if (_AREACODE_POSITION[dbType] != 0)
            this.areaCode = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _AREACODE_POSITION, mid)) + 1);

        if (_WEATHERSTATIONCODE_POSITION[dbType] != 0)
            this.weatherCode = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _WEATHERSTATIONCODE_POSITION, mid)) + 1);

        if (_WEATHERSTATIONNAME_POSITION[dbType] != 0)
            this.weatherName = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _WEATHERSTATIONNAME_POSITION, mid)) + 1);

        if (_MCC_POSITION[dbType] != 0)
            this.mcc = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _MCC_POSITION, mid)) + 1);

        if (_MNC_POSITION[dbType] != 0)
            this.mnc = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _MNC_POSITION, mid)) + 1);

        if (_MOBILEBRAND_POSITION[dbType] != 0)
            this.mobileBrand = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _MOBILEBRAND_POSITION, mid)) + 1);
                
        if (_ELEVATION_POSITION[dbType] != 0)
            this.elevation = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _ELEVATION_POSITION, mid)) + 1);

        if (_USAGETYPE_POSITION[dbType] != 0)
            this.usageType = Utils.readString(f, Utils.readUInteger(f, calcOffset(dbAttr, _USAGETYPE_POSITION, mid)) + 1);

	}
    
    public long calcOffset(DbAttribtues dbAttr, int[] what, long mid) {
		int column = (int)dbAttr.getColumn();
		int dbType = dbAttr.getType();
		return dbAttr.getAddr() + mid * (column * 4) + 4 * (what[dbType]-1);
	}

	public Integer getIp() {
		return ip;
	}

	public String getCountryShort() {
		return countryShort;
	}

	public String getCountryLong() {
		return countryLong;
	}

	public String getRegion() {
		return region;
	}

	public String getCity() {
		return city;
	}

	public String getIsp() {
		return isp;
	}

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public String getDomain() {
		return domain;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getNetspeed() {
		return netspeed;
	}

	public String getIddCode() {
		return iddCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getWeatherCode() {
		return weatherCode;
	}

	public String getWeatherName() {
		return weatherName;
	}

	public String getMcc() {
		return mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public String getMobileBrand() {
		return mobileBrand;
	}

	public String getElevation() {
		return elevation;
	}

	public String getUsageType() {
		return usageType;
	}

}
