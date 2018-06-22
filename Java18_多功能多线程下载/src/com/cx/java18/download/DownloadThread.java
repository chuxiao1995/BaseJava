package com.cx.java18.download;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * 下载线程
 */
public class DownloadThread extends Thread {
	// 是否暂停
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
			// 设置请求熟悉
			// ********Range bytes=
			// startPos-endPos****************con.setRequestProperty("Range",
			// "bytes=" + 开始位置+ "-" + "结束位置");
			conn.setRequestProperty("Range",
					"bytes=" + (info.getStartPos() + info.getAmount()) + "-"
							+ info.getEndPos());
			InputStream is = conn.getInputStream();

			// 本地文件写入
			RandomAccessFile raf = new RandomAccessFile(
					info.getLocationString(), "rw");
			// 定位文件位置
			raf.seek(info.getStartPos() + info.getAmount());

			byte[] buf = new byte[1024];
			int len = -1;
			while ((len = is.read(buf)) != -1) {
				while (pause) {
					Thread.sleep(1000);
				}
				raf.write(buf, 0, len);
				// //保存数据到meta中,更新进度条
				updateAmount(info.getIndex(), len);
				// //设置进度条
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
	 * 更新meta中的amount量
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
