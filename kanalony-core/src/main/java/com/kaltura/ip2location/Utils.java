package com.kaltura.ip2location;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Utils {
	
	
	public static String readString(RandomAccessFile f, long l) throws IOException {
        f.seek(l - 1);
    	int length = (int)f.readUnsignedByte();
    	byte[] strArr = new byte[length];
    	f.read(strArr);
    	return new String(strArr);
    }
	
	public static long readUInteger(RandomAccessFile f) throws IOException {
		byte b1 = f.readByte();
    	byte b2 = f.readByte();
    	byte b3 = f.readByte();
    	byte b4 = f.readByte();
    	int number = ((b4 & 0xff) << 24)
    		     | ((b3 & 0xff) << 16)
    		     | ((b2 & 0xff) << 8)
    		     | ((b1 & 0xff) << 0);

    	return (number & 0x00000000ffffffffL);
	}

    public static long readUInteger(RandomAccessFile f, long offset) throws IOException {
    	f.seek(offset - 1);
    	return readUInteger(f);
    	
    }
    
    public static float readFloat(RandomAccessFile f, long offset) throws IOException {
    	return Float.intBitsToFloat((int) readUInteger(f, offset));
    }
    
}


