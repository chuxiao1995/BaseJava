package 第十五天_单例模式;

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
	 * 第十五天_单例模式.GarbageBox@299e6e0
                 第十五天_单例模式.GarbageBox@262bd2d
                 出现的结果，原因是...

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
