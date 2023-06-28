package com.dao;

import com.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao{
	@Autowired
	private JdbcTemplate template;
	@Override
	public boolean addTicket(Ticket ticket) {
		String sql = "insert into ticket(client_id,flight_id,seat_type,seat_no)" +
				"values(?,?,?,?)";
		int row = template.update(sql,ticket.getClient(),ticket.getFlight(),ticket.getSeatType(),
				ticket.getSeatNo());
		return 1 == row;
	}


	@Override
	public int findSeatNo(int flightId, String type) {//未做
		return 0;
	}

	@Override
	public boolean deleteTicket(int ticketId) {
		return true;
	}

	@Override
	public int countFlightTicket(int flightId) {
		return 0;
	}

	@Override
	public List<Ticket> findAllTicket() {
		return null;
	}

}
