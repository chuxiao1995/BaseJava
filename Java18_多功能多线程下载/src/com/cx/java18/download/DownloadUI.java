package com.cx.java18.download;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class DownloadUI extends JFrame implements ActionListener {
	/**
	 * 下载ui
	 */
	// url
	private JLabel lblUrl;
	private JTextField tfUrl;

	// 文件保存位置
	private JLabel lblLocation;
	private JTextField tfLocation;

	// 线程数
	private JLabel lblCount;
	private JTextField tfcount;

	private JButton btnStart;
	private JButton btnPause;
	// 进度条
	public JProgressBar[] bars;
	// 完成的线程数
	private int completedCount = 0;
	//
	private Downloader downloader;

	// Container barContainer =this;

	public DownloadUI() {
		init();
		this.setVisible(true);
	}

	private void init() {
		this.setBounds(0, 0, 800, 600);
		this.setLayout(null);
		//
		lblUrl = new JLabel("url 地址");
		lblUrl.setBounds(0, 0, 100, 30);
		this.add(lblUrl);

		tfUrl = new JTextField("http://localhost:8080/test.png");
		tfUrl.setBounds(0, 40, 800, 30);
		this.add(tfUrl);
		// location标签
		lblLocation = new JLabel("保存地址");
		lblLocation.setBounds(0, 80, 100, 30);
		this.add(lblLocation);

		tfLocation = new JTextField("e://test//hello2018.png");
		tfLocation.setBounds(0, 120, 800, 30);
		this.add(tfLocation);

		// 线程数
		lblCount = new JLabel("线程数");
		lblCount.setBounds(0, 160, 100, 30);
		this.add(lblCount);

		tfcount = new JTextField("3");
		tfcount.setBounds(0, 200, 800, 30);
		this.add(tfcount);

		// 按钮
		btnStart = new JButton("开始");
		btnStart.setBounds(0, 240, 100, 30);
		btnStart.addActionListener(this);
		this.add(btnStart);

		btnPause = new JButton("暂停");
		btnPause.setBounds(150, 240, 100, 30);
		btnPause.addActionListener(this);
		this.add(btnPause);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}

	/**
	 * 按钮点击事件
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnStart) {
			String url = tfUrl.getText();
			String location = tfLocation.getText();
			int count = Integer.parseInt(tfcount.getText());
			// 新建一个下载器
			downloader = new Downloader(url, location, count, this);
			// 动态进度条
			this.addBars(downloader.getDownloadInfos());
			// 开始下载
			downloader.startDownload();

		} else if (source == btnPause) {
			DownloadThread.pause = !DownloadThread.pause;
			if (DownloadThread.pause) {
				btnPause.setText("继续");
			} else {
				btnPause.setText("暂停");
			}
		}
	}

	/**
	 * 更新进度条
	 * 
	 * @param index
	 * @param len
	 */
	public void updateBar(int index, int len) {
		bars[index].setValue(bars[index].getValue() + len);
		bars[index].repaint(0);
		if (bars[index].getValue() >= bars[index].getMaximum()) {
			processFinsh();
		}
	}

	/**
	 * 处理完成
	 */
	private synchronized void processFinsh() {
		completedCount++;
		if (completedCount == bars.length) {

			// barContainer.removeAll();
			for (JProgressBar bar : bars) {
				this.remove(bar);
			}
			completedCount = 0;
			bars = null;
			this.repaint();
			downloader.prop = null;
			downloader.deleteMetaFile();
		}

	}

	/**
	 * 添加动态进度条
	 * 
	 */
	private void addBars(List<DownloadInfo> downloadInfos) {
		bars = new JProgressBar[downloadInfos.size()];
		for (DownloadInfo di : downloadInfos) {
			bars[di.getIndex()] = new JProgressBar();
			bars[di.getIndex()].setBounds(10, 280 + (di.getIndex() * 50), 750,
					30);
			bars[di.getIndex()].setMaximum(di.getEndPos() - di.getStartPos()
					+ 1);
			bars[di.getIndex()].setValue(di.getAmount()); // 已经下载的量

			this.add(bars[di.getIndex()]);
		}
		// 重新绘画窗口
		this.repaint();
	}

}
