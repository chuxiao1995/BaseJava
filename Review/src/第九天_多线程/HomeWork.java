package 第九天_多线程;
/**
 * 两只熊和一百只蜜蜂，在一个最多容量为20的蜜罐中，蜜蜂每只一个循环可以生产1的蜜蜂
 * 熊每次吃蜜罐中蜜，一次可以吃完，但是仅在容量为20的时候才去捕食。
 * 模拟这个例子
 * @author 86152
 *
 */
public class HomeWork {//熊和蜜蜂
public static void main(String[] args) {
	BeeBox box = new BeeBox();
	for (int i = 0; i < 100; i++) {
		    new Bee(box).start();
	}
	Beer b1 = new Beer(box);
	Beer b2 = new Beer(box);
	b1.start();
	b2.start();
}
}


class BeeBox{//蜜罐
	private int Max = 20;
	static int i = 0;
	private java.util.List<Integer> list = new java.util.LinkedList<Integer>();
	public synchronized void addLast() {
		while (list.size() >= Max) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		list.add(1);
		i++;
		System.out.println("BeeBox.size " + list.size() + " 生产总数 " + i);
		notify();//唤醒所有线程
	}
	
	public  synchronized void remove() {//消费所有蜂蜜
	while (list.size() < Max) {
		try {
			wait();
		} catch (Exception e) {
		}
	}
	notifyAll();//唤醒线程
	list.clear();
	System.out.println("BeeBox.size   " + list.size());
	}
}


class Bee extends Thread{//蜜蜂
	//static int i = 1;
	private BeeBox bb;
	public Bee(BeeBox bb){
		this.bb = bb;
	}
	public void run() {
		while (true) {
			bb.addLast();
			//System.out.println("P: " + i);
		}
	}
}


class Beer extends Thread{//熊
	private BeeBox bb;
	public Beer(BeeBox bb) {
		this.bb = bb;
	}
	public void run() {
		while (true) {
			bb.remove();
		}
	}
}