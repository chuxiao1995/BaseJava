package com.cx.java19.qq.common;
/**
 * 
 * 服务器的群聊消息记录
 *
 */
public class ServerChatsMessage extends Message<String[]>{

	public ServerChatsMessage(String[] msg) {
		this.setMessageContent(msg);
	}

} 
