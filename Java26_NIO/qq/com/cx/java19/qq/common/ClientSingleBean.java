package com.cx.java19.qq.common;

/**
 * 
 * �ͻ���˽��bean
 *
 */
public class ClientSingleBean extends ChatSingleBean {
	private String chat;

	//�ͻ���˽����Ϣ��װ
	public ClientSingleBean(String userInfo, String chat) {
		this.chat = chat;
		this.setUserInfo(userInfo);
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

}
