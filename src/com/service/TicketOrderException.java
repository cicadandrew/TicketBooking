package com.service;

/**
 * The class {@code TicketOrderException} and its subclasses are a form of
 * {@code Exception} and that indicates conditions that a reasonable application
 * might want to catch.
 * 
 * 
 * @author 103-2 EOSE OOP Course
 */

public class TicketOrderException extends Exception {

	private static final long serialVersionUID = 8637026090957511860L;

	enum ExceptionTYPE {
		TRAINID_NOFOUND, STATATIONID_NOFOUND, BOOKINGISFULL,

	}

	// TODO Any instances
	ExceptionTYPE type;

	public TicketOrderException(ExceptionTYPE type) {

		// TODO
		super();
		this.type = type;

	}

	@Override
	public String getMessage() {

		// TODO
		// You have to design different error message for different exception
		// type.
		if (this.type == ExceptionTYPE.TRAINID_NOFOUND) {
			return "Train ID is not found!";
		} else if (this.type == ExceptionTYPE.STATATIONID_NOFOUND) {
			return "Station ID is not found!";
		} else if (this.type == ExceptionTYPE.BOOKINGISFULL) {
			return "Sorry! No seat Available!";
		} else {
			return "Error";
		}

	}

}
