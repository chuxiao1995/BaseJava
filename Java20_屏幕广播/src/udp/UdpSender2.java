package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender2 {
	public static void main(String[] args) {
		try {
			//�����û����ݱ����׽���
			DatagramSocket socket = new DatagramSocket(8888);
			
			//����
			byte[] buf = "hello world".getBytes();
			
			//���ݱ���
			InetAddress addr = InetAddress.getByName("192.168.2.178");
			DatagramPacket pack = new DatagramPacket(buf, buf.length,addr,7777);
			int i = 1 ;
			//�������ݱ���
			while (true) {
				
				pack.setData(("tom"+i).getBytes());
				socket.send(pack);
				System.out.println("tom  :"+i);
				i++;
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
