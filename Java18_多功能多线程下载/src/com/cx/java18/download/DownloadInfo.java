package com.cx.java18.download;

/**
 * 下载信息类
 * 
 * @author 86152
 *
 */
public class DownloadInfo {
	// 线程索引
	private int index;
	private String url;
	private String location;
	private int startPos;
	private int endPos;
	private int amount;

	public DownloadInfo(int index, String url, String locationString,
			int startPos, int endPos, int amount) {
		this.index = index;
		this.url = url;
		this.location = locationString;
		this.startPos = startPos;
		this.endPos = endPos;
		this.amount = amount;
	}

	public int getIndex() {
		return index;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocationString() {
		return location;
	}

	public void setLocationString(String locationString) {
		this.location = locationString;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

}
