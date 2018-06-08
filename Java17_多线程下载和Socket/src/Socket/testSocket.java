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
	 * 创建ServerSocket
	 */

	@Test
	public void test1() throws Exception{
		//创建服务器socket
		//byte[] addr1 = new byte[]{(byte)127,(byte)0,(byte)0,1};
		byte[] addr2 = new byte[]{(byte)192,(byte)168,2,(byte)178};
//		InetAddress addr = InetAddress.getByAddress(addr2);
//		ServerSocket ss= new ServerSocket(8888,50,addr);
		//创建serversocket对象
		ServerSocket ss = new ServerSocket();
		InetSocketAddress adr1 = new InetSocketAddress(InetAddress.getByAddress(addr2),8888);
		ss.bind(adr1);
		//接受请求
		while (true) {
			Socket s = ss.accept();
			//本地地址
			InetSocketAddress localAddr = (InetSocketAddress)s.getLocalSocketAddress();
			System.out.println("local = "+ localAddr.getHostName() + " : " +localAddr.getPort()); 
			//远程地址
			InetSocketAddress remoteAddr = (InetSocketAddress)s.getRemoteSocketAddress();
			System.out.println("remote = "+ remoteAddr.getHostName());
		}
		//System.out.println("over");
	}
	/**
	 * 测试客户端socket
	 */
	@Test
	public void testClient() throws Exception {
		Socket s= new Socket("192.168.2.178",8888);
		
		System.out.println("连上了");
		while (true) {
			Thread.sleep(5000);
		}
	}
	/**
	 * 服务器Socket
	 */
	public void testServerSocket2() {
		
		
	}

}
