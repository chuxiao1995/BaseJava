package ��ʮ����_����ģʽ;

class GarbageBox2 {
	//ʵ��������ʽ
	private static GarbageBox2 instance = new GarbageBox2();//����ؾͻ�ȡ����
	/**
	 * ����˽��
	 */
	private GarbageBox2(){
		
	}
	public static GarbageBox2 getInstance() {
		return instance;
	}
	
}