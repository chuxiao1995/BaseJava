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
 *Server��:�ͻ�����Ϣ������
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
			//�ͻ�������ˢ�º����б�
			if (msg.getClass() == ClientRequestFreshFriendsMessage.class) {
				//���ͺ����б�
				out.write(MessageFactory.popServerFriends(server.getFriendList()));
				out.flush();
			}
			//�ͻ��˵�������Ϣ
			else if(msg.getClass() == ClientChatsMessage.class) {
				//�ͻ�������Ϣ
				byte[] chat = ((ClientChatsMessage)msg).getMessageContent();
				System.out.println("�ͻ���������Ϣ :"+ chat);
				//������������ת����������Ϣ
				System.out.println("userInfo: " + userInfo);
				byte[] serverChat = MessageFactory.popServerChatsMessage(userInfo, chat);
				System.out.println("������ת��������Ϣ�� "+serverChat);
				//Ⱥ��������Ϣ
				server.broadcastMessage(serverChat);
			
			}else if (msg.getClass() == ClientChatSingleMessage.class) {
				//
				ServerSingleBean csb = (ServerSingleBean)((ClientChatSingleMessage)msg).getMessageContent();
				//��������
				byte[] chat = csb.getChat();
				//���ܷ�
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
