package ��ʮ����_�������ģʽ;

import org.junit.Test;

public class testFactory {
@Test
	public void test() {
		TVSet tv = TVSetFactory.producTvSet();
		System.out.println(tv.getBrand());
	}
}
