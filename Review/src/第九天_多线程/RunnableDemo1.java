package 第九天_多线程;
/**
 * 降低耦合
 * @author 86152
 *
 */
public class RunnableDemo1 {
	public static void main(String[] args) {
		MyRunnable r = new MyRunnable();
		new Thread(r).start();
	}
}

//runnable实现类
class MyRunnable implements Runnable{
	public void run() {
		while (true) {
			System.out.println("hello world");
		}
	}
}
