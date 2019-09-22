package 第十五天_归档解归档;

import java.util.List;

import org.junit.Test;

public class testArchiveier {
	/**
	 * 测归档
	 */
	@Test
	public void test() {
		Archiveier a = new Archiveier();
		String archFile = "d:/arch/my.xar";
		String txt = "d:/arch/a.txt";
		String png = "d:/arch/b.png";
		a.archive(archFile, png, txt);
	}

	/**
	 * 测解档
	 */
	@Test
	public void testUnArchive() {
		Archiveier a = new Archiveier();
		String archfile = "d:/arch/my.xar";
		a.unarchive(archfile, "d:/arch/unarch");
	}

	/**
	 * 追加归档文件
	 */
	@Test
	public void testAppendFileArchive() {
		Archiveier a = new Archiveier();
		String archfile = "d:/arch/my.xar";// 被追加的文档
		a.appendFile(archfile, "d:/arch/a.txt"/**/);
	}

	/**
	 * 测归档文件内所有文件名字,预览未解压文件
	 */
	@Test
	public void testArchFileNames() {
		Archiveier a = new Archiveier();
		String archfile = "d:/arch/my.xar";
		List<String> namesList = a.getAllFileNames(archfile);
		for (String name : namesList) {
			System.out.println(name);
		}

	}

}
