package �ھ���_���߳�;

public class HomeWork2 {
	public static void main(String[] args) {
		Box box = new Box();
		for (int i = 1; i <= 50; i++) {
			new heshang((String) "����" + i, 0, box).start();
		}
	}

}

class heshang extends Thread {// �����ж��Ƿ�ȥȡ��ͷ
	String name;
	int i;
	Box box;

	public heshang(String name, int i, Box b) {
		this.name = name;
		this.i = i;
		this.box = b;
	}

	public void run() {
		while (i < 3 && box.BOXNUM > 0) {
				int res = box.getMantou();
				i += 1;
				System.out.println(name + " �Ѿ����� " + i + " ����ͷ " + " ��ʣ "
						+ res + " ����ͷ");
				try {
					sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				}
		}
	}


class Box {
	int BOXNUM = 100;
	int res;

	public synchronized int getMantou() {
		int res = BOXNUM - 1;
		BOXNUM--;
		return res;
	}
}