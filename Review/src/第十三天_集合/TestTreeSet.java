package 第十三天_集合;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TestTreeSet {
	@Test
	public void testAdd() {
		Set<Dog> set = new TreeSet<Dog>(new DogComparator());
		set.add(new Dog("大黄1"));
		set.add(new Dog("大黄2"));
		set.add(new Dog("大黄3"));
		set.add(new Dog("大黄4"));

		System.out.println(set.size());
		System.out.println("大黄".compareTo("二黄"));
//		System.out.println((int) '大');
//		System.out.println((int) '二');
	}

	/**
	 * Dog对比器
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
