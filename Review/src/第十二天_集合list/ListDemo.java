package ��ʮ����_����list;

import java.util.ArrayList;
import java.util.List;

import ��ʮ����_����list.Cat;
class ListDemo {
	public static void main(String[] args) {

		List<Cat> list = new ArrayList<Cat>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			list.add(new Cat("Tom" + i, i));
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}

