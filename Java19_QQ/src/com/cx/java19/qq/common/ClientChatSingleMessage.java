package com.cx.java19.qq.common;
/**
 * 
 *�ͻ��˷���˽����Ϣ
 */
public class ClientChatSingleMessage extends Message<ChatSingleBean> {
	public ClientChatSingleMessage(ChatSingleBean csb){
		this.setMessageContent(csb);
	}

}
