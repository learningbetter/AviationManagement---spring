package com.entity;

public class Client {
	private Integer clientId;
	private String name;
	private String password;

	private int isAdmin;

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}



	@Override
	public String toString() {
		return "Client{" +
				"clientId=" + clientId +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
