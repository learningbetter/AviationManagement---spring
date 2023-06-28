package com.dao;

import com.entity.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AirplaneDaoImpl implements AirplaneDao{
	@Autowired
	private JdbcTemplate template;
	@Override
	public boolean addAirplane(Airplane airplane) {
		String sql = "insert into Airplane(id,airplaneType,firstClassLimit,businessClassLimit,economyClassLimit)"
				+ "values (?,?,?,?,?)";

		return 1 == template.update(sql,airplane.getAirplaneId(),airplane.getAirplaneType(),airplane.getFirstClassLimit(),
				airplane.getBusinessClassLimit(),airplane.getEconomyClassLimit());
	}

	@Override
	public Airplane findAirplaneByType(String airplaneType) {
		String sql = "select id,firstClassLimit,businessClassLimit,economyClassLimit from Airplane " +
				"where airplaneType=?";
		return template.queryForObject(sql,new BeanPropertyRowMapper<Airplane>(Airplane.class),
				Integer.parseInt(airplaneType));
	}
}
