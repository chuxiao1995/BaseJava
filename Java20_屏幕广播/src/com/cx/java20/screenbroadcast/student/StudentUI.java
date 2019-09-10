package com.cx.java20.screenbroadcast.student;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudentUI extends JFrame {
	private JLabel lblIcon;
	public StudentUI() {
		init();
		
	}

	private void init() {
		this.setTitle("ѧ����");
		this.setBounds(0, 0, 1000, 500);
		this.setLayout(null);
		
		//��ǩ�ռ�
		lblIcon = new JLabel();
		lblIcon.setBounds(0, 0,1000,500);
		
		//
		ImageIcon icon = new ImageIcon("D:/1.png");
		lblIcon.setIcon(icon);
		this.add(lblIcon);
		this.setVisible(true);
		}
	
	/**
	 * ����ͼ��
	 */
	public void updateIcon(byte[] unzipFrameData) {
		ImageIcon icon = new ImageIcon(unzipFrameData);
		lblIcon.setIcon(icon);
		
	}
}
