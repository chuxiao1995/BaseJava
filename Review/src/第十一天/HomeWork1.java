package 第十一天;

public class HomeWork1 {//增加了每个和尚至少吃一个馒头的条件
	public static void main(String[] args) {
		Box box = new Box();
		for (int i = 1; i <= 50; i++) {
			new heshang((String) "和尚" + i, 0, box).start();
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
		System.out.println(name + " 已经吃了 " + i + " 个馒头 " + " 还剩 " + "0个馒头");
	}
}

class Box {
	int boxnum = 93;

	public synchronized int getMantou(int i, String name) {
		// 判断是否可以去取馒头
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

		}// 判断是去等待队列还是争抢馒头
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
