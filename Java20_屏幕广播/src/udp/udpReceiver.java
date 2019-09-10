package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * 接收者
 * @author 86152
 *
 */
public class udpReceiver {
	public static void main(String[] args) {
		try {
			//创建用户数据报包套接字
			DatagramSocket socket = new DatagramSocket(7777);
			byte[] buf = new byte[1024*1024];
			DatagramPacket pack = new DatagramPacket(buf, buf.length);
			//阻塞的
			socket.receive(pack);
			int len = pack.getLength();
			String str = new String(buf,0,len);
			System.out.println("received data :" + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
