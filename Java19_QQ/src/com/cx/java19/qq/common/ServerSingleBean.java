package com.cx.java19.qq.common;

/**
 * 
 * 服务器私聊bean
 *
 */
public class ServerSingleBean extends ChatSingleBean {
	private byte[] chat;

	//封装目标用户的信息
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
