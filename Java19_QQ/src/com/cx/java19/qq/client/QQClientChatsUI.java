package com.cx.java19.qq.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.cx.java19.qq.common.MessageFactory;

/**
 * 
 * �ͻ���UI
 *
 */
public class QQClientChatsUI extends JFrame implements ActionListener {
	//ά�����е�˽�Ĵ���
	public static Map<String, QQClientChatUI> chatSingleWindow = new HashMap<String, QQClientChatUI>(); 
		
	
	MessageSender sender;
	// ��ʷ������
	private JTextArea taHistory;
	// �����б�
	private JList<String> lstFriends;
	// ��Ϣ������
	private JTextArea taInputMessage;
	// ���Ͱ�ť
	private JButton btnSend;
	// ˢ�º����б�ť
	private JButton btnRefresh;

	public QQClientChatsUI(MessageSender sender) {
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

		// lstFriends
		lstFriends = new JList<String>();
		lstFriends.setBounds(620, 0, 160, 400);
		lstFriends.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count = e.getClickCount();
				if (count == 2) {
					String recInfo =lstFriends.getSelectedValue();
					//���´���
					QQClientChatUI ui = chatSingleWindow.get(recInfo);
					if (ui == null) {
						ui = new QQClientChatUI(sender,recInfo);
						chatSingleWindow.put(recInfo, ui);
					} 
					//���ڻ�ý���
					//ui.setFocusable(true);
					 
					
				}
			}
		});
		this.add(lstFriends);

		// taInputMessage
		taInputMessage = new JTextArea();
		taInputMessage.setBounds(0, 420, 540, 160);
		this.add(taInputMessage);

		// btnSend
		btnSend = new JButton("����");
		btnSend.setBounds(560, 420, 100, 160);
		btnSend.addActionListener(this);
		this.add(btnSend);

		// btnRefresh
		btnRefresh = new JButton("ˢ��");
		btnRefresh.setBounds(680, 420, 100, 160);
		btnRefresh.addActionListener(this);
		this.add(btnRefresh);

		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				sender.sendMessage(MessageFactory.popCientChatsMessage(str));

			}
		}// ˢ�°�ť
		else if (source == btnRefresh) {
			sender.sendMessage(MessageFactory.popCientRefreshMessage());

		}

	}

	/**
	 * 
	 * ˢ�º����б�
	 */
	public void refreshFriendList(List<String> list) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (String s : list) {
			listModel.addElement(s);
		}
		lstFriends.setModel(listModel);

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
