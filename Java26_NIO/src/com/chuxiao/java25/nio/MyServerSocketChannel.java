package com.chuxiao.java25.nio;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Server.NIO�����Ƿ������� Selector SelectSocketChannel SocketChannel
 */
public class MyServerSocketChannel {

	public static void main(String[] args) throws Exception {
		// ����ѡ��
		Selector sel = Selector.open();
		// ��ServerChannel��������ͨ������
		ServerSocketChannel ssc = ServerSocketChannel.open();
		// �����󶨵�ַ
		InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 8888);
		// �󶨵�ַ
		ssc.bind(addr);
		// ���÷�����ģʽ
		ssc.configureBlocking(false);
		// ����ѡ����ע��ͨ����ָ������Ȥ���¼�
		ssc.register(sel, SelectionKey.OP_ACCEPT);
		SocketChannel sc0 = null;
		ByteBuffer buf = null;
		while (true) {
			// ��ʼ��ѡ
			try {
				sel.select();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// ���������¼�
			Set<SelectionKey> keys = sel.selectedKeys();
			for (SelectionKey key : keys) {
				try {
					if (key.isAcceptable()) {
						// �õ�������ͨ��
						ServerSocketChannel ssc0 = (ServerSocketChannel) key
								.channel();
						// ���ܿͻ������ӣ�����SocketChannel
						sc0 = ssc0.accept();
						System.out.println(getClientInfo(sc0.socket())+ " : ������");
						//ע�������
						sc0.configureBlocking(false);
						//����ѡ����ע���²�����SocketChannel
						sc0.register(sel, SelectionKey.OP_READ
								| SelectionKey.OP_WRITE
								| SelectionKey.OP_CONNECT);
					}
					if (key.isReadable()) {
						// �õ�SocketChannel
						sc0 = (SocketChannel) key.channel();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						//
						buf = ByteBuffer.allocate(1024);
						// ��ȡ�ͻ��˷��͵�����
						while (sc0.read(buf) != 0) {
							buf.flip();
							baos.write(buf.array(), 0, buf.limit());
							buf.clear();
						}
						// ���컺������д��ȥclient
						String str = "hello : "
								+ new String(baos.toByteArray());
						// �������
						System.out.println(getClientInfo(sc0.socket()) + str);
						buf = ByteBuffer.allocate(str.getBytes().length);
						buf.put(str.getBytes());
						buf.flip();
						sc0.write(buf);
						buf.clear();
					}
				} catch (Exception e) {
					// ��selectorע��ͨ��
					key.cancel();
					Socket s = ((SocketChannel) key.channel()).socket();
					System.out.println(getClientInfo(s) + " : ������");

				}
			}
			keys.clear();
		}
	}

	/**
	 * 
	 * ��ÿͻ�����Ϣ
	 */
	private static String getClientInfo(Socket socket) {
		InetSocketAddress addr = (InetSocketAddress) socket
				.getRemoteSocketAddress();
		String ip = addr.getAddress().getHostAddress();
		String port = addr.getPort() + "";
		return "[" + ip + " : " + port + "]";

	}
}
