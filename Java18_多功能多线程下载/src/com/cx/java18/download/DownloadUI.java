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
	 * ����ui
	 */
	// url
	private JLabel lblUrl;
	private JTextField tfUrl;

	// �ļ�����λ��
	private JLabel lblLocation;
	private JTextField tfLocation;

	// �߳���
	private JLabel lblCount;
	private JTextField tfcount;

	private JButton btnStart;
	private JButton btnPause;
	// ������
	public JProgressBar[] bars;
	// ��ɵ��߳���
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
		lblUrl = new JLabel("url ��ַ");
		lblUrl.setBounds(0, 0, 100, 30);
		this.add(lblUrl);

		tfUrl = new JTextField("http://localhost:8080/test.png");
		tfUrl.setBounds(0, 40, 800, 30);
		this.add(tfUrl);
		// location��ǩ
		lblLocation = new JLabel("�����ַ");
		lblLocation.setBounds(0, 80, 100, 30);
		this.add(lblLocation);

		tfLocation = new JTextField("e://test//hello2018.png");
		tfLocation.setBounds(0, 120, 800, 30);
		this.add(tfLocation);

		// �߳���
		lblCount = new JLabel("�߳���");
		lblCount.setBounds(0, 160, 100, 30);
		this.add(lblCount);

		tfcount = new JTextField("3");
		tfcount.setBounds(0, 200, 800, 30);
		this.add(tfcount);

		// ��ť
		btnStart = new JButton("��ʼ");
		btnStart.setBounds(0, 240, 100, 30);
		btnStart.addActionListener(this);
		this.add(btnStart);

		btnPause = new JButton("��ͣ");
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
	 * ��ť����¼�
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnStart) {
			String url = tfUrl.getText();
			String location = tfLocation.getText();
			int count = Integer.parseInt(tfcount.getText());
			// �½�һ��������
			downloader = new Downloader(url, location, count, this);
			// ��̬������
			this.addBars(downloader.getDownloadInfos());
			// ��ʼ����
			downloader.startDownload();

		} else if (source == btnPause) {
			DownloadThread.pause = !DownloadThread.pause;
			if (DownloadThread.pause) {
				btnPause.setText("����");
			} else {
				btnPause.setText("��ͣ");
			}
		}
	}

	/**
	 * ���½�����
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
	 * �������
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
	 * ��Ӷ�̬������
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
			bars[di.getIndex()].setValue(di.getAmount()); // �Ѿ����ص���

			this.add(bars[di.getIndex()]);
		}
		// ���»滭����
		this.repaint();
	}

}
