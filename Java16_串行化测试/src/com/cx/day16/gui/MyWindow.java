package com.cx.day16.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyWindow {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		// f.setSize(800,600);
		// f.setLocation(100,50);
		f.setBounds(100, 50, 800, 600);
		// 创建按钮
		f.setLayout(null);
		JButton Button = new JButton("OK");
		Button.setBounds(100, 50, 100, 50);
		Button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("点击按钮");
			}
		});
		// 添加按钮
		f.add(Button);
		// 添加监听
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
		});
		// 菜单栏
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("文件");
		JMenuItem mi = new JMenuItem("打开");
		menu.add(mi);
		bar.add(menu);
		f.setJMenuBar(bar);
		f.setVisible(true);
	}

}
