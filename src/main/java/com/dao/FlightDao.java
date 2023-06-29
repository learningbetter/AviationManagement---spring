package com.dao;

import com.entity.Flight;

import java.util.List;

public interface FlightDao {
	public boolean addFlight(Flight flight);
	public boolean deleteFlight(int flightId);
	public int countFlight();
	public List<Flight> findAllFlight();

	public Flight findFlightById(int flightId);
}
