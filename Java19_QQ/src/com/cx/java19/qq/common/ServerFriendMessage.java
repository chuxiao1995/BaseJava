package com.cx.java19.qq.common;

import java.util.List;
/**
 * 
 * �������˺����б�
 *
 */
public class ServerFriendMessage extends Message<List<String>> {
	public ServerFriendMessage(List<String> message) {
		this.setMessageContent(message);
		
	}
}
