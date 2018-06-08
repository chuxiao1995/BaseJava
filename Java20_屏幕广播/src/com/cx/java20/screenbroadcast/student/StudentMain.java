package com.cx.java20.screenbroadcast.student;

public class StudentMain {
	public static void main(String[] args) {
		StudentUI ui = new StudentUI();
		ReceiverThread thread = new ReceiverThread(ui);
		thread.start();
	}

}
