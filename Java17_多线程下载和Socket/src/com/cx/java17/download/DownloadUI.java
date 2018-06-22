package com.cx.java17.download;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	// 进度条
	public JProgressBar bar;

	public DownloadUI() {
		init();
		this.setVisible(true);
	}
	/**
	 * 初始化布局
	 */

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

		tfLocation = new JTextField("e://hello2018.png");
		tfLocation.setBounds(0, 120, 800, 30);
		this.add(tfLocation);

		// 线程数
		lblCount = new JLabel("url 地址");
		lblCount.setBounds(0, 160, 100, 30);
		this.add(lblCount);

		tfcount = new JTextField("3");
		tfcount.setBounds(0, 200, 800, 30);
		this.add(tfcount);

		// 按钮
		btnStart = new JButton("开始");
		btnStart.setBounds(400, 240, 100, 30);
		btnStart.addActionListener(this);
		this.add(btnStart);

		// 进度条
		bar = new JProgressBar();
		bar.setBounds(0,280,800,30);
		this.add(bar);
	

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnStart) {
			String url = tfUrl.getText();
			String location = tfLocation.getText();
			int count = Integer.parseInt(tfcount.getText());
			// 新建一个下载器
			Downloader downloader = new Downloader(url, location, count, this);
			// 设置bar的maxvalue
			bar.setMaximum(downloader.getLength());
			// 开始下载
			downloader.startDownload();

		}

	}

}
