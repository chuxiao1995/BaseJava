package com.cx.java19.qq.common;
/**
 * 
 * 服务器的私聊消息
 *
 */
public class ServerChatSingleMessage extends Message<ClientSingleBean>{

	public ServerChatSingleMessage(ClientSingleBean msg) {
		this.setMessageContent(msg);
	}

} 
