package com.cx.java19.qq.common;
/**
 * 
 *�ͻ��˷���Ⱥ����Ϣ
 */
public class ClientChatsMessage extends Message<byte[]> {
	public ClientChatsMessage(byte[] raw){
		this.setMessageContent(raw);
	}

}
