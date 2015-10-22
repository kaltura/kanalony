package com.kaltura.core.ip2location;
import java.io.IOException;
import java.io.RandomAccessFile;


public class DbAttribtues {
	
	protected final int type;
	protected final int column;
	protected final int year;
	protected final int month;
	protected final int day;
	protected final long count;
	protected final long addr;
	protected final long ipVersion;
	
	public DbAttribtues(RandomAccessFile f) throws IOException {
		this.type = f.readUnsignedByte();
		this.column = f.readUnsignedByte();
		this.year = f.readUnsignedByte();
		this.month = f.readUnsignedByte();
		this.day = f.readUnsignedByte();
		this.count = Utils.readUInteger(f);
		this.addr =  Utils.readUInteger(f);
		this.ipVersion =  Utils.readUInteger(f);
	}

	public int getType() {
		return type;
	}

	public int getColumn() {
		return column;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public long getCount() {
		return count;
	}

	public long getAddr() {
		return addr;
	}

	public long getIpVersion() {
		return ipVersion;
	}

}
