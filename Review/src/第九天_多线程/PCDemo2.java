package 第九天_多线程;

public class PCDemo2 {// 死锁例子
	public static void main(String[] args) {
		Pool pool = new Pool();
		Productor1 p1 = new Productor1("生产者",pool);
		p1.setName("p1");
		Consumer1 c1 = new Consumer1("消费者1",pool);
		c1.setName("c1");
		Consumer1 c2 = new Consumer1("消费者2",pool);
		c2.setName("c2");
		p1.start();
		c1.start();
		c2.start();
	}
}

class Pool {
	private java.util.List<Integer> list = new java.util.LinkedList<Integer>();
	private int MAX = 1;

	public void add(int n) {
		synchronized (this) {
			try {
				String name = Thread.currentThread().getName();
				while (list.size() == MAX) {
					System.out.println(name + ".wait()");
					this.wait();

				}
				list.add(n);
				System.out.println(name + " + :" + n);
				System.out.println(name + ".notify()");
				this.notify();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int remove(){
		synchronized (this) {
			try {
				String name = Thread.currentThread().getName();
				while (list.size() == 0) {
					System.out.println(name + ".wait()");
					this.wait();

				}
				int i = list.remove(0);
				System.out.println(name + " - : " + i);
				System.out.println(name + ".notify()");
				this.notify();
				return i;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}

	}

}

class Productor1 extends Thread{//生产者
	static int i = 0;
	private String name;
	private Pool pool;
	public Productor1(String name,Pool pool){
		this.pool = pool;
		this.name = name;
	}
	public void run() {
		while (true) {
			pool.add(i ++);
		}
	}

	

}

class Consumer1 extends Thread {//消费者
	private String name;
	private Pool pool;
	public Consumer1(String name,Pool pool) {
		this.name = name;
		this.pool = pool;
	}
	public void run() {
		while (true) {
			pool.remove();
		}
	}
}