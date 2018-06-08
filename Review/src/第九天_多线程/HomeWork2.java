package 第九天_多线程;

public class HomeWork2 {
	public static void main(String[] args) {
		Box box = new Box();
		for (int i = 1; i <= 50; i++) {
			new heshang((String) "和尚" + i, 0, box).start();
		}
	}

}

class heshang extends Thread {// 人来判断是否去取馒头
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
				System.out.println(name + " 已经吃了 " + i + " 个馒头 " + " 还剩 "
						+ res + " 个馒头");
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