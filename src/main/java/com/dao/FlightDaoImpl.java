package com.dao;

import com.entity.Airplane;
import com.entity.Flight;
import com.mapper.FlightRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightDaoImpl implements FlightDao {
	@Autowired
	JdbcTemplate template;
	@Override
	public boolean addFlight(Flight flight) {
		String sql = "insert into flight(from_Data,to_data,from_position,to_position," +
				"first_class_price,business_class_price,economy_class_price,airplane_id)\n" +
				"values(?,?,?,?,?,?,?,?)";

		int row = template.update(sql,flight.getFromData(),flight.getToData(),flight.getFrom(),flight.getTo(),
				flight.getFirstClassPrice(),flight.getBusinessClassPrice(),
				flight.getEconomyClassPrice(),
				flight.getAirplane().getAirplaneId());//此处传入的是flight->airplane->airplaneID)
		return 1 == row;
	}

	@Override
	public boolean deleteFlight(int flightId) {
		String sql = "delete from flight where id = ?";
		int row = template.update(sql,flightId);
		return 1 == row;
	}

	@Override
	public int countFlight() {//未做
		return 0;
	}

	@Override
	public Flight findFlightById(int flightId){
		String sql = "select flight.id,from_Data,to_data,from_position,to_position,first_class_price,business_class_price,economy_class_price,airplane_id," +
				"airplaneType,firstClassLimit,businessClassLimit,economyClassLimit " +
				" from flight,airplane where flight.id=? and airplane_id=airplane.id";
		return template.queryForObject(sql,new FlightRowMapper(),flightId);
	}

	@Override
	public List<Flight> findAllFlight() {
		String sql = "select flight.id,from_Data,to_data,from_position,to_position,first_class_price,business_class_price,economy_class_price,airplane_id," +
				"airplaneType,firstClassLimit,businessClassLimit,economyClassLimit " +
				" from flight,airplane where airplane_id=airplane.id";
		try {
			List<Flight> flights = template.query(sql, new FlightRowMapper());
			System.out.println(flights);
			return flights;
		}catch (Exception e)
		{
			return null;
		}

	}
}



