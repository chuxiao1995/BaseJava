package 第十三天_集合;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

//public class HomeWork<E> extends LinkedList<E>{
//	transient Node<E> last;
//	  transient Node<E> first;
//	  transient int size = 0;
//	@Test
//	public void makeCycle() {
//		List<Integer> cycle = new LinkedList<Integer>();
//		cycle.add(1);
//		cycle.add(2);
//		cycle.add(3);
//		cycle.add(4);
//		MylinkLast((E) new Integer(5));
////		Iterator<Integer> it = cycle.iterator();
////		while (it.hasNext()) {
////			int integer = (Integer) it.next();
////			System.out.println(integer);
////		}
//		
//	}
//	 private static class Node<E> {
//	        E item;
//	        Node<E> next;
//	        Node<E> prev;
//
//	        Node(Node<E> prev, E element, Node<E> next) {
//	            this.item = element;
//	            this.next = next;
//	            this.prev = prev;
//	        }
//	    }
//	 
//	 void MylinkLast(E e) {
//		 final Node<E> l = last;
//	        final Node<E> newNode = new Node<>(l, e, first);
//	        last = newNode;
//	        if (l == null)
//	            first = newNode;
//	        else
//	            l.next = newNode;
//	        size++;
//	        modCount++;
//	    
//	    }}

