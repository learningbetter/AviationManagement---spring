package com.service;

import com.entity.Flight;
import com.entity.Ticket;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ClientService {
	public int loginRegister(String username, String password, HttpSession session);
	public boolean buyTicket(int flightId,int purchasedFlight,int clientId);
	public boolean refundTheTicket(int ticketId);
	public List<Flight> showFlight();
	public List<Ticket> showTicket();
}
