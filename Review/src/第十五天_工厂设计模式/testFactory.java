package 第十五天_工厂设计模式;

import org.junit.Test;

public class testFactory {
@Test
	public void test() {
		TVSet tv = TVSetFactory.producTvSet();
		System.out.println(tv.getBrand());
	}
}
