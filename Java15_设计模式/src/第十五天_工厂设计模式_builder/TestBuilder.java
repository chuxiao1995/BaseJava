package ��ʮ����_�������ģʽ_builder;

import org.junit.Test;

public class TestBuilder {
	@Test
	public void test1() {
		Phone p = new Phone()
		.setBrand("a")
		.setName("b")
		.setPrice(9.f)
		.setProductArea("shanghai");
		System.out.println(p.getName());
	}

}
