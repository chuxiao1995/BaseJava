package ��ʮ����_����;

import org.junit.Test;

public class Converter {
	@Test
	public void test() {
		int i = 1;
		outArr(int2ByteArray(i));
		
		System.out.println((new Converter()).toHexString(i));

	}

	public byte[] int2ByteArray(int i) {
		byte[] arr = new byte[4];
		arr[0] = (byte) (i >> 24);
		arr[1] = (byte) (i >> 16);
		arr[2] = (byte) (i >> 8);
		arr[3] = (byte) i;
		return arr;
	}

	public int byteArr2Int(byte[] arr) {// byteתint��ǰ��Ŀ�λ��1
		// ��һ�ַ�������&0xffs����λ��������λ����ת�������򲻻Ჹ1
		int i0 = arr[0] << 24;
		int i1 = (arr[1] << 16) & 0x00ffffff;
		int i2 = (arr[2] << 8) & 0x0000ffff;
		int i3 = arr[3] & 0x000000ff;
		return i0 | i1 | i2 | i3;//������

	}

	private void outArr(byte[] arr) {// ���
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] + " ");
		}
		System.out.println();

	}

	public String toHexString(int x) {// ת����16���ƴ�
		char[] arr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		StringBuilder s = new StringBuilder();
		int y = x;
		if (y < 0) {
			y = -x;
			s.append("-0x");
		}
		s.append("0x");
		for (int i = 7; i >= 0; i--) {
			int temp = (y >> (i * 4) & 0xf);
			s.append(arr[temp]);

		}
		return s.toString();

	}

}
