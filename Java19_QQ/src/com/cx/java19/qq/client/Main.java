package com.cx.java19.qq.client;

import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		try {
			//����Socket
			Socket socket = new Socket("192.168.2.178",8888);
			
			//������Ϣ������
			MessageSender sender = new MessageSender(socket);
					
			//����UI
			QQClientChatsUI ui = new QQClientChatsUI(sender);
			
			
			
			new ServerMessageReceiver(socket,ui,sender).start();
			System.out.println("�ͻ���������");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
