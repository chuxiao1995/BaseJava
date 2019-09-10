package com.cx.java19.qq.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cx.java19.qq.common.MessageFactory;
import com.cx.java19.qq.util.Util;

/**
 * ��Ϣͨ�ŷ�����
 *
 */
public class QQServer {

	public Map<String, Socket> allSocketMap = new HashMap<String, Socket>();

	public void startServer() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			while (true) {
				// ���ܿͻ�������
				Socket s = ss.accept();
				allSocketMap.put(Util.getUserInfo(s), s);
				// �������̣߳���������ÿ��client������
				new ClientMessageReceiver(s, this).start();
				// Ⱥ�������б�
				this.broadcatsFriendList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	/**
	 * 
	 * �õ������б�
	 */
	public List<String> getFriendList() {
		return new ArrayList<String>(allSocketMap.keySet());
	}

	/**
	 * �㲥��Ϣ
	 */
	public void broadcastMessage(byte[] message) {
		try {
			Collection<Socket> sockets = allSocketMap.values();
			for (Socket ss : sockets) {
				OutputStream os = ss.getOutputStream();
				os.write(message);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Ⱥ�������б�
	 */
	public void broadcatsFriendList() {
		broadcastMessage(MessageFactory.popServerFriends(getFriendList()));

	}


	/**
	 * 
	 * ɾ������
	 */
	public void removeFriend(String userInfo) {
		allSocketMap.remove(userInfo);
		
	}

}
