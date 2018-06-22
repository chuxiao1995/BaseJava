package com.cx.java19.qq.common;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.cx.java19.qq.util.Util;

/**
 * �����࣬��װ�ͻ��˺ͷ���������Ϣ
 * 
 * @author 86152
 *
 */
public class MessageFactory {
	/**
	 * ��װ�ͻ��˷��͵�Ⱥ����Ϣ
	 */
	public static byte[] popCientChatsMessage(String msg) {
		return popMessage(Message.CLIENT_TO_SERVER_CHATS, msg.getBytes());
	}

	/**
	 * ��װ�ͻ��˷��͵�˽����Ϣ receiverUserInfo ��Ϣ��������Ϣ msg��Ҫ���͵���Ϣ
	 */
	public static byte[] popCientChatSingleMessage(String receiverUserInfo,
			String msg) {
		// ��������Ϣ����
		byte[] recv = receiverUserInfo.getBytes();
		// ��Ϣ����
		byte[] chat = msg.getBytes();
		// ��װ����
		byte[] pack = new byte[4 + 4 + recv.length + 4 + chat.length];
		// 1��Ϣ����
		System.arraycopy(Util.int2Bytes(Message.CLIENT_TO_SERVER_CHAT), 0,
				pack, 0, 4);

		// 2��������Ϣ����
		System.arraycopy(Util.int2Bytes(recv.length), 0, pack, 4, 4);
		// 3��������Ϣ
		System.arraycopy(recv, 0, pack, 8, recv.length);
		// 4��Ϣ����
		System.arraycopy(Util.int2Bytes(chat.length), 0, pack, 8 + recv.length,
				4);
		// 5��Ϣ����
		System.arraycopy(chat, 0, pack, 8 + recv.length + 4, chat.length);
		return pack;
	}

	/**
	 * ��װ�ͻ��˷��͵�ˢ�º����б���Ϣ
	 */
	public static byte[] popCientRefreshMessage() {
		return Util.int2Bytes(Message.CLIENT_TO_SERVER_REFRESH_FRIENDS);
	}

	/**
	 * ��װ�������˺����б���Ϣ
	 */
	public static byte[] popServerFriends(List<String> list) {
		byte[] objArr = Util.serialize((Serializable) list);
		return popMessage(Message.SERVER_TO_CLIENT_FRIEND_LIST, objArr);
	}

	/**
	 * ��װ��������ת������Ϣ-Ⱥ����Ϣ
	 * -----------------------------------
	 *  |		|		|		|		|	4�ֽ� --> ��Ϣ����
	 * -----------------------------------
	 *  |		|		|		|		|	4---->userInfo����
	 * ------------------------------------
	 * |..................................| n---->userInfo����
	 * ------------------------------------
	 *  |		|		|		|		|	4--->��Ϣ����
	 * ------------------------------------
	 *  |..................................|n--->��Ϣ����
	 * ------------------------------------
	 */
	public static byte[] popServerChatsMessage(String userInfo,
			byte[] clientChatMsg) {
		// �û���Ϣ����
		byte[] userInfoArr = userInfo.getBytes();

		// ����Ϣ����
		byte[] allArr = new byte[4 + 4 + userInfoArr.length + 4
				+ clientChatMsg.length];
		// ��Ϣ����
		System.arraycopy(Util.int2Bytes(Message.SERVER_TO_CLIENT_CHAT_MESSAGE),
				0, allArr, 0, 4);
		// �û���Ϣ����
		System.arraycopy(Util.int2Bytes(userInfoArr.length), 0, allArr, 4, 4);
		// �û���Ϣ����
		System.arraycopy(userInfoArr, 0, allArr, 8, userInfoArr.length);
		// �������ݳ���
		System.arraycopy(Util.int2Bytes(clientChatMsg.length), 0, allArr,
				(8 + userInfoArr.length), 4);
		// ��������
		System.arraycopy(clientChatMsg, 0, allArr,
				(8 + userInfoArr.length + 4), clientChatMsg.length);
		return allArr;
	}

	/**
	 * ��װ��������˽����Ϣ
	 * 
	 * @param userInfo
	 * @param ChatMsg
	 *            : pure message
	 * @return
	 */
	public static byte[] popServerChatSingleMessage(String senderUserInfo,
			byte[] chatMsg) {
		// �û���Ϣ����
		byte[] senderInfo = senderUserInfo.getBytes();

		// ����Ϣ����
		byte[] pack = new byte[4 + 4 + senderInfo.length + 4 + chatMsg.length];
		// 1��Ϣ����
		System.arraycopy(
				Util.int2Bytes(Message.SERVER_TO_CLIENT_CHAT_MESSAGE_SINGLE),
				0, pack, 0, 4);
		// 2Ŀ���û���Ϣ����
		System.arraycopy(Util.int2Bytes(senderInfo.length), 0, pack, 4, 4);
		// 3Ŀ���û���Ϣ����
		System.arraycopy(senderInfo, 0, pack, 8, senderInfo.length);
		// 4�������ݳ���
		System.arraycopy(Util.int2Bytes(chatMsg.length), 0, pack,
				(8 + senderInfo.length), 4);
		// 5��������
		System.arraycopy(chatMsg, 0, pack, (8 + senderInfo.length + 4),
				chatMsg.length);
		return pack;
	}

	/**
	 * ��װһ������Ϣ
	 * 
	 */
	private static byte[] popMessage(int msgType, byte[] msgArr) {
		// ��Ϣ����
		byte[] msglenArr = Util.int2Bytes(msgArr.length);
		// ��Ϣ����
		byte[] type = Util.int2Bytes(msgType);
		// ����Ϣ
		byte[] allArr = new byte[type.length + msglenArr.length + msgArr.length];
		// ������װ��Ϣ��all
		System.arraycopy(type, 0, allArr, 0, type.length);
		System.arraycopy(msglenArr, 0, allArr, type.length, msglenArr.length);
		System.arraycopy(msgArr, 0, allArr, (type.length + msglenArr.length),
				msgArr.length);
		return allArr;
	}

	/**
	 * ���������н�����Ϣ
	 * 
	 * @param is
	 * @return
	 */
	public static Message parseMessageFromInputStream(InputStream is) {
		try {
			byte[] typeArr = new byte[4];
			byte[] buf4 = new byte[4];
			is.read(typeArr);
			int type = Util.bytes2Int(typeArr);
			// �ж���Ϣ����
			switch (type) {

			/************************************ �������������� **************************************/
			// ����ˢ�º���
			case Message.CLIENT_TO_SERVER_REFRESH_FRIENDS:
				return new ClientRequestFreshFriendsMessage();

				// �ͻ��˷���Ⱥ����Ϣ
			case Message.CLIENT_TO_SERVER_CHATS:
				// ��ȡ��Ϣ����
				is.read(buf4);
				int msgLen = Util.bytes2Int(buf4);
				// ��Ϣ����
				byte[] msgBuf = new byte[msgLen];
				is.read(msgBuf);
				// �ͻ��˷��͵�������Ϣ(��������Ϣ)
				return new ClientChatsMessage(msgBuf);

				// �ͻ���˽����Ϣ
			case Message.CLIENT_TO_SERVER_CHAT:
				is.read(buf4);
				int recvInfoLen = Util.bytes2Int(buf4);
				// ��������
				byte[] recvBuf = new byte[recvInfoLen];
				is.read(recvBuf);
				String recvInfo = new String(recvBuf);

				is.read(buf4);
				int chatLen = Util.bytes2Int(buf4);
				byte[] chatBuf = new byte[chatLen];
				is.read(chatBuf);
				// ��������˽��
				return new ClientChatSingleMessage(new ServerSingleBean(
						recvInfo, chatBuf));

				/*********************************** �ͻ��˽������� **************************************/
				// ���������Ϳͻ���������Ϣ
			case Message.SERVER_TO_CLIENT_CHAT_MESSAGE:
				// ��ȡuserInfoLen
				is.read(buf4);
				int userInfoLen = Util.bytes2Int(buf4);
				// ��ȡuserInfo
				byte[] bufUserInfo = new byte[userInfoLen];
				is.read(bufUserInfo);
				String strUserInfo = new String(bufUserInfo);

				// ��ȡchat����
				is.read(buf4);
				chatLen = Util.bytes2Int(buf4);

				// ��ȡchat����
				chatBuf = new byte[chatLen];
				is.read(chatBuf);
				String strChat = new String(chatBuf);

				// �½��ַ�����������û���Ϣ����������
				return new ServerChatsMessage(new String[] { strUserInfo,
						strChat });

				// ���������͵Ŀͻ���˽����Ϣ
			case Message.SERVER_TO_CLIENT_CHAT_MESSAGE_SINGLE:
				// ��ȡuserInfoLen
				is.read(buf4);
				int senderuserInfoLen = Util.bytes2Int(buf4);
				// ��ȡuserInfo
				byte[] senderbufUserInfo = new byte[senderuserInfoLen];
				is.read(senderbufUserInfo);
				// senderInfo
				String strSenderUserInfo = new String(senderbufUserInfo);

				// ��ȡchat����
				is.read(buf4);
				chatLen = Util.bytes2Int(buf4);

				// ��ȡchat����
				chatBuf = new byte[chatLen];
				is.read(chatBuf);
				String strSenderChat = new String(chatBuf);

				// �½��ַ�����������û���Ϣ����������
				return new ServerChatSingleMessage(new ClientSingleBean(
						strSenderUserInfo, strSenderChat));

				// ���������͸��ͻ��˵ĺ����б�
			case Message.SERVER_TO_CLIENT_FRIEND_LIST:
				// ��ȡ�б���������
				is.read(buf4);
				int listLen = Util.bytes2Int(buf4);
				// ��ȡ�����б�����
				byte[] friendBuf = new byte[listLen];
				is.read(friendBuf);

				// �����б�
				List<String> friendList = (List<String>) Util
						.deserialize(friendBuf);
				return new ServerFriendMessage(friendList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
