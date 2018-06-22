package 第九天_多线程;
public class ThreadDemo2 {//解释守护线程Daemon
	public static void main(String[] args) {
		Room t1 = new Room(888, 4000);
		Waiter t2 = new Waiter();
		//t2.setDaemon(true);//设置线程为守护线程
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
	public void run() {//唱歌流程
		System.out.println(roomid+"号房间正在唱歌");
		try {
			sleep(time);
		} catch (Exception e) {
			
		}
		System.out.println(roomid + "号房间唱完走了");
	}
}

class Waiter extends Thread{
	
	public  Waiter() {//设置为守护线程
		this.setDaemon(true);
	}
	
	public void run() {
		while (true) {
		
			System.out.println("当前时间:" + new java.util.Date());
			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
	}
	
	
}
