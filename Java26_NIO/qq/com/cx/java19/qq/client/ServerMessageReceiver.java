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
			//循环接受服务器发送来的消息
			while (true) {
				Message msg = MessageFactory.parseMessageFromInputStream(in);
				System.out.println("客户端接受消息"+msg);
				//好友列表
				if (msg.getClass() == ServerFriendMessage.class) { 
					List<String> list = ((ServerFriendMessage)msg).getMessageContent();
					//刷新好友列表
					ui.refreshFriendList(list);
				}else if (msg.getClass() == ServerChatsMessage.class) {
					System.out.println("进入chat");
					String[] ss = ((ServerChatsMessage)msg).getMessageContent();
					//更新聊天历史区
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
					//更新聊天历史区
					ui.updateHistory(new String[]{senderInfo,chat});
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

}
