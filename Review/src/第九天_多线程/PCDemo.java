package �ھ���_���߳�;

public class PCDemo {//�����ߺ�������
public static void main(String[] args) {
	MyList myList = new MyList();
	Productor p1 = new Productor(myList);
	Productor p2 = new Productor(myList);
	//Productor p3 = new Productor(myList);
	Consumer c1 =new Consumer(myList);
	Consumer c2 =new Consumer(myList);
	p1.start();
	p2.start();
	//p3.start();
	c1.start();
	c2.start();
}
}


class MyList{
	private int Max = 100;
	private java.util.List<Integer> list = new java.util.LinkedList<Integer>();
	public synchronized void addLast(Integer i) {
		while (list.size() >= Max) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		list.add(i);
		System.out.println("add.size   " + list.size());
		notify();//�������һ���߳�
	}
	
	public  synchronized Integer removeFirst() {//���ѵ�һ����
	while (list.size() == 0) {
		try {
			wait();
		} catch (Exception e) {
		}
	}
	notify();//�������һ���߳�
	int no = list.remove(0);
	System.out.println("remove.size   " + list.size());
	return no;
	}
}


class Productor extends Thread{//������
	static int i = 1;
	private MyList myList;
	public Productor(MyList ml){
		this.myList = ml;
	}
	public void run() {
		while (true) {
			myList.addLast(i);
			System.out.println("P: " + i);
			i ++;
		}
	}
}


class Consumer extends Thread{//������
	private MyList myList;
	public Consumer(MyList ml) {
		this.myList = ml;
	}
	public void run() {
		while (true) {
			int no = myList.removeFirst();
			System.out.println("C: " + no);
//			try {
//				Thread.sleep(10);
//			} catch (Exception e) {
//			}
		}
	}
}