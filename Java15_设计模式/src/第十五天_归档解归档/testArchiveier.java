package ��ʮ����_�鵵��鵵;

import java.util.List;

import org.junit.Test;

public class testArchiveier {
	/**
	 * ��鵵
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
	 * ��⵵
	 */
	@Test
	public void testUnArchive() {
		Archiveier a = new Archiveier();
		String archfile = "d:/arch/my.xar";
		a.unarchive(archfile, "d:/arch/unarch");
	}

	/**
	 * ׷�ӹ鵵�ļ�
	 */
	@Test
	public void testAppendFileArchive() {
		Archiveier a = new Archiveier();
		String archfile = "d:/arch/my.xar";// ��׷�ӵ��ĵ�
		a.appendFile(archfile, "d:/arch/a.txt"/**/);
	}

	/**
	 * ��鵵�ļ��������ļ�����,Ԥ��δ��ѹ�ļ�
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
