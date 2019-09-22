package day14_IOandCollection;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestArrays {
    @Test
    public void test1() throws UnsupportedEncodingException {
        System.out.println("\ud585\u4e2d");

    }

    /**
     * 集合工具类
     */
    @Test
    public void testColletions() {
        // int[] arr = {3,1,2,5};
        // Arrays.sort(arr);//排序1235
        // for (int i = 0; i < arr.length; i++) {
        // System.out.println(arr[i]);
        //
        // }
        // System.out.println();
        // int index = Arrays.binarySearch(arr, 2);折半查找 1
        // System.out.println(index);
        List<String> list = new LinkedList<String>();
        list.add("jerry");
        list.add("john");
        list.add("bob");
        String string = Collections.max(list);// john
        Collections.reverse(list);// 反转表

    }

    /**
     * 增强for循环
     */

    @Test
    public void testProFor() {
        List<String> list = new ArrayList<String>();
        list.add("jerry");
        list.add("john");
        list.add("bob");

        for (String s : list) {
            System.out.println(s);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "tom1");
        map.put("2", "tom2");
        map.put("3", "tom3");
        map.put("4", "tom4");
        // 迭代entry集合
        // Set<Entry<String,String>> set = map.entrySet();
        // for (Entry<String,String> s : set) {
        // String key = s.getKey();
        // String value = s.getValue();
        // System.out.println(key + " "+ value);
        // }
        Set<String> set = map.keySet();
        for (String s : set) {
            String key = s;
            String value = map.get(s);
            System.out.println(key + " " + value);

        }

    }

    /**
     * 测试变长参数
     */
    @Test
    public void testVarParam() {
        List<String> list = new ArrayList<String>();
        addEle(list, "tom", "tomas", "tomasLee");//不限长
        System.out.println(list.size());
    }

    private void addEle(List<String> list, String... x) {
        for (String s : x) {//增强for循环
            list.add(s);
        }

    }

}
