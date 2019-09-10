package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender {
	public static void main(String[] args) {
		try {
			//�����û����ݱ����׽���
			DatagramSocket socket = new DatagramSocket(8888);
			
			//����
			byte[] buf = "hello world".getBytes();
			
			//���ݱ���
			InetAddress addr = InetAddress.getByName("192.168.2.178");
			DatagramPacket pack = new DatagramPacket(buf, buf.length,addr,9999);
			
			//�������ݱ���
			socket.send(pack);
			System.out.println("send over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
