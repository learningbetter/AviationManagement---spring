package com.dao;

import com.entity.Ticket;

import java.util.List;

public interface TicketDao {
	public boolean addTicket(Ticket ticket);
	public int findSeatNo(int flightId,String type);
	public boolean deleteTicket(int ticketId);
	public int countFlightTicket(int flightId);
	public List<Ticket> findAllTicket();
}
