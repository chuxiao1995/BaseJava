package com.cx.java19.qq.common;

/**
 * 
 * ������˽��bean
 *
 */
public class ServerSingleBean extends ChatSingleBean {
	private byte[] chat;

	//��װĿ���û�����Ϣ
	public ServerSingleBean(String userInfo, byte[] chat) {
		this.chat = chat;
		this.setUserInfo(userInfo);
	}

	public byte[] getChat() {
		return chat;
	}

	public void setChat(byte[] chat) {
		this.chat = chat;
	}

}
