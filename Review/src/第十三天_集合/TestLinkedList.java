package ��ʮ����_����;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

public class TestLinkedList {

	@Test
	public void test2() {
		int Max = 500000;
		List<Integer> list1 = new ArrayList<Integer>();
		for (int i = 0; i < Max; i++) {
			list1.add(i);
		}
		List<Integer> list2 = new LinkedList<Integer>();
		for (int i = 0; i < Max; i++) {
			list2.add(i);
		}
		long start = System.nanoTime();
		list1.get(Max / 2);// Arraylist ����ָ��Ԫ�� 15150
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		list2.get(Max / 2);// linkedlist ����ָ��Ԫ�� 2721227
		System.out.println(System.nanoTime() - start);

	}

	/**
	 * ����LinkedList����
	 */
	@Test
	public void testotherways() {
		/*
		 * List<Integer> list = new LinkedList<Integer>(); boolean a =
		 * list.add(1);//���ز���ֵ list.clear(); System.out.println(list.size());
		 * list.remove(0);
		 */
		List<String> list = new LinkedList<String>();
		list.add(new String("tom1"));
		list.add(new String("tom2"));
		list.add(new String("tom3"));
		list.add(new String("tom4"));
		System.out.println(list.size());
		//list.remove("tom1");
		list.add(new String("tom1"));//��������ظ�ֵ
		System.out.println(list.size());

	}
	
/*	�������unlink
 * E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }*/

}
