package com.cx.java19.qq.common;
/**
 * 
 * ��������˽����Ϣ
 *
 */
public class ServerChatSingleMessage extends Message<ClientSingleBean>{

	public ServerChatSingleMessage(ClientSingleBean msg) {
		this.setMessageContent(msg);
	}

} 
