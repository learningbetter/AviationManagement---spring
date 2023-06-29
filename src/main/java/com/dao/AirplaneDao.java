package com.dao;

import com.entity.Airplane;

public interface AirplaneDao {
	public boolean addAirplane(Airplane airplane);
	public Airplane findAirplaneByType(String airplaneType);

	public Airplane findAirplaneById(int airplaneId);

}
