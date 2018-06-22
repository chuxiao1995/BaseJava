package com.cx.java18.download;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * �����߳�
 */
public class DownloadThread extends Thread {
	// �Ƿ���ͣ
	public static boolean pause = false;
	private DownloadInfo info;
	private DownloadUI ui;
	private Properties prop;

	public DownloadThread(DownloadInfo info, DownloadUI ui, Properties prop) {
		this.info = info;
		this.ui = ui;
		this.prop = prop;
	}

	public void run() {
		try {
			URL u = new URL(info.getUrl());
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			// ����������Ϥ
			// ********Range bytes=
			// startPos-endPos****************con.setRequestProperty("Range",
			// "bytes=" + ��ʼλ��+ "-" + "����λ��");
			conn.setRequestProperty("Range",
					"bytes=" + (info.getStartPos() + info.getAmount()) + "-"
							+ info.getEndPos());
			InputStream is = conn.getInputStream();

			// �����ļ�д��
			RandomAccessFile raf = new RandomAccessFile(
					info.getLocationString(), "rw");
			// ��λ�ļ�λ��
			raf.seek(info.getStartPos() + info.getAmount());

			byte[] buf = new byte[1024];
			int len = -1;
			while ((len = is.read(buf)) != -1) {
				while (pause) {
					Thread.sleep(1000);
				}
				raf.write(buf, 0, len);
				// //�������ݵ�meta��,���½�����
				updateAmount(info.getIndex(), len);
				// //���ý�����
				ui.updateBar(info.getIndex(), len);
				sleep(200);
			}
			raf.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ����meta�е�amount��
	 */

	private void updateAmount(int index, int len) {
		try {
			int oldAmount = Integer.parseInt(prop.getProperty("thread." + index
					+ ".amount"));
			prop.setProperty("thread." + index + ".amount", (oldAmount + len)
					+ "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
