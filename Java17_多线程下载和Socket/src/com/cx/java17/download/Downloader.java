package com.cx.java17.download;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ������
 *
 */
public class Downloader {
	// url
	private String url;
	// location
	private String location;
	// �߳���
	private int count;
	// ui����
	private DownloadUI ui;
	// �ļ���С
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
	 * ��ȡ��������Դ���ļ�����
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
	 * ��ʼ����
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
