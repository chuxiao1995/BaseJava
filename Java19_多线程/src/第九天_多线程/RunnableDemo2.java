package 第九天_多线程;

//class RunnableDemo3 {
//	public static void main(String[] args) {//匿名内部类对象
//		new Thread(new Runnable(){
//			public void run() {
//				System.out.println("hello world");
//			}
//		}).start();
//	}
//
//}

class RunnableDemo2 {
	public static void main(String[] args) {//匿名内部类对象
		new Thread(){
			public void run() {
				System.out.println("hello world");
			}
		}.start();
	}

}