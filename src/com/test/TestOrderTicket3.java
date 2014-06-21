package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.service.TicketOrderException;
import com.service.TicketOrderFacetory;
import com.service.TicketOrderService;
import com.ticket.Ticket;

public class TestOrderTicket3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * The flow of this main function
		 * 
		 * 1) Initialize a TicketOrderService object
		 * 2) Order 2 tickets
		 * 3) Order 2 tickets
		 * 4) Print the tickets information
		 * 5) Cancel this two tickets
		 * 4) Print the ticket information
		 */
		
		
		TicketOrderService service = TicketOrderFacetory.getService();
		
		
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		/*
		 * Sample Input
		 */
		
		String       studentID    = "myid";//TODO
		Date         date         = new Date(); 
 		String       startStation = "1003"; //SongShan Station
		String       endStation   = "1008"; //Taipei Station
		
		int          trainID      =  1100;
		int          ticketnum    =  2;
		
		try {
			
			
			tickets.addAll(service.order(studentID, date, startStation, endStation, trainID, ticketnum)); //No error
			
			
			
		} catch (TicketOrderException e) {
			System.err.print(e.getMessage());
			
		}
		
		try {
			tickets.addAll(service.order(studentID, date, startStation, endStation, trainID, ticketnum));
			
		} catch (TicketOrderException e) {
			System.err.print(e.getMessage());
		}
		
		List<String> ticketNos = new ArrayList<>();
		for(Ticket ticket: tickets){
			ticket.printTicket();
			//TODO: get the ticket number(TicketNo) of each ticket and save them into ticket 
			ticketNos.add(ticket.getTicketID());
			
		}
		
		System.out.println(tickets.size() + "\n");
		
		
		//TODO Retrieve the number from the last booking
		for(String ticketNo: ticketNos){
			Ticket ticket = Ticket.getTicket(ticketNo);
			ticket.printTicket();
		}
		
	}

}
