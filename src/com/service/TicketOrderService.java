package com.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.service.TicketOrderException.ExceptionTYPE;
import com.service.TrainInfo.TimeInfo;
import com.ticket.Ticket;

//These packages are for xml parser
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TicketOrderService {

	// TODO Any instances here
	List<TrainInfo> trains = new ArrayList<TrainInfo>();
	TrainInfo traincheck;
	List<TimeInfo> table = new ArrayList<TimeInfo>();
	private List<Ticket> tickets = new ArrayList<Ticket>();

	protected TicketOrderService() {

		/*
		 * 1) Read the information from time table(timetable.xml) 2) Save the
		 * information in the {@code TicketOrderService}
		 */
		try {

			File fXmlFile = new File("./data/20140524.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList nList = doc.getElementsByTagName("TrainInfo");

			System.out.println("# of train:" + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				trains.add(new TrainInfo(nNode));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * You have to use the Real-Time Table
	 */

	public List<Ticket> order(String studentID, Date date, String startStation,
			String endStation, int trainID, int count)
			throws TicketOrderException {

		// TODO if the tickets are not available or sold out. Please throw the
		// related exception.
		ExceptionTYPE type = checkAvailable(studentID, date, startStation,
				endStation, trainID, count);

		if (type != null)
			throw new TicketOrderException(type);

		// .... more exceptions
		for (int i = 0; i < count; i++) {
			Ticket ticket = new Ticket(studentID, date, startStation,
					endStation, trainID);
			ticket.create(studentID, date, startStation, endStation, trainID);
			tickets.add(ticket);
		}
		return tickets;
	}

	/*
	 * Return ExceptionTYPE. Null means the tickets of the given conditions are
	 * available.
	 */

	public ExceptionTYPE checkAvailable(String studentID, Date date,
			String startStation, String endStation, int trainID, int count) {
		// TODO

		boolean checkTrainID = checkTrainID(trainID);
		boolean checkStationID = checkStationID(Integer.valueOf(startStation),
				Integer.valueOf(endStation));
		boolean checkBookFull = checkBookFull(date, startStation, trainID);

		if (!checkTrainID) {
			return ExceptionTYPE.TRAINID_NOFOUND;
		} else if (!checkStationID) {
			return ExceptionTYPE.STATATIONID_NOFOUND;
		} else if (checkBookFull) {
			return ExceptionTYPE.BOOKINGISFULL;
		}

		return null;
	}

	// check trainID
	public boolean checkTrainID(int trainID) {

		boolean check = false;

		for (int i = 1; i < trains.size(); i++) {
			if (trainID == Integer.parseInt(trains.get(i).getTrainID())) {
				check = true;
				traincheck = trains.get(i);
				break;
			}
		}

		return check;
	}

	// check stationID
	private boolean checkStationID(Integer startStat, Integer endStat) {

		boolean checkStart = false;
		boolean checkEnd = false;
		int count = 0;

		for (int i = 0; i < trains.size(); i++) {
			count = i;
			for (int j = 0; j < trains.get(i).getTables().size(); j++) {
				if (startStat.equals(trains.get(i).getTables().get(j)
						.getStationID().toString())) {
					checkStart = true;
					break;
				}
			}
			if (checkStart = true)
				break;

		}

		if (count == trains.size())
			return false;

		for (int i = count; i < trains.size(); i++) {
			for (int j = 0; j < trains.get(i).getTables().size(); j++) {
				if (endStat.equals(trains.get(i).getTables().get(j)
						.getStationID().toString())) {
					checkEnd = true;
					break;
				}
			}
			if (checkEnd = true)
				break;
		}

		if (checkStart == true && checkEnd == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkBookFull(Date date, String startStation, int trainID) {

		boolean check = false;
		int checkDay = 0;
		int checkSeat = 0;

		for (int i = 0; i < tickets.size(); i++) {
			if (!date.equals(tickets.get(i).getDate()))
				checkDay++;
		}

		if (checkDay == tickets.size())
			return check = false;

		for (int j = 0; j < tickets.size(); j++) {
			if (tickets.get(j).getTrainID() == trainID
					&& Integer.parseInt(tickets.get(j).getEndStation()) >= Integer
							.parseInt(startStation))
				checkSeat++;
		}

		if (checkSeat == 52 * 4)
			return check = true;
		else
			return check = false;

	}

}
