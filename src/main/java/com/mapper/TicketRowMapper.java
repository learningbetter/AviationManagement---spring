package com.mapper;

import com.dao.ClientDao;
import com.dao.FlightDao;
import com.entity.Airplane;
import com.entity.Client;
import com.entity.Flight;
import com.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TicketRowMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setTicketId(rs.getInt("ticket.id"));

		Client client = new Client();
		client.setClientId(rs.getInt("client_id"));
		client.setName(rs.getString("name"));
		client.setPassword(rs.getString("password"));
		client.setIsAdmin(rs.getInt("isAdmin"));

		Flight flight = new Flight();
		flight.setFlightId(rs.getInt("flight_id"));
		flight.setFromData(rs.getDate("from_Data"));
		flight.setToData(rs.getDate("to_data"));
		flight.setFrom(rs.getString("from_position"));
		flight.setTo(rs.getString("to_position"));
		flight.setFirstClassPrice(rs.getInt("first_class_price"));
		flight.setBusinessClassPrice(rs.getInt("business_class_price"));
		flight.setEconomyClassPrice(rs.getInt("economy_class_price"));

		Airplane airplane = new Airplane();
		airplane.setAirplaneId(rs.getInt("airplane_id"));
		airplane.setAirplaneType(rs.getString("airplaneType"));
		airplane.setFirstClassLimit(rs.getInt("firstClassLimit"));
		airplane.setBusinessClassLimit(rs.getInt("businessClassLimit"));
		airplane.setEconomyClassLimit(rs.getInt("economyClassLimit"));

		flight.setAirplane(airplane);
		ticket.setClient(client);
		ticket.setFlight(flight);

		ticket.setSeatType(rs.getString("seat_type"));
		ticket.setSeatNo(rs.getInt("seat_no"));

		System.out.println(ticket);
		return ticket;
	}
}
