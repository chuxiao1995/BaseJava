package 第九天_多线程;

public class ThreadDemo {
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		YourThread t2 = new YourThread();
		t1.start();
		t2.start();
	}

}

class MyThread extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println("MyThread");
			yield();
		}
	}

}

class YourThread extends Thread {
	public void run() {
		while (true) {
			System.out.println("YourThread");
			yield();
		}
	}

}