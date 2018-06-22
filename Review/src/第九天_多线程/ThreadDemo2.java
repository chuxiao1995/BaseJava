package �ھ���_���߳�;
public class ThreadDemo2 {//�����ػ��߳�Daemon
	public static void main(String[] args) {
		Room t1 = new Room(888, 4000);
		Waiter t2 = new Waiter();
		//t2.setDaemon(true);//�����߳�Ϊ�ػ��߳�
		t1.start();
		t2.start();
	}
  
}



class Room extends Thread{
	private int roomid ;
	private int time ;
	public Room(int roomid,int time) {
		this.time = time;
		this.roomid = roomid;
	}
	@Override
	public void run() {//��������
		System.out.println(roomid+"�ŷ������ڳ���");
		try {
			sleep(time);
		} catch (Exception e) {
			
		}
		System.out.println(roomid + "�ŷ��䳪������");
	}
}

class Waiter extends Thread{
	
	public  Waiter() {//����Ϊ�ػ��߳�
		this.setDaemon(true);
	}
	
	public void run() {
		while (true) {
		
			System.out.println("��ǰʱ��:" + new java.util.Date());
			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
	}
	
	
}
