package main.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;

public class MyClient {
	@Test
	public void send() {
		try {
			Socket s = new Socket("localhost",8888);
			//��������
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line =null;
			byte[] buf = new byte[1024];
			int len= -1;
			while ((line = reader.readLine())!= null) {
				os.write(line.getBytes());
				len = is.read(buf);
				System.out.println(new String(buf,0,len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
