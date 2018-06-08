package 第十五天_单例模式;

class GarbageBox2 {
	//实例，饿汉式
	private static GarbageBox2 instance = new GarbageBox2();//类加载就获取对象
	/**
	 * 构造私有
	 */
	private GarbageBox2(){
		
	}
	public static GarbageBox2 getInstance() {
		return instance;
	}
	
}