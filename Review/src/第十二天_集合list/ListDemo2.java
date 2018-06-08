package 第十二天_集合list;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ListDemo2 {
	@Test
	public void testIndexof() {
		int Max = 330;
		Cat obj = null;
		List<Cat> list = new ArrayList<Cat>();
		for (int i = 0; i < Max; i++) {
			if (i == 24) {
				obj = new Cat("tom" + i, i);
				list.add(obj);
			}
			list.add(new Cat("tom" + i, i));

		}
		Cat cat = new Cat("tom24", 24);
		int index = list.indexOf(obj);
		System.out.println(index);
		Cat ccCat = new Cat("tom24", 24);
		System.out.println(ccCat.equals(cat));

	}

}
