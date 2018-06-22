package 第十四天_IO和集合嵌套;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestMap {
	@Test
	public void testNestedMap() {
		// 小区。楼栋编号 单元编号
		Map<Integer, Map<String, List<String>>> allBuildingMap = new HashMap<Integer, Map<String, List<String>>>();
		// Integer 楼号 String 单元 String 户名
		for (int i = 0; i < 10; i++) {
			Map<String, List<String>> allCells = new HashMap<String, List<String>>();
			for (int j = 1; j <= 5; j++) {
				List<String> allNames = new ArrayList<String>();
				for (int k = 1; k <= 20; k++) {
					allNames.add("tom" + i + "." + j + "." + k);
				}
				allCells.put(i + "." + j, allNames);
			}
			allBuildingMap.put(i, allCells);
		}
		System.out.println("kk");
/*
 *   增强for循环遍历
 */
		Set<Integer> set = allBuildingMap.keySet();
		for (Integer allbuidings : set) {
			Map<String, List<String>> allCells = allBuildingMap.get(allbuidings);
			Set<String> allCellsSet = allCells.keySet();
			for (String CellName : allCellsSet) {
				List<String> usrnameList = allCells.get(CellName);
				for (String usrname : usrnameList) {
					System.out.println(usrname);
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
