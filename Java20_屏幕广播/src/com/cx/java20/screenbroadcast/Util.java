package com.cx.java20.screenbroadcast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * 工具类
 *
 */
public class Util {
	

	
	// int -> byte[]
	public static byte[] long2Bytes(long i) {
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (i >> 56);
		bytes[1] = (byte) (i >> 48);
		bytes[2] = (byte) (i >> 40);
		bytes[3] = (byte) (i >> 32);
		bytes[4] = (byte) (i >> 24);
		bytes[5] = (byte) (i >> 16);
		bytes[6] = (byte) (i >> 8);
		bytes[7] = (byte) (i >> 0);
		return bytes;
	}

	// byte[] -> int
	public static long bytes2long(byte[] bytes) {
		long i0 = (bytes[0] & 0xffL) << 56;
		long i1 = (bytes[1] & 0xffL) << 48;
		long i2 = (bytes[2] & 0xffL) << 40;
		long i3 = (bytes[3] & 0xffL) << 32;
		long i4 = (bytes[4] & 0xffL) << 24;
		long i5 = (bytes[5] & 0xffL) << 16;
		long i6 = (bytes[6] & 0xffL) << 8;
		long i7 = (bytes[7] & 0xffL) << 0;
		return i0 | i1 | i2 | i3 | i4 | i5 | i6| i7;
	}
	
	
	
	// int -> byte[]
		public static byte[] int2Bytes(int i) {
			byte[] bytes = new byte[4];
			bytes[0] = (byte) (i >> 24);
			bytes[1] = (byte) (i >> 16);
			bytes[2] = (byte) (i >> 8);
			bytes[3] = (byte) (i >> 0);
			return bytes;
		}

		// byte[] -> int
		public static int bytes2Int(byte[] bytes) {
			int i0 = (bytes[0] & 0xff) << 24;
			int i1 = (bytes[1] & 0xff) << 16;
			int i2 = (bytes[2] & 0xff) << 8;
			int i3 = (bytes[3] & 0xff) << 0;
			return i0 | i1 | i2 | i3;
		}
		// byte[] -> int
		public static int bytes2Int(byte[] bytes,int offset) {
			int i0 = (bytes[0+offset] & 0xff) << 24;
			int i1 = (bytes[1+offset] & 0xff) << 16;
			int i2 = (bytes[2+offset] & 0xff) << 8;
			int i3 = (bytes[3+offset] & 0xff) << 0;
			return i0 | i1 | i2 | i3;
		}

	/**
	 * 将src对象串行化为byte[]
	 */
	public static byte[] serialize(Serializable src) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 将byte[]反串行化为src对象
	 */
	public static Serializable deserialize(byte[] bytes) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable)ois.readObject();
			ois.close();
			bais.close();
			return copy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
