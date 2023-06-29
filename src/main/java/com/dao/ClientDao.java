package com.dao;

import com.entity.Client;

import java.util.List;

public interface ClientDao {
	public boolean addClient(Client client);
	public Client findClientByName(String username);
	public Client findClientById(int clientId);


}
