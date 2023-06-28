package com.mapper;

import com.entity.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Client client = new Client();
		client.setClientId(rs.getInt("id"));
		client.setName(rs.getString("name"));
		client.setPassword(rs.getString("password"));
		client.setIsAdmin(rs.getInt("isAdmin"));
		return client;
	}
}
