package com.entity;


import org.springframework.stereotype.Component;

@Component
public class Airplane {
	private Integer airplaneId;
	private String airplaneType;/*飞机型号*/
	private int firstClassLimit;/*头等舱个数*/
	private int businessClassLimit;/*商务舱个数*/
	private int economyClassLimit;/*经济舱个数*/

	@Override
	public String toString() {
		return "Airplane{" +
				"airplaneId=" + airplaneId +
				", airplaneType='" + airplaneType + '\'' +
				", firstClassLimit=" + firstClassLimit +
				", businessClassLimit=" + businessClassLimit +
				", economyClassLimit=" + economyClassLimit +
				'}';
	}

	public Integer getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(Integer airplaneId) {
		this.airplaneId = airplaneId;
	}

	public String getAirplaneType() {
		return airplaneType;
	}

	public void setAirplaneType(String airplaneType) {
		this.airplaneType = airplaneType;
	}

	public int getFirstClassLimit() {
		return firstClassLimit;
	}

	public void setFirstClassLimit(int firstClassLimit) {
		this.firstClassLimit = firstClassLimit;
	}

	public int getBusinessClassLimit() {
		return businessClassLimit;
	}

	public void setBusinessClassLimit(int businessClassLimit) {
		this.businessClassLimit = businessClassLimit;
	}

	public int getEconomyClassLimit() {
		return economyClassLimit;
	}

	public void setEconomyClassLimit(int economyClassLimit) {
		this.economyClassLimit = economyClassLimit;
	}
}
