package com.cx.java19.qq.common;
/**
 * 
 * ��������Ⱥ����Ϣ��¼
 *
 */
public class ServerChatsMessage extends Message<String[]>{

	public ServerChatsMessage(String[] msg) {
		this.setMessageContent(msg);
	}

} 
