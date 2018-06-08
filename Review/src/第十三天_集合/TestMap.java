package 第十三天_集合;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class TestMap {
	public void tets1() {
		// Map<String, String> map = new Map<String, String>();

	}

	/**
	 * map的遍历
	 */
	@Test
	public void testReadAccess2() {
		// 创建map集合
		Map<String, String> map = new HashMap<String, String>();
		map.put("key001", "tom1");
		String string = map.put("key001", "tom2");
		map.put("key002", "tom1");
		map.put("key003", "tom4");

		String value1 = map.get("key001");
		System.out.println(string + value1);// 返回被覆盖的value，和当前对象的value

		Set<Entry<String, String>> entrySet = map.entrySet();// 泛型的嵌套
		Iterator<Entry<String, String>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			entry.getKey();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + " = " + value);

		}

	}

	/**
	 * KeySet的遍历
	 */
	@Test
	public void testReadAccess3() {
		// 创建map集合
		Map<String, String> map = new HashMap<String, String>();
		map.put("key001", "tom1");
		map.put("key002", "tom1");
		map.put("key003", "tom4");

		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " = " + value);

		}

	}

}

// final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
// boolean evict) {
// Node<K,V>[] tab; Node<K,V> p; int n, i;
// if ((tab = table) == null || (n = tab.length) == 0)//判断桶是不是空的
// n = (tab = resize()).length;//初始化
// if ((p = tab[i = (n - 1) & hash]) == null)//找到存储桶的位置i，若改桶层为空，直接创建 node
// tab[i] = newNode(hash, key, value, null);
// else {//存在元素则比较
// Node<K,V> e; K k;
// if (p.hash == hash && //hash码，内存地址或者equals相等都可以
// ((k = p.key) == key || (key != null && key.equals(k))))
// e = p;//覆盖
// else if (p instanceof TreeNode)
// e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
// else {
// for (int binCount = 0; ; ++binCount) {
// if (( e = p.next//e的赋值 ) == null) {
// p.next = newNode(hash, key, value, null);
// if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
// treeifyBin(tab, hash);
// break;
// }
// if (e.hash == hash &&
// ((k = e.key) == key || (key != null && key.equals(k))))
// break;
// p = e;
// }
// }
// if (e != null) { // existing mapping for key
// V oldValue = e.value;
// if (!onlyIfAbsent || oldValue == null)
// e.value = value;
// afterNodeAccess(e);
// return oldValue;
// }
// }
// ++modCount;
// if (++size > threshold)
// resize();
// afterNodeInsertion(evict);
// return null;
// }