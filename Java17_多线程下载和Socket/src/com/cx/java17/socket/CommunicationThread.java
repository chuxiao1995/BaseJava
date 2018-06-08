package com.cx.java17.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 
 * ͨ���߳�
 *
 */
public class CommunicationThread extends Thread {
	private Socket socket;
	private String clientInfo;

	public CommunicationThread(Socket socket) {
		super();
		this.socket = socket;
		this.clientInfo = getClientInfo();
	}

	public void run() {
		System.out.println("�������ˣ�");

		try {
			// ��������
			InputStream is = socket.getInputStream();//��ȡ����������ȡ�ͻ�������
			OutputStream os = socket.getOutputStream();//��ȡ������ش���client
			InputStreamReader reader = new InputStreamReader(is);
			char[] buf = new char[1024];
			int len = 0;
			while ((len = reader.read(buf)) != -1) {
				String msg = new String(buf, 0, len);

				System.out.println("client " +clientInfo+":"+ msg);
				os.write(("[from Teacher] " + msg).getBytes());
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���socket��Զ��client����Ϣ(ip:port)
	 */
	private String getClientInfo() {
		try {
			InetSocketAddress addr = (InetSocketAddress) socket.getRemoteSocketAddress();
			String ip = addr.getAddress().getHostAddress();
			int port = addr.getPort();
			return ip + " : "+ port;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
