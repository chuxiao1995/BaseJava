package 第十三天_集合;

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

	public int byteArr2Int(byte[] arr) {// byte转int，前面的空位补1
		// 另一种方法，先&0xffs后移位，先扩充位数，转换过程则不会补1
		int i0 = arr[0] << 24;
		int i1 = (arr[1] << 16) & 0x00ffffff;
		int i2 = (arr[2] << 8) & 0x0000ffff;
		int i3 = arr[3] & 0x000000ff;
		return i0 | i1 | i2 | i3;//或运算

	}

	private void outArr(byte[] arr) {// 输出
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] + " ");
		}
		System.out.println();

	}

	public String toHexString(int x) {// 转换成16进制串
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
