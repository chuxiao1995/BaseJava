package com.cx.java19.qq.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import com.cx.java19.qq.common.ClientSingleBean;
import com.cx.java19.qq.common.Message;
import com.cx.java19.qq.common.MessageFactory;
import com.cx.java19.qq.common.ServerChatSingleMessage;
import com.cx.java19.qq.common.ServerChatsMessage;
import com.cx.java19.qq.common.ServerFriendMessage;
import com.cx.java19.qq.common.ServerSingleBean;

public class ServerMessageReceiver extends Thread{
	private Socket sock;
	private InputStream in;
	private OutputStream out;
	private QQClientChatsUI ui;
	private MessageSender sender;
	
	public ServerMessageReceiver(Socket sock,QQClientChatsUI ui,MessageSender sender){
		try {
			this.sender =sender;
			this.sock = sock;
			this.in = sock.getInputStream();
			this.out = sock.getOutputStream();
			this.ui =ui;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 */
	public void run() {
		try {
			//ѭ�����ܷ���������������Ϣ
			while (true) {
				Message msg = MessageFactory.parseMessageFromInputStream(in);
				System.out.println("�ͻ��˽�����Ϣ"+msg);
				//�����б�
				if (msg.getClass() == ServerFriendMessage.class) { 
					List<String> list = ((ServerFriendMessage)msg).getMessageContent();
					//ˢ�º����б�
					ui.refreshFriendList(list);
				}else if (msg.getClass() == ServerChatsMessage.class) {
					System.out.println("����chat");
					String[] ss = ((ServerChatsMessage)msg).getMessageContent();
					//����������ʷ��
					System.out.println("ss :"+ss);
					ui.updateHistory(ss);
					
				}else if (msg.getClass() == ServerChatSingleMessage.class) {
					ServerChatSingleMessage scsm = ((ServerChatSingleMessage)msg);
					ClientSingleBean ccsb = (ClientSingleBean) scsm.getMessageContent();
					String senderInfo =ccsb.getUserInfo();
					String chat = ccsb.getChat();
					
					//
					QQClientChatUI ui = QQClientChatsUI.chatSingleWindow.get(senderInfo);
					if (ui == null) {
						ui = new QQClientChatUI(sender, senderInfo);
						QQClientChatsUI.chatSingleWindow.put(senderInfo, ui);
						
					}
					//����������ʷ��
					ui.updateHistory(new String[]{senderInfo,chat});
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

}
