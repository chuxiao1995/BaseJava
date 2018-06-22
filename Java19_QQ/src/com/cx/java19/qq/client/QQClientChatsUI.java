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
 * 客户端UI
 *
 */
public class QQClientChatsUI extends JFrame implements ActionListener {
	//维护所有的私聊窗口
	public static Map<String, QQClientChatUI> chatSingleWindow = new HashMap<String, QQClientChatUI>(); 
		
	
	MessageSender sender;
	// 历史聊天区
	private JTextArea taHistory;
	// 好友列表
	private JList<String> lstFriends;
	// 消息输入区
	private JTextArea taInputMessage;
	// 发送按钮
	private JButton btnSend;
	// 刷新好友列表按钮
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

		// 历史区
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
					//打开新窗口
					QQClientChatUI ui = chatSingleWindow.get(recInfo);
					if (ui == null) {
						ui = new QQClientChatUI(sender,recInfo);
						chatSingleWindow.put(recInfo, ui);
					} 
					//窗口获得焦点
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
		btnSend = new JButton("发送");
		btnSend.setBounds(560, 420, 100, 160);
		btnSend.addActionListener(this);
		this.add(btnSend);

		// btnRefresh
		btnRefresh = new JButton("刷新");
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
		// 发送按钮
		if (source == btnSend) {
			String str = taInputMessage.getText();
			if (str != null && !str.equals("")) {
				// 发送自己的聊天信息
				sender.sendMessage(MessageFactory.popCientChatsMessage(str));

			}
		}// 刷新按钮
		else if (source == btnRefresh) {
			sender.sendMessage(MessageFactory.popCientRefreshMessage());

		}

	}

	/**
	 * 
	 * 刷新好友列表
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
	 * 更新历史区域内容
	 */
	public void updateHistory(String[] ss) {
		taHistory.append("[" + ss[0] + "]说:\r\n");
		String formatStr = ss[1].replace("\r\n", "\r\n\t");
		formatStr = "\t" + formatStr + "\r\n";
		taHistory.append(formatStr);

	}

}
