package com.test;

import javax.swing.JFrame;

import com.service.TicketBooking;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("訂票系統兒");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.add(new TicketBooking());
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

}
