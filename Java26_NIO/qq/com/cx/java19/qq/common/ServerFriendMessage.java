package com.cx.java19.qq.common;

import java.util.List;
/**
 * 
 * 服务器端好友列表
 *
 */
public class ServerFriendMessage extends Message<List<String>> {
	public ServerFriendMessage(List<String> message) {
		this.setMessageContent(message);
		
	}
}
