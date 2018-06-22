package 第十五天_单例模式;

/**
 * 单例模式，垃圾箱 两种形态
 */
public class GarbageBox {
	// 实例，懒汉式
	private static GarbageBox instance;
	private static Object lock = new Object();

	// Object object = new Object();
	/**
	 * 构造私有
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
