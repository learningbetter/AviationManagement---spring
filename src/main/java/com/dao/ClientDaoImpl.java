package com.dao;

import com.entity.Client;
import com.mapper.ClientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ClientDaoImpl implements ClientDao{
	@Autowired
	private JdbcTemplate template;

	@Override
	public boolean addClient(Client client) {
		String sql = "insert into client(name,password,isAdmin) values(?,?,?)";
		int row = template.update(sql,client.getName(),client.getPassword(),client.getIsAdmin());
		if(1 == row){
			client.setClientId(findClient(client.getName(),client.getPassword()).getClientId());
			return true;
		}
		else {
			return false;
		}

	}

	@Override
	public Client findClient(String username, String password) {
		String sql = "select id,name,password,isAdmin from client where name=?";
		try {
			Client client = template.queryForObject(sql,new ClientRowMapper(),new String[]{username});
			return client;
		}catch (Exception e){
			return null;
		}
	}

}
