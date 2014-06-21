package com.ticket;

import java.util.Date;
import java.util.Random;

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

	// TODO: fill in the constructor
	public Ticket(String ticketID) {
		// 回傳ticketID
		this.ticketID = ticketID;
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
	public void create(String studentID, Date date, String startStation,
			String endStation, int trainID) {
		// 開上
		if (this.studentID.equals(studentID) && this.date.equals(date)
				&& this.startStation.equals(startStation)
				&& this.endStation.equals(endStation)
				&& this.trainID == trainID){
			
			Deptime = TimeInfo.getDeptime();
			Arrtime = TimeInfo.getArrtime();
			setSeat();
		}

			

	}

	// TODO: please release the seat here.
	public boolean cancel() {
		// delete
		this.studentID = null;
		this.date = null;
		this.startStation = null;
		this.endStation = null;
		this.trainID = (Integer) null;
		this.seatBook = false;
		this.Deptime = null;
		this.Arrtime = null;
		
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

		System.out.println("1) Date: " + this.date);
		System.out.println("2) Train Type: " + this.trainID);
		System.out.println("3) Train No: " + this.seat.carNum);
		System.out.println("4) Dep Time: " + this.Deptime);
		System.out.println("5) Arr Time: " + this.Arrtime);
		System.out.println("6) Seat Number: " + this.seat.seatNum);
		System.out.println();

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


	class Seat {
		// 有車號有座位號
		// TODO Any instances here
		private String carNum;
		private String seatNum;

		// TODO Any constructor here
		public Seat(int carNum, int seatNum) {
			this.carNum = Integer.toString(carNum);
			this.seatNum = Integer.toString(seatNum);
		}

		@Override
		public String toString() {
			// TODO Please return seat information as format "{\2d-\2d}" (e.g.,
			// 1-03, 12-03, 10-32)
			return this.carNum + "-" + this.seatNum;
		}
		
		

	}
}
