package com.entity;

import java.util.Date;

public class Flight {
	private int flightId;
	private Date fromData;
	private Date toData;
	private String from;
	private String to;
	private float firstClassPrice;
	private float businessClassPrice;
	private float economyClassPrice;
	private Airplane airplane;/*最好改为id*//*不用改为id,传输到前端显示数据时、从数据库获取数据映射时都更加方便*/

	@Override
	public String toString() {
		return "Flight{" +
				"flightId=" + flightId +
				", fromData=" + fromData +
				", toData=" + toData +
				", from='" + from + '\'' +
				", to='" + to + '\'' +
				", firstClassPrice=" + firstClassPrice +
				", businessClassPrice=" + businessClassPrice +
				", economyClassPrice=" + economyClassPrice +
				", airplane=" + airplane +
				'}';
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Date getFromData() {
		return fromData;
	}

	public void setFromData(Date fromData) {
		this.fromData = fromData;
	}

	public Date getToData() {
		return toData;
	}

	public void setToData(Date toData) {
		this.toData = toData;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public float getFirstClassPrice() {
		return firstClassPrice;
	}

	public void setFirstClassPrice(float firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	public float getBusinessClassPrice() {
		return businessClassPrice;
	}

	public void setBusinessClassPrice(float businessClassPrice) {
		this.businessClassPrice = businessClassPrice;
	}

	public float getEconomyClassPrice() {
		return economyClassPrice;
	}

	public void setEconomyClassPrice(float economyClassPrice) {
		this.economyClassPrice = economyClassPrice;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
}
