package com.dao;

import com.entity.Airplane;
import com.mapper.AirplaneRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AirplaneDaoImpl implements AirplaneDao{
	@Autowired
	private JdbcTemplate template;
	@Override
	public boolean addAirplane(Airplane airplane) {
		String sql = "insert into airplane(airplaneType,firstClassLimit,businessClassLimit,economyClassLimit)"
				+ "values (?,?,?,?)";

		return 1 == template.update(sql,airplane.getAirplaneType(),airplane.getFirstClassLimit(),
				airplane.getBusinessClassLimit(),airplane.getEconomyClassLimit());
	}

	@Override
	public Airplane findAirplaneByType(String airplaneType) {
			String sql = "select id,airplaneType,firstClassLimit,businessClassLimit,economyClassLimit from airplane " +
					"where airplaneType=?";
			return template.queryForObject(sql,new AirplaneRowMapper(),airplaneType);
	}

	@Override
	public Airplane findAirplaneById(int airplaneId) {
		String sql = "select id,airplaneType,firstClassLimit,businessClassLimit,economyClassLimit from airplane " +
				"where id=1";
		return template.queryForObject(sql,new AirplaneRowMapper());

	}
}
