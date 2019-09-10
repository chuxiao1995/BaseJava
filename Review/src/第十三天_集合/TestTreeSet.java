package ��ʮ����_����;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TestTreeSet {
	@Test
	public void testAdd() {
		Set<Dog> set = new TreeSet<Dog>(new DogComparator());
		set.add(new Dog("���1"));
		set.add(new Dog("���2"));
		set.add(new Dog("���3"));
		set.add(new Dog("���4"));

		System.out.println(set.size());
		System.out.println("���".compareTo("����"));
//		System.out.println((int) '��');
//		System.out.println((int) '��');
	}

	/**
	 * Dog�Ա���
	 *
	 */
	class DogComparator implements Comparator<Dog> {
		public int compare(Dog o1, Dog o2) {
			if (o1 == null) {
				if (o2 == null) {
					return 0;

				} else {
					return -1;
				}

			} else {
				if (o2 == null) {
					return 1;
				} else {
					return o1.name.compareTo(o2.name);
				}

			}

		}
	}

	class Dog /*implements Comparable<Dog>*/ {
		private String name = "";

		public Dog(String name) {

			super();
			this.name = name;
		}

//		public int compareTo(Dog o) {
//			if (o == null) {
//				return 1;
//
//			}
//			return this.name.compareTo(o.name);
//
//		}
	}

}
