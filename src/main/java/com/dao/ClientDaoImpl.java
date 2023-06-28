package com.dao;

import com.entity.Client;
import com.mapper.ClientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl implements ClientDao{
	@Autowired
	private JdbcTemplate template;

	@Override
	public boolean addClient(Client client) {
		String sql = "insert into client(name,password,isAdmin) values(?,?,?)";
		int row = template.update(sql,client.getName(),client.getPassword(),client.getIsAdmin());
		if(1 == row){
			//这一句是为了将数据库设置的自增ID取出，并设置给client对象，这个对象是service传下来的
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
			//成功了返回对象本身，失败返回Null
			Client client = template.queryForObject(sql,new ClientRowMapper(),new String[]{username});
			return client;
		}catch (Exception e){
			return null;
		}
	}

}
