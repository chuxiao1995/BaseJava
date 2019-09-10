package com.cx.java19.qq.client;

import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		try {
			//创建Socket
			Socket socket = new Socket("192.168.2.178",8888);
			
			//创建消息发送器
			MessageSender sender = new MessageSender(socket);
					
			//创建UI
			QQClientChatsUI ui = new QQClientChatsUI(sender);
			
			
			
			new ServerMessageReceiver(socket,ui,sender).start();
			System.out.println("客户端启动了");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
