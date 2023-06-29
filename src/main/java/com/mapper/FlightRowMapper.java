package com.mapper;

import com.dao.AirplaneDao;
import com.dao.AirplaneDaoImpl;
import com.entity.Airplane;
import com.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightRowMapper implements RowMapper<Flight> {

	@Override
	public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
		Flight flight = new Flight();

		flight.setFlightId(rs.getInt("id"));
		flight.setFromData(rs.getDate("from_data"));
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

		return flight;
	}
}
