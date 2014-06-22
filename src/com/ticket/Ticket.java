package com.ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Random;

import com.service.TicketOrderService;
import com.service.TrainInfo;
import com.service.TrainInfo.TimeInfo;

public class Ticket {

	// TODO Any instances here
	private Seat seat;
	private String ticketID;
	private String studentID;
	private Date date;
	private String startStation;
	private String endStation;
	private Integer trainID;
	private String Deptime;
	private String Arrtime;
	private boolean seatBook;
	private File save;

	// TODO: fill in the constructor
	public Ticket(String ticketID) {
		TicketOrderService.getTickets().get(Integer.parseInt(ticketID));
	}

	public static Ticket getTicket(String ticketID) {
		// 回傳ticketID
		if (TicketOrderService.getTickets().get(Integer.parseInt(ticketID)) != null)
			return TicketOrderService.getTickets().get(
					Integer.parseInt(ticketID));
		else
			return null;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Ticket) {
			return this.getTicketID().equals(((Ticket) obj).getTicketID());
		}
		return false;
	}

	public String getDeptime() {
		return Deptime;
	}

	public String getArrtime() {
		return Arrtime;
	}

	// TODO: fill in the constructor
	public Ticket(String studentID, Date date, String startStation,
			String endStation, int trainID) {
		// 儲存學生資料，無座位
		this.studentID = studentID;
		this.date = date;
		this.startStation = startStation;
		this.endStation = endStation;
		this.trainID = trainID;

	}

	// TODO: create the ticket and store the attributes.
	public void create(Ticket ticket) {
		// 開上
		if (this.studentID.equals(ticket.studentID)
				&& this.date.equals(ticket.date)
				&& this.startStation.equals(ticket.startStation)
				&& this.endStation.equals(ticket.endStation)
				&& this.trainID == ticket.trainID) {

			Deptime = TrainInfo.TimeInfo.getDeptime();
			Arrtime = TimeInfo.getArrtime();
			setSeat();
		}

	}

	// TODO: please release the seat here.
	public boolean cancel() {
		// delete

		this.setTicketID(null);
		this.studentID = null;
		this.date = null;
		this.startStation = null;
		this.endStation = null;
		this.trainID = (Integer) null;
		this.seatBook = false;
		this.Deptime = null;
		this.Arrtime = null;
		this.seat.cancelSeat();

		return this.seatBook;

	}

	// TODO: you should assign the seat for this ticket
	public void setSeat() {
		Random r1 = new Random();
		Random r2 = new Random();
		int carNum = r1.nextInt(4) + 1;
		int seatNum = r2.nextInt(52) + 1;
		seat = new Seat(carNum, seatNum);
		seatBook = true;
	}

	public void printTicket() {

		/*
		 * TODO:Please print the 1) Date 2) Train Type 3) Train No 4) Dep Time
		 * 5) Arr Time 6) Seat Number
		 */
		System.out.println("Ticket ID  " + this.ticketID);
		System.out.println("1) Date: " + this.date);
		System.out.println("2) Train Type: " + this.trainID);
		System.out.println("3) Train No: " + this.seat.carNum);
		System.out.println("4) Dep Time: " + this.Deptime);
		System.out.println("5) Arr Time: " + this.Arrtime);
		System.out.println("6) Seat Number: " + this.seat.seatNum);
		System.out.println();

	}

	public void saveTicket() throws IOException {
		save = new File("C:/Users/user/Desktop/Ticket.txt");
		FileOutputStream fout = new FileOutputStream(save, true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
		bw.flush();

		bw.write("Ticket ID  " + this.ticketID);
		bw.newLine();
		bw.write("1) Date: " + this.date);
		bw.newLine();
		bw.write("2) Train Type: " + this.trainID);
		bw.newLine();
		bw.write("3) Train No: " + this.seat.carNum);
		bw.newLine();
		bw.write("4) Dep Time: " + this.Deptime);
		bw.newLine();
		bw.write("5) Arr Time: " + this.Arrtime);
		bw.newLine();
		bw.write("6) Seat Number: " + this.seat.seatNum);
		bw.newLine();
		
		bw.write("祝您旅途愉快!");

		bw.flush();
		bw.close();
		fout.close();
		System.out.println("完成");
	}

	public Seat getSeat() {
		// TODO: return the seat object
		return this.seat;
	}

	public Date getDate() {
		return date;
	}

	/*
	 * There are only four cars in each train. And in each car, there are
	 * 52seats.
	 * 
	 * The sample seat number is Car 1-03
	 */

	public Integer getTrainID() {
		return trainID;
	}

	public String getEndStation() {
		return endStation;
	}

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public class Seat {
		// 有車號有座位號
		// TODO Any instances here
		private String carNum;
		private String seatNum;

		// TODO Any constructor here
		public Seat() {

		}

		public Seat(int carNum, int seatNum) {
			this.carNum = Integer.toString(carNum);
			this.seatNum = Integer.toString(seatNum);
		}

		public void cancelSeat() {
			this.setCarNum(null);
			this.setSeatNum(null);
		}

		public void setCarNum(String carNum) {
			this.carNum = carNum;
		}

		public void setSeatNum(String seatNum) {
			this.seatNum = seatNum;
		}

		public String getCarNum() {
			return carNum;
		}

		public String getSeatNum() {
			return seatNum;
		}

		@Override
		public String toString() {
			// TODO Please return seat information as format "{\2d-\2d}" (e.g.,
			// 1-03, 12-03, 10-32)
			return this.carNum + "-" + this.seatNum;
		}

	}
}
