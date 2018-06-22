package com.cx.java19.qq.common;

/**
 * ��Ϣ
 *
 */
public abstract class Message<T> {
	// �ͻ��˷��͸�������-ˢ�º����б�
	public static final int CLIENT_TO_SERVER_REFRESH_FRIENDS = 1;
	// �ͻ��˷��͸�������-�ı���Ϣ-����Ⱥ��
	public static final int CLIENT_TO_SERVER_CHATS = 2;
	// �ͻ��˷��͸�������-�ı���Ϣ-����˽��
	public static final int CLIENT_TO_SERVER_CHAT = 3;
	// �ͻ��˷��͸�������-�����б�
	public static final int SERVER_TO_CLIENT_FRIEND_LIST = 4;
	// �ͻ��˷��͸�������-������Ϣ
	public static final int SERVER_TO_CLIENT_CHAT_MESSAGE = 5;
	// �ͻ��˷��͸�������-˽����Ϣ
	public static final int SERVER_TO_CLIENT_CHAT_MESSAGE_SINGLE = 6;

	//��Ϣ����
	private T messageContent;

	public T getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(T messageContent) {
		this.messageContent = messageContent;
	};

}
