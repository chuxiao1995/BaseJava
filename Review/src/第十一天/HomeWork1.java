package ��ʮһ��;

public class HomeWork1 {//������ÿ���������ٳ�һ����ͷ������
	public static void main(String[] args) {
		Box box = new Box();
		for (int i = 1; i <= 50; i++) {
			new heshang((String) "����" + i, 0, box).start();
		}
	}

}

class heshang extends Thread {
	String name;
	int i;
	Box box;

	public heshang(String name, int i, Box b) {
		this.name = name;
		this.i = i;
		this.box = b;
	}

	public void run() {
		while (box.boxnum > 0) {
			i = box.getMantou(i, name);
		}
		// notifyAll();
		System.out.println(name + " �Ѿ����� " + i + " ����ͷ " + " ��ʣ " + "0����ͷ");
	}
}

class Box {
	int boxnum = 93;

	public synchronized int getMantou(int i, String name) {
		// �ж��Ƿ����ȥȡ��ͷ
		if (i == 1) {
			while (boxnum > 43) {
				try {
					this.wait();
				} catch (InterruptedException e) {
				}
			}
			i += 1;
			boxnum = boxnum - 1;
			this.notify();

			return i;

		}// �ж���ȥ�ȴ����л���������ͷ
		if (i == 0 || i == 2) {
			i += 1;
			boxnum = boxnum - 1;

			notify();
			return i;
		}

		if (i == 3) {

			try {
				notify();
				this.wait();
			} catch (InterruptedException e) {
			}

			return i;
		}

		return i;

	}
}
