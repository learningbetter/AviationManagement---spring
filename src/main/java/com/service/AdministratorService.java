package com.service;

import com.entity.Airplane;
import com.entity.Flight;

import java.util.List;

public interface AdministratorService {
	public boolean addAirplane(Airplane airplane);
	public boolean createFlight(Flight flight, String airplaneType);
	public boolean deleteFlight(int flightId);
	public List<Flight> showFlight();
	public int[] showFlightNumber();
}
