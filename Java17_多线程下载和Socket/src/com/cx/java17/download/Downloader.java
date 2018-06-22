package com.cx.java17.download;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载器
 *
 */
public class Downloader {
	// url
	private String url;
	// location
	private String location;
	// 线程数
	private int count;
	// ui窗口
	private DownloadUI ui;
	// 文件大小
	private int length;

	public Downloader(String url, String location, int count, DownloadUI ui) {
		this.url = url;
		this.location = location;
		this.count = count;
		this.ui = ui;
		this.length = calcFileLength();
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * 获取服务器资源的文件长度
	 */
	private int calcFileLength() {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			return conn.getContentLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 开始下载
	 */
	public void startDownload() {
		int blockSize = length / count;
		int startPos = 0;
		int endPos = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				startPos = i * blockSize;
				if (i == (count - 1)) {
					endPos = length - 1;
				} else {
					endPos = (i + 1) * blockSize - 1;
				}
			}
			new DownloadThread(url, location, startPos, endPos, ui).start();
		}
	}

}
