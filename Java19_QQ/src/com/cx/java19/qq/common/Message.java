package com.cx.java19.qq.common;

/**
 * 消息
 *
 */
public abstract class Message<T> {
	// 客户端发送给服务器-刷新好友列表
	public static final int CLIENT_TO_SERVER_REFRESH_FRIENDS = 1;
	// 客户端发送给服务器-文本消息-用于群聊
	public static final int CLIENT_TO_SERVER_CHATS = 2;
	// 客户端发送给服务器-文本消息-用于私聊
	public static final int CLIENT_TO_SERVER_CHAT = 3;
	// 客户端发送给服务器-好友列表
	public static final int SERVER_TO_CLIENT_FRIEND_LIST = 4;
	// 客户端发送给服务器-聊天消息
	public static final int SERVER_TO_CLIENT_CHAT_MESSAGE = 5;
	// 客户端发送给服务器-私聊消息
	public static final int SERVER_TO_CLIENT_CHAT_MESSAGE_SINGLE = 6;

	//消息内容
	private T messageContent;

	public T getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(T messageContent) {
		this.messageContent = messageContent;
	};

}
