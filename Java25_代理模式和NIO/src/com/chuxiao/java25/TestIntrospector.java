package com.chuxiao.java25;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.junit.Test;
/**
 * 
 * ������ʡ
 *
 */
public class TestIntrospector {

	@Test
	public void test1() throws Exception {
		//�õ�bean��Ϣ
		BeanInfo bi = Introspector.getBeanInfo(Person.class);
		//�õ�����������
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		//
		for(PropertyDescriptor pd : pds){
			//�õ�������
			System.out.println(pd.getName());
			//�õ�getXXX����
			System.out.println(pd.getReadMethod());
			//�õ�setXXX����
			System.out.println(pd.getWriteMethod());
		}
	}
}
