package Socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 
 * @author 86152
 *
 */
public class testSocket {
	/**
	 * ����ServerSocket
	 */

	@Test
	public void test1() throws Exception{
		//����������socket
		//byte[] addr1 = new byte[]{(byte)127,(byte)0,(byte)0,1};
		byte[] addr2 = new byte[]{(byte)192,(byte)168,2,(byte)178};
//		InetAddress addr = InetAddress.getByAddress(addr2);
//		ServerSocket ss= new ServerSocket(8888,50,addr);
		//����serversocket����
		ServerSocket ss = new ServerSocket();
		InetSocketAddress adr1 = new InetSocketAddress(InetAddress.getByAddress(addr2),8888);
		ss.bind(adr1);
		//��������
		while (true) {
			Socket s = ss.accept();
			//���ص�ַ
			InetSocketAddress localAddr = (InetSocketAddress)s.getLocalSocketAddress();
			System.out.println("local = "+ localAddr.getHostName() + " : " +localAddr.getPort()); 
			//Զ�̵�ַ
			InetSocketAddress remoteAddr = (InetSocketAddress)s.getRemoteSocketAddress();
			System.out.println("remote = "+ remoteAddr.getHostName());
		}
		//System.out.println("over");
	}
	/**
	 * ���Կͻ���socket
	 */
	@Test
	public void testClient() throws Exception {
		Socket s= new Socket("192.168.2.178",8888);
		
		System.out.println("������");
		while (true) {
			Thread.sleep(5000);
		}
	}
	/**
	 * ������Socket
	 */
	public void testServerSocket2() {
		
		
	}

}
