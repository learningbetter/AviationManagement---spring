package com.service;

import com.dao.AirplaneDao;
import com.dao.FlightDao;
import com.dao.TicketDao;
import com.entity.Airplane;
import com.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService{
	@Autowired
	private AirplaneDao airplaneDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private FlightDao flightDao;


	@Override
	public boolean addAirplane(Airplane airplane) {
		return airplaneDao.addAirplane(airplane);
	}

	@Override
	public boolean createFlight(Flight flight, String airplaneType) {
		Airplane airplane = airplaneDao.findAirplaneByType(airplaneType);
		if (airplane==null)
			return false;
		else{
			//此处将airPlane设置进Flight对象，对应数据库flight中字段airplane_id是int，是否需要同步设置id?
			flight.setAirplane(airplane);
			return flightDao.addFlight(flight);
		}
	}

	@Override
	public boolean deleteFlight(int flightId) {
		return flightDao.deleteFlight(flightId);
	}

	@Override
	public List<Flight> showFlight() {
		return flightDao.findAllFlight();
	}

	@Override
	public int[] showFlightNumber() {
		List<Integer> number = new ArrayList<>();
		for (int i=1;i<=flightDao.countFlight();i++)
			number.add(ticketDao.countFlightTicket(i));
		/*stream将List转换为Integer再换为int，可能有bug*/
		return number.stream().mapToInt(Integer::intValue).toArray();
	}
}
