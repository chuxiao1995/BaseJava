package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * ������
 * @author 86152
 *
 */
public class udpReceiver {
	public static void main(String[] args) {
		try {
			//�����û����ݱ����׽���
			DatagramSocket socket = new DatagramSocket(7777);
			byte[] buf = new byte[1024*1024];
			DatagramPacket pack = new DatagramPacket(buf, buf.length);
			//������
			socket.receive(pack);
			int len = pack.getLength();
			String str = new String(buf,0,len);
			System.out.println("received data :" + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
