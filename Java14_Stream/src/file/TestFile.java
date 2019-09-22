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

        System.out.println(f.mkdirs()); //创建一系列嵌套文件夹;
        System.out.println(f.mkdir()); //只能创建单层文件夹;
        //f.createNewFile();

//	   f = new File("d:/");
//	   File[] files = f.listFiles();
//	   for (File file : files) {//遍历目录下对象
//		System.out.println(file.getName());
//		   System.out.println(file.isDirectory());
//		System.out.println(file.getCanonicalPath());//得到正规路径名
//		System.out.println(file.getAbsolutePath());//绝对路径
//	}
//	   File[] roots = File.listRoots();
//	   for (File file2 : roots) {
//		   System.out.println(file2.isDirectory());
//		   System.out.println(file2.getAbsolutePath());
//	   }
    }

}
