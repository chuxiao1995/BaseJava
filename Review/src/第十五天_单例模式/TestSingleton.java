package ��ʮ����_����ģʽ;

import org.junit.Test;

public class TestSingleton {
	@Test
	public void testSingleton() {
		GarbageBox box = GarbageBox.getInstance();
		System.out.println(box.toString());
		System.out.println(GarbageBox.getInstance());
		System.out.println(GarbageBox.getInstance());
		System.out.println(GarbageBox.getInstance());
	}
	/**
	 * ��ʮ����_����ģʽ.GarbageBox@299e6e0
                 ��ʮ����_����ģʽ.GarbageBox@262bd2d
                 ���ֵĽ����ԭ����...

	 */
	@Test
	public void testSingleton2() {
		for (int i = 0; i < 30; i++) {
			new Thread(){
				public void run() {
					GarbageBox box = GarbageBox.getInstance();
					System.out.println(box);
				}
			}.start();
		}
		
		
	}

}
