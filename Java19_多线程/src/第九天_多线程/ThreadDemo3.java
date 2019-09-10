package 第九天_多线程;

public class ThreadDemo3 {//售票问题
public static void main(String[] args) {
	TicketMachine t1 = new TicketMachine();
	Saler s1 = new Saler("1",t1);
	Saler s2 = new Saler("2",t1);
	Saler s3 = new Saler("3",t1);
	s1.start();
	s2.start();
	s3.start();
	
}
}
class Saler extends Thread{
	//static int ticker = 10;
	TicketMachine tm;
	private String name;
	public Saler(String name,TicketMachine tm) {
		this.name = name;
		this.tm = tm;
	}
	public void run() {
		while (TicketMachine.ticker > 0) {
			System.out.println(name + "卖出票:" + tm.getTicket());
		}
	}
	
}

class TicketMachine{
	static int ticker = 10;
		public synchronized int getTicket() {
			int tickets = ticker;
			     ticker--;
				return tickets;
		}
}