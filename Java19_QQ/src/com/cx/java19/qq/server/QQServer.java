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
 * 消息通信服务器
 *
 */
public class QQServer {

	public Map<String, Socket> allSocketMap = new HashMap<String, Socket>();

	public void startServer() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			while (true) {
				// 接受客户端连接
				Socket s = ss.accept();
				allSocketMap.put(Util.getUserInfo(s), s);
				// 开启分线程，单独处理每个client的请求
				new ClientMessageReceiver(s, this).start();
				// 群发好友列表
				this.broadcatsFriendList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	/**
	 * 
	 * 得到好友列表
	 */
	public List<String> getFriendList() {
		return new ArrayList<String>(allSocketMap.keySet());
	}

	/**
	 * 广播消息
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
	 * 群发好友列表
	 */
	public void broadcatsFriendList() {
		broadcastMessage(MessageFactory.popServerFriends(getFriendList()));

	}


	/**
	 * 
	 * 删除好友
	 */
	public void removeFriend(String userInfo) {
		allSocketMap.remove(userInfo);
		
	}

}
