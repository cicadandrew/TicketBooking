package com.service;

public class TicketOrderFacetory {

	
	public static TicketOrderService getService(){
		
		return new TicketOrderService();
		
	}
}
