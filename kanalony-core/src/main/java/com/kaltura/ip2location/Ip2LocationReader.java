package com.kaltura.ip2location;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.UnknownHostException;


public class Ip2LocationReader {
	
	private DbAttribtues dbAttr = null;
	private RandomAccessFile file = null;
	private boolean isLocked = false;

	public void init(String fileName) throws IOException {
		file = new RandomAccessFile(fileName, "r");
		this.dbAttr = new DbAttribtues(file);
		lock();
	}
	
	public void unlock() {
		this.isLocked = false;
	}
	
	public boolean isLocked()
	{
		return isLocked;
	}
	
	public void lock()
	{
		isLocked = true;
	}
	
	public void close() throws IOException {
		if(file != null)
			file.close();
	}
	
	public Ip2LocationRecord getAll(String ip) throws IOException {
		return getRecord(ip);
	}
	
	public Ip2LocationRecord find(String ip) throws IOException {
		return getRecord(ip);
	}

	public Ip2LocationRecord getRecord(String ip) throws IOException {
        long baseaddr = this.dbAttr.getAddr();
        long low = 0;
        long high = this.dbAttr.getCount();
        int column = this.dbAttr.getColumn();

        long ipno = ipv4ToNumber(ip);

        while(low <= high) {
            long mid = ((low + high) / 2);
           
            long ipfrom = Utils.readUInteger(file, baseaddr + (mid) * (column * 4));
            long ipto = Utils.readUInteger(file, baseaddr + (mid + 1) * (column * 4));

            if((ipfrom <= ipno) && (ipno < ipto)) {
                return new Ip2LocationRecord(this.file, this.dbAttr, mid);
            } else {
                if(ipno < ipfrom)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
        }
		return null;
	}
	
	public static long ipv4ToNumber (String ipaddr) throws UnknownHostException {
		
		long long_ip = 0;
		String[] ipAddressInArray = ipaddr.split("\\.");
	 
		for (int i = 3; i >= 0; i--) {
	 
			long ip = Long.parseLong(ipAddressInArray[3 - i]);
			long_ip |= ip << (i * 8);
	 
		}
		if (long_ip < 0) {
			long_ip += Math.pow(2, 32);
		}
		return long_ip;
	}
	
}
