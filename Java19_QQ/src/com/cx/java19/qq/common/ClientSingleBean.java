package com.cx.java19.qq.common;

/**
 * 
 * 客户端私聊bean
 *
 */
public class ClientSingleBean extends ChatSingleBean {
	private String chat;

	//客户端私聊信息封装
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
