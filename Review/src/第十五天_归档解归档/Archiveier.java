package ��ʮ����_�鵵��鵵;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * �鵵����ļ���һ���鵵�ļ���
 */
public class Archiveier {
	/**
	 * �鵵����ļ���һ���鵵�ļ���
	 */
	public void archive(String archFile, String... srcFiles) {// �ַ�������

		for (String srcFile : srcFiles) {
			System.out.println(srcFile);
			appendFile(archFile, srcFile);
		}
	}

	public void unarchive(String archFile, String destDir) {
		try {
			FileInputStream fis = new FileInputStream(archFile);
			// ��ȡ�ĸ��ֽڵ��ļ�������
			byte[] fnameLenbuf = new byte[4];
			while ((fis.read(fnameLenbuf)) != -1) {

				int fnameLength = byteArr2Int(fnameLenbuf);

				// ��ȡ�ļ���
				byte[] fnameBuf = new byte[fnameLength];
				fis.read(fnameBuf);
				String fname = new String(fnameBuf);

				// ����ļ����ݳ���
				byte[] fcontBuf = new byte[4];
				fis.read(fcontBuf);
				int flen = byteArr2Int(fcontBuf);

				// ����ļ�����
				FileOutputStream fos = new FileOutputStream(destDir + "/"
						+ fname);
				byte[] buf = new byte[1024];
				int count = 0;
				int mod = flen % buf.length;
				if (mod == 0) {// �����ͷ����������
					count = flen / buf.length;
				} else {
					count = flen / buf.length + 1;
				}
				for (int i = 0; i < count; i++) {// ѭ����ȡ
					if (i == (count - 1)) {// ���һ��
						mod = (mod == 0) ? buf.length : mod;
						fis.read(buf, 0, mod);
						fos.write(buf, 0, mod);
						fos.close();
					} else {
						fis.read(buf);
						fos.write(buf);
					}
				}
			}
			fis.close();

		} catch (Exception e) {
		}
	}

	public List<String> getAllFileNames(String archFile) {// ��������������е��ļ���
		List<String> filenames = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(archFile);
			// ��ȡ�ĸ��ֽڵ��ļ�������
			byte[] fnameLenbuf = new byte[4];
			while ((fis.read(fnameLenbuf)) != -1) {

				int fnameLength = byteArr2Int(fnameLenbuf);

				// ��ȡ�ļ���
				byte[] fnameBuf = new byte[fnameLength];
				fis.read(fnameBuf);
				String fname = new String(fnameBuf);

				// ����ļ����ݳ���
				byte[] fcontBuf = new byte[4];
				fis.read(fcontBuf);
				int flen = byteArr2Int(fcontBuf);
				fis.skip(flen);
				// ����ļ�����
				filenames.add(fname);
			}

			fis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return filenames;
	}

	/**
	 * 1.�����ļ��� 1.1�ļ������� 1.2�ļ����ֽ����� 2.�����ļ����� 2.1���ݳ��� 2.2��������
	 */

	public void appendFile(String archFile, String srcFile) {// ��ӹ鵵�ļ�
		try {
			// �����鵵�ļ������
			FileOutputStream fos = new FileOutputStream(archFile, true);
			// ��ȡ�ļ���
			String fileName = getFileName(srcFile);
			// �洢�ļ������ȵ� int����
			fos.write(int2ByteArr(fileName.length()));
			// �洢�ļ���
			fos.write(fileName.getBytes());
			// System.out.println(fileName.length());
			// �洢�ļ����ݳ���
			FileInputStream fis = new FileInputStream(srcFile);
			fos.write(int2ByteArr(fis.available()));
			// �洢�ļ�����
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getFileName(String srcFile) {// ��������
		File file = new File(srcFile.trim());
		return file.getName();
	}

	/**
	 * 
	 * ������ת�����ֽ�����
	 */
	private byte[] int2ByteArr(int i) {
		byte[] arr = new byte[4];
		arr[0] = (byte) (i >> 24);
		arr[1] = (byte) (i >> 16);
		arr[2] = (byte) (i >> 8);
		arr[3] = (byte) (i >> 0);
		return arr;
	}

	/**
	 * ���ֽ�����ת����int
	 */
	private int byteArr2Int(byte[] arr) {// byteתint��ǰ��Ŀ�λ��1
		// ��һ�ַ�������&0xffs����λ��������λ����ת�������򲻻Ჹ1
		int i0 = arr[0] << 24;
		int i1 = (arr[1] << 16) & 0x00ffffff;
		int i2 = (arr[2] << 8) & 0x0000ffff;
		int i3 = arr[3] & 0x000000ff;
		return i0 | i1 | i2 | i3;// ������

	}

}
