package com.mapper;

import com.entity.Airplane;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirplaneRowMapper implements RowMapper<Airplane> {

	@Override
	public Airplane mapRow(ResultSet rs, int rowNum) throws SQLException {
		Airplane airplane = new Airplane();
		System.out.println("dawdad");
		airplane.setAirplaneId(rs.getInt("id"));
		airplane.setAirplaneType(rs.getString("airplaneType"));

		airplane.setFirstClassLimit(rs.getInt("firstClassLimit"));
		airplane.setBusinessClassLimit(rs.getInt("businessClassLimit"));
		airplane.setEconomyClassLimit(rs.getInt("economyClassLimit"));
		return airplane;
	}
}
