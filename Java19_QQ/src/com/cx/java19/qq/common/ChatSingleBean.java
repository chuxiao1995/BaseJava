package com.cx.java19.qq.common;

/**
 * 
 * 单聊信息
 *服务器解析时，userInfo是接受者消息
 *客户端解析时，userInfo是发送者消息
 */
public abstract class ChatSingleBean {
	// 发送者/接受者
	private String userInfo;

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

}
