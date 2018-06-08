package com.cx.day16.gui;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyTextEditor extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3347215767124322676L;
	private JTextArea taContent;
	private JButton btnSaveButton;
	private JButton btnOpenButton;
	public static void main(String[] args) {
		new MyTextEditor();
	}
	public  MyTextEditor() {
		init();
		this.setVisible(true);
		
	}
	private void init() {
		this.setBounds(100,50,800,600);
		this.setLayout(null);
		
		taContent = new JTextArea();
		taContent.setBounds(0,0,800,400);
		this.add(taContent);
		
		JScrollPane pane1 = new JScrollPane(taContent);
		pane1.setBounds(0,0,800,400);
		this.add(pane1);
		
		
		
		btnSaveButton = new JButton("保存");
		btnSaveButton.setBounds(100,450,100,50);
		btnSaveButton.addActionListener(this);
		this.add(btnSaveButton);
		
		
		
		btnOpenButton = new JButton("打开");
		btnOpenButton.setBounds(500,450,100,50);
		btnOpenButton.addActionListener(this);
		this.add(btnOpenButton);
		
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(-1);
				
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == btnSaveButton) {
			try {
				FileWriter writer = new FileWriter("d:/test.txt");
				writer.write(taContent.getText());
				writer.close();
				taContent.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//System.out.println("save");
		} else if(s == btnOpenButton){
			//System.out.println("open");
			try {
				taContent.setText("");
				FileDialog fd = new FileDialog(this,"打开文件");
				fd.setVisible(true);
				File f = new File(fd.getDirectory(),fd.getFile());
				new LoadTextThread(f, taContent).start();
//				FileReader reader = new FileReader(f);
//				char[] buf = new char[1024];
//				int len = 0;
//				while ((len = reader.read(buf))!=-1) {
//					String ss = new String(buf,0,len);
//					taContent.append(ss);
//				}
//				FileReader reader = new FileReader("d:/test.txt");
//				char[] buf =new char[1024];
//				int len = 0;
//				while ((len = reader.read(buf)) != -1) {
//					String ss = new String(buf,0,len);
//					taContent.append(ss);
//				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}







































