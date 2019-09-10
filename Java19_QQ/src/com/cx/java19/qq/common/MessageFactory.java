package com.cx.java19.qq.common;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.cx.java19.qq.util.Util;

/**
 * 工厂类，组装客户端和服务器端消息
 * 
 * @author 86152
 *
 */
public class MessageFactory {
	/**
	 * 组装客户端发送的群聊消息
	 */
	public static byte[] popCientChatsMessage(String msg) {
		return popMessage(Message.CLIENT_TO_SERVER_CHATS, msg.getBytes());
	}

	/**
	 * 组装客户端发送的私聊消息 receiverUserInfo 消息接收者消息 msg需要发送的信息
	 */
	public static byte[] popCientChatSingleMessage(String receiverUserInfo,
			String msg) {
		// 接受者信息数组
		byte[] recv = receiverUserInfo.getBytes();
		// 消息数组
		byte[] chat = msg.getBytes();
		// 组装报文
		byte[] pack = new byte[4 + 4 + recv.length + 4 + chat.length];
		// 1消息类型
		System.arraycopy(Util.int2Bytes(Message.CLIENT_TO_SERVER_CHAT), 0,
				pack, 0, 4);

		// 2接受者信息长度
		System.arraycopy(Util.int2Bytes(recv.length), 0, pack, 4, 4);
		// 3接受者信息
		System.arraycopy(recv, 0, pack, 8, recv.length);
		// 4消息长度
		System.arraycopy(Util.int2Bytes(chat.length), 0, pack, 8 + recv.length,
				4);
		// 5消息内容
		System.arraycopy(chat, 0, pack, 8 + recv.length + 4, chat.length);
		return pack;
	}

	/**
	 * 组装客户端发送的刷新好友列表消息
	 */
	public static byte[] popCientRefreshMessage() {
		return Util.int2Bytes(Message.CLIENT_TO_SERVER_REFRESH_FRIENDS);
	}

	/**
	 * 组装服务器端好友列表消息
	 */
	public static byte[] popServerFriends(List<String> list) {
		byte[] objArr = Util.serialize((Serializable) list);
		return popMessage(Message.SERVER_TO_CLIENT_FRIEND_LIST, objArr);
	}

	/**
	 * 组装服务器端转发的消息-群聊消息
	 * -----------------------------------
	 *  |		|		|		|		|	4字节 --> 消息类型
	 * -----------------------------------
	 *  |		|		|		|		|	4---->userInfo长度
	 * ------------------------------------
	 * |..................................| n---->userInfo内容
	 * ------------------------------------
	 *  |		|		|		|		|	4--->消息长度
	 * ------------------------------------
	 *  |..................................|n--->消息内容
	 * ------------------------------------
	 */
	public static byte[] popServerChatsMessage(String userInfo,
			byte[] clientChatMsg) {
		// 用户信息数组
		byte[] userInfoArr = userInfo.getBytes();

		// 总消息长度
		byte[] allArr = new byte[4 + 4 + userInfoArr.length + 4
				+ clientChatMsg.length];
		// 消息类型
		System.arraycopy(Util.int2Bytes(Message.SERVER_TO_CLIENT_CHAT_MESSAGE),
				0, allArr, 0, 4);
		// 用户信息长度
		System.arraycopy(Util.int2Bytes(userInfoArr.length), 0, allArr, 4, 4);
		// 用户信息内容
		System.arraycopy(userInfoArr, 0, allArr, 8, userInfoArr.length);
		// 聊天内容长度
		System.arraycopy(Util.int2Bytes(clientChatMsg.length), 0, allArr,
				(8 + userInfoArr.length), 4);
		// 聊天内容
		System.arraycopy(clientChatMsg, 0, allArr,
				(8 + userInfoArr.length + 4), clientChatMsg.length);
		return allArr;
	}

	/**
	 * 组装服务器端私聊消息
	 * 
	 * @param userInfo
	 * @param ChatMsg
	 *            : pure message
	 * @return
	 */
	public static byte[] popServerChatSingleMessage(String senderUserInfo,
			byte[] chatMsg) {
		// 用户信息数组
		byte[] senderInfo = senderUserInfo.getBytes();

		// 总消息长度
		byte[] pack = new byte[4 + 4 + senderInfo.length + 4 + chatMsg.length];
		// 1消息类型
		System.arraycopy(
				Util.int2Bytes(Message.SERVER_TO_CLIENT_CHAT_MESSAGE_SINGLE),
				0, pack, 0, 4);
		// 2目标用户信息长度
		System.arraycopy(Util.int2Bytes(senderInfo.length), 0, pack, 4, 4);
		// 3目标用户信息内容
		System.arraycopy(senderInfo, 0, pack, 8, senderInfo.length);
		// 4聊天内容长度
		System.arraycopy(Util.int2Bytes(chatMsg.length), 0, pack,
				(8 + senderInfo.length), 4);
		// 5聊天内容
		System.arraycopy(chatMsg, 0, pack, (8 + senderInfo.length + 4),
				chatMsg.length);
		return pack;
	}

	/**
	 * 组装一般性消息
	 * 
	 */
	private static byte[] popMessage(int msgType, byte[] msgArr) {
		// 消息长度
		byte[] msglenArr = Util.int2Bytes(msgArr.length);
		// 消息类型
		byte[] type = Util.int2Bytes(msgType);
		// 总消息
		byte[] allArr = new byte[type.length + msglenArr.length + msgArr.length];
		// 复制组装消息到all
		System.arraycopy(type, 0, allArr, 0, type.length);
		System.arraycopy(msglenArr, 0, allArr, type.length, msglenArr.length);
		System.arraycopy(msgArr, 0, allArr, (type.length + msglenArr.length),
				msgArr.length);
		return allArr;
	}

	/**
	 * 从输入流中解析消息
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
			// 判断消息类型
			switch (type) {

			/************************************ 服务器解析部分 **************************************/
			// 请求刷新好友
			case Message.CLIENT_TO_SERVER_REFRESH_FRIENDS:
				return new ClientRequestFreshFriendsMessage();

				// 客户端发送群聊消息
			case Message.CLIENT_TO_SERVER_CHATS:
				// 读取消息长度
				is.read(buf4);
				int msgLen = Util.bytes2Int(buf4);
				// 消息内容
				byte[] msgBuf = new byte[msgLen];
				is.read(msgBuf);
				// 客户端发送的聊天消息(纯内容消息)
				return new ClientChatsMessage(msgBuf);

				// 客户端私聊消息
			case Message.CLIENT_TO_SERVER_CHAT:
				is.read(buf4);
				int recvInfoLen = Util.bytes2Int(buf4);
				// 接收内容
				byte[] recvBuf = new byte[recvInfoLen];
				is.read(recvBuf);
				String recvInfo = new String(recvBuf);

				is.read(buf4);
				int chatLen = Util.bytes2Int(buf4);
				byte[] chatBuf = new byte[chatLen];
				is.read(chatBuf);
				// 服务器端私聊
				return new ClientChatSingleMessage(new ServerSingleBean(
						recvInfo, chatBuf));

				/*********************************** 客户端解析部分 **************************************/
				// 服务器发送客户端聊天信息
			case Message.SERVER_TO_CLIENT_CHAT_MESSAGE:
				// 读取userInfoLen
				is.read(buf4);
				int userInfoLen = Util.bytes2Int(buf4);
				// 读取userInfo
				byte[] bufUserInfo = new byte[userInfoLen];
				is.read(bufUserInfo);
				String strUserInfo = new String(bufUserInfo);

				// 读取chat长度
				is.read(buf4);
				chatLen = Util.bytes2Int(buf4);

				// 读取chat内容
				chatBuf = new byte[chatLen];
				is.read(chatBuf);
				String strChat = new String(chatBuf);

				// 新建字符串数组包含用户信息和聊天内容
				return new ServerChatsMessage(new String[] { strUserInfo,
						strChat });

				// 服务器发送的客户端私聊信息
			case Message.SERVER_TO_CLIENT_CHAT_MESSAGE_SINGLE:
				// 读取userInfoLen
				is.read(buf4);
				int senderuserInfoLen = Util.bytes2Int(buf4);
				// 读取userInfo
				byte[] senderbufUserInfo = new byte[senderuserInfoLen];
				is.read(senderbufUserInfo);
				// senderInfo
				String strSenderUserInfo = new String(senderbufUserInfo);

				// 读取chat长度
				is.read(buf4);
				chatLen = Util.bytes2Int(buf4);

				// 读取chat内容
				chatBuf = new byte[chatLen];
				is.read(chatBuf);
				String strSenderChat = new String(chatBuf);

				// 新建字符串数组包含用户信息和聊天内容
				return new ServerChatSingleMessage(new ClientSingleBean(
						strSenderUserInfo, strSenderChat));

				// 服务器发送给客户端的好友列表
			case Message.SERVER_TO_CLIENT_FRIEND_LIST:
				// 读取列表缓存区长度
				is.read(buf4);
				int listLen = Util.bytes2Int(buf4);
				// 读取好友列表内容
				byte[] friendBuf = new byte[listLen];
				is.read(friendBuf);

				// 好友列表
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
