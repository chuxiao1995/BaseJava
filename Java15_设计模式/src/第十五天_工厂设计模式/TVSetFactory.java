package ��ʮ����_�������ģʽ;

public class TVSetFactory {
	/**
	 * ��̬��������
	 */
	public static TVSet producTvSet() {
		TVSet tv = new TVSet();
		tv.setBrand("����");
		tv.setName("xxx");
		tv.setPrice(500.f);
		return tv;
	}

}
