package �ھ���_���߳�;

public class ThreadDemo1 {//�齫��Ϸ  ����join()
	public static void main(String[] args) {
		Player p1 = new Player("1", 5000);
		Player p2 = new Player("2", 8000);
		Player p3 = new Player("3", 4000);
		Player p4 = new Player("4", 3000);
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		try {
			//p1.join();
			//p2.join();
			//p3.join();
			p4.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("�˵��롣����");
	}

}


class Player extends Thread{
	private String name;
	private int time;
	public Player(String name,int time) {
		this.name = name;
		this.time = time;
		
	}
	public void run() {
		System.out.println("��ң�" + name + "�����ˣ�");
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			
		}
		System.out.println("���" + name +"����");
	}
	
}
