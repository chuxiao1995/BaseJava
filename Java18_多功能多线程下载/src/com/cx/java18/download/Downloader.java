package com.cx.java18.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	// �����߳���Ϣ����
	private List<DownloadInfo> downloadInfos;

	// �ڴ��е�������Ϣ
	public Properties prop;

	public List<DownloadInfo> getDownloadInfos() {
		return downloadInfos;
	}

	public void setDownloadInfos(List<DownloadInfo> downloadInfos) {
		this.downloadInfos = downloadInfos;
	}

	/**
	 * 
	 * ������
	 */
	public Downloader(String url, String location, int count, DownloadUI ui) {
		this.url = url;
		this.location = location;
		this.count = count;
		this.ui = ui;
		// ��ʼ�������߳���Ϣ
		initDownloadInfos();
	}

	/**
	 * ��ʼ�������߳���Ϣ����
	 * 
	 * @throws IOException
	 */
	private void initDownloadInfos() {
		// 1.��������
		downloadInfos = new ArrayList<DownloadInfo>();
		// �ж����´���������
		if (!isFirstDownload()) {
			// 2.�����ļ���С
			length = calcFileLength();
			// 3.����ÿ���߳̿��С
			int blockSize = length / count;
			int startPos;
			int endPos;
			// 4.ѭ������������Ϣ
			for (int i = 0; i < count; i++) {
				startPos = i * blockSize;

				if (i == (count - 1)) {
					endPos = length - 1;
				} else {
					endPos = (i + 1) * blockSize - 1;
				}
				DownloadInfo di = new DownloadInfo(i, url, location, startPos,
						endPos, 0);
				downloadInfos.add(di);
			}
			// 5.����������ϢԪ�����ļ�
			createMetaFile(downloadInfos);
		}
		// ����
		else {
			try {
				FileInputStream fis = new FileInputStream(location + ".meta");
				prop = new Properties();
				prop.load(fis);
				// �߳���
				int count = Integer.parseInt(prop.getProperty("thread.count"));
				for (int i = 0; i < count; i++) {
					int startPos = Integer.parseInt(prop.getProperty("thread."
							+ i + ".startPos"));
					int endPos = Integer.parseInt(prop.getProperty("thread."
							+ i + ".endPos"));
					int amount = Integer.parseInt(prop.getProperty("thread."
							+ i + ".amount"));
					DownloadInfo di = new DownloadInfo(i, url, location,
							startPos, endPos, amount);
					downloadInfos.add(di);
				}
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * ����Ԫ�����ļ�
	 */
	private void createMetaFile(List<DownloadInfo> list) {
		try {
			prop = new Properties();
			prop.setProperty("thread.count", list.size() + "");// ת�����ַ���
			for (DownloadInfo di : list) {
				prop.setProperty("thread." + di.getIndex() + ".startPos",
						di.getStartPos() + "");
				prop.setProperty("thread." + di.getIndex() + ".endPos",
						di.getEndPos() + "");
				prop.setProperty("thread." + di.getIndex() + ".amount",
						di.getAmount() + "");
			}
			File file = new File(location + ".meta");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			prop.store(fos, "this is a com");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * �Ƿ����״�����
	 */
	private boolean isFirstDownload() {
		return (new File(location + ".meta")).exists();
	}

	/**
	 * ��ȡ��������Դ���ļ�����
	 */
	private int calcFileLength() {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			int len = conn.getContentLength();
			conn.disconnect();
			return len;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * ��ʼ����
	 */
	public void startDownload() {
		for (DownloadInfo di : downloadInfos) {
			new DownloadThread(di, ui, this.prop).start();
		}
		// ����Ԫ�ļ�
		new BackGroundWriteMetaThread(prop, location).start();
	}

	// ɾ��.meta�ļ�
	public void deleteMetaFile() {
		File f = new File(location + ".meta");
		f.delete();

	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public DownloadUI getUi() {
		return ui;
	}

	public void setUi(DownloadUI ui) {
		this.ui = ui;
	}

}
