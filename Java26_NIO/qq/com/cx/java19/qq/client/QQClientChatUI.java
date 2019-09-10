package com.cx.java19.qq.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.cx.java19.qq.common.MessageFactory;
/**
 * �ͻ���˽�Ľ���
 * @author 86152
 *
 */

public class QQClientChatUI extends JFrame implements ActionListener{
	MessageSender sender;
	// ��ʷ������
	private JTextArea taHistory;
	
	// ��Ϣ������
	private JTextArea taInputMessage;
	// ���Ͱ�ť
	private JButton btnSend;
	//
	private String recvInfo;


	public QQClientChatUI(MessageSender sender,String recvInfo) {
		this.recvInfo = recvInfo;
		this.sender = sender;
		init();
		this.setVisible(true);
	}

	//
	private void init() {
		this.setTitle("QQClient");
		this.setBounds(100, 100, 800, 600);
		this.setLayout(null);

		// ��ʷ��
		taHistory = new JTextArea();
		taHistory.setBounds(0, 0, 600, 400);

		JScrollPane sp1 = new JScrollPane(taHistory);
		sp1.setBounds(0, 0, 600, 400);
		this.add(sp1);

	

		// taInputMessage
		taInputMessage = new JTextArea();
		taInputMessage.setBounds(0, 420, 540, 160);
		this.add(taInputMessage);

		// btnSend
		btnSend = new JButton("����");
		btnSend.setBounds(560, 420, 100, 160);
		btnSend.addActionListener(this);
		this.add(btnSend);

		

		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(-1);
//			}
//		});

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// ���Ͱ�ť
		if (source == btnSend) {
			String str = taInputMessage.getText();
			if (str != null && !str.equals("")) {
				// �����Լ���������Ϣ
				sender.sendMessage(MessageFactory.popCientChatSingleMessage(recvInfo,str));

			}
		}// ˢ�°�ť
	

	}

	

	/**
	 * 
	 * ������ʷ��������
	 */
	public void updateHistory(String[] ss) {
		taHistory.append("[" + ss[0] + "]˵:\r\n");
		String formatStr = ss[1].replace("\r\n", "\r\n\t");
		formatStr = "\t" + formatStr + "\r\n";
		taHistory.append(formatStr);

	}



}
