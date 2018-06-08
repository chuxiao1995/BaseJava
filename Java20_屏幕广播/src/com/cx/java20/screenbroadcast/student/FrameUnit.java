package com.cx.java20.screenbroadcast.student;
/**
 * 
 *
 *帧单元,封装每个block的信息
 */
public class FrameUnit {
	//时间戳
	private long timestamp;
	
	//帧单元编号
	private int index;
	
	private int count;
	private byte[] unitData;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public byte[] getUnitData() {
		return unitData;
	}

	public void setUnitData(byte[] unitData) {
		this.unitData = unitData;
	}

	public FrameUnit(long timestamp,int count, int index, byte[] unitData) {
		this.timestamp = timestamp;
		this.index = index;
		this.unitData = unitData;
		this.count =count;
	}
	
	
	

}
