package com.cx.java19.qq.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.cx.java19.qq.common.ClientChatSingleMessage;
import com.cx.java19.qq.common.ClientRequestFreshFriendsMessage;
import com.cx.java19.qq.common.ClientChatsMessage;
import com.cx.java19.qq.common.Message;
import com.cx.java19.qq.common.MessageFactory;
import com.cx.java19.qq.common.ServerSingleBean;
import com.cx.java19.qq.util.Util;
/**
 * 
 *Server端:客户端消息接受者
 */
public class ClientMessageReceiver extends Thread {
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private QQServer server;
	private String userInfo;
	public ClientMessageReceiver(Socket s,QQServer server){
		try {
			this.server = server;
			this.socket = s;
			this.in= s.getInputStream();
			this.out = s.getOutputStream();
			this.userInfo = Util.getUserInfo(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (true) {
			Message msg	 =MessageFactory.parseMessageFromInputStream(in);
			//客户端请求刷新好友列表
			if (msg.getClass() == ClientRequestFreshFriendsMessage.class) {
				//发送好友列表
				out.write(MessageFactory.popServerFriends(server.getFriendList()));
				out.flush();
			}
			//客户端的聊天信息
			else if(msg.getClass() == ClientChatsMessage.class) {
				//客户聊天消息
				byte[] chat = ((ClientChatsMessage)msg).getMessageContent();
				System.out.println("客户端聊天信息 :"+ chat);
				//生产服务器端转发的聊天消息
				System.out.println("userInfo: " + userInfo);
				byte[] serverChat = MessageFactory.popServerChatsMessage(userInfo, chat);
				System.out.println("服务器转发聊天信息： "+serverChat);
				//群发聊天消息
				server.broadcastMessage(serverChat);
			
			}else if (msg.getClass() == ClientChatSingleMessage.class) {
				//
				ServerSingleBean csb = (ServerSingleBean)((ClientChatSingleMessage)msg).getMessageContent();
				//聊天内容
				byte[] chat = csb.getChat();
				//接受方
				String recv = csb.getUserInfo();
				//
				Socket socket =server.allSocketMap.get(recv);
				byte[] pack = MessageFactory.popServerChatSingleMessage(userInfo, chat);
				OutputStream out = socket.getOutputStream();
				out.write(pack);
				out.flush();
			}
			}
		} catch (Exception e) {
			server.removeFriend(userInfo);
			server.broadcatsFriendList();
			
		}
	}
	

}
