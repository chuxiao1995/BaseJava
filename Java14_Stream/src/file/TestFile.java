package file;

import java.io.File;

import org.junit.Test;

public class TestFile {
    @Test
    public void test1() throws Exception {
        File f = new File("e:/XMPp4/pp");
//	   System.out.println(f.exists());
//	   System.out.println(f.isDirectory());
//	   System.out.println(f.isFile());

        System.out.println(f.mkdirs()); //����һϵ��Ƕ���ļ���;
        System.out.println(f.mkdir()); //ֻ�ܴ��������ļ���;
        //f.createNewFile();

//	   f = new File("d:/");
//	   File[] files = f.listFiles();
//	   for (File file : files) {//����Ŀ¼�¶���
//		System.out.println(file.getName());
//		   System.out.println(file.isDirectory());
//		System.out.println(file.getCanonicalPath());//�õ�����·����
//		System.out.println(file.getAbsolutePath());//����·��
//	}
//	   File[] roots = File.listRoots();
//	   for (File file2 : roots) {
//		   System.out.println(file2.isDirectory());
//		   System.out.println(file2.getAbsolutePath());
//	   }
    }

}
