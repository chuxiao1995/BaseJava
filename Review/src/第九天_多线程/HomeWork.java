package �ھ���_���߳�;
/**
 * ��ֻ�ܺ�һ��ֻ�۷䣬��һ���������Ϊ20���۹��У��۷�ÿֻһ��ѭ����������1���۷�
 * ��ÿ�γ��۹����ۣ�һ�ο��Գ��꣬���ǽ�������Ϊ20��ʱ���ȥ��ʳ��
 * ģ���������
 * @author 86152
 *
 */
public class HomeWork {//�ܺ��۷�
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


class BeeBox{//�۹�
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
		System.out.println("BeeBox.size " + list.size() + " �������� " + i);
		notify();//���������߳�
	}
	
	public  synchronized void remove() {//�������з���
	while (list.size() < Max) {
		try {
			wait();
		} catch (Exception e) {
		}
	}
	notifyAll();//�����߳�
	list.clear();
	System.out.println("BeeBox.size   " + list.size());
	}
}


class Bee extends Thread{//�۷�
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


class Beer extends Thread{//��
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