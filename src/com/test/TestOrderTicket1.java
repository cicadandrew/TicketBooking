package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.service.TicketOrderException;
import com.service.TicketOrderFacetory;
import com.service.TicketOrderService;
import com.ticket.Ticket;

public class TestOrderTicket1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * The flow of this main function
		 * 
		 * 1) Initialize a TicketOrderService object 2) Add a ticket with the
		 * given ticketNo 3) Add a ticket with the given ticketNo 4) Print the
		 * tickets information 5) Cancel this two tickets 4) Print the ticket
		 * information
		 */

		TicketOrderService service = TicketOrderFacetory.getService();

		List<Ticket> tickets = new ArrayList<Ticket>();

		/*
		 * Sample Input
		 */

		String ticket1 = "000";

		try {

			if (!tickets.contains(new Ticket(ticket1)))
				tickets.add(new Ticket(ticket1)); // add successfully

//			if (!tickets.contains(new Ticket(ticket1)))
//				tickets.add(new Ticket(ticket1)); // this code does not execute.

		} catch (Exception e) {
			e.printStackTrace();
		}

		// The length of tickets should be 1.
		for (Ticket ticket : tickets) {
			ticket.printTicket();
		}

		for (Ticket ticket : tickets) {
			ticket.cancel();
		}

		for (Ticket ticket : tickets) {
			ticket.printTicket(); // You sould Print the "null"
		}

	}

}
