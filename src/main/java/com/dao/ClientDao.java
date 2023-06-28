package com.dao;

import com.entity.Client;

import java.util.List;

public interface ClientDao {
	public boolean addClient(Client client);
	public Client findClient(String username,String password);


}
