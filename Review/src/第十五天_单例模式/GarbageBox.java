package ��ʮ����_����ģʽ;

/**
 * ����ģʽ�������� ������̬
 */
public class GarbageBox {
	// ʵ��������ʽ
	private static GarbageBox instance;
	private static Object lock = new Object();

	// Object object = new Object();
	/**
	 * ����˽��
	 */
	private GarbageBox() {

	}

	public static GarbageBox getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new GarbageBox();
				}
				return instance;
			}
		}
		return instance;

	}
}
