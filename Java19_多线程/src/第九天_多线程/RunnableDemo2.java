package �ھ���_���߳�;

//class RunnableDemo3 {
//	public static void main(String[] args) {//�����ڲ������
//		new Thread(new Runnable(){
//			public void run() {
//				System.out.println("hello world");
//			}
//		}).start();
//	}
//
//}

class RunnableDemo2 {
	public static void main(String[] args) {//�����ڲ������
		new Thread(){
			public void run() {
				System.out.println("hello world");
			}
		}.start();
	}

}