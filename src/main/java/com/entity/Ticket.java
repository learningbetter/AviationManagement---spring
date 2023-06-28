package com.entity;

/*该实体类可用于在 我的机票 页面显示统计数据*/
public class Ticket {
	/*Ticket的创建只能在FlightRevenue航班收益中进行*/
	public static final String CLASS1_FIRST = "头等舱";
	public static final String CLASS2_BUSINESS = "商务舱";
	public static final String CLASS3_ECONOMY = "经济舱";

	private int ticketId;
	private Client client;
	private Flight flight;
	/*关于是否要将Flight、Client改为id，而不是对象：使用对象更优，虽然略占内存，但数据库到后端、后端到前端，数据的存放与显示都更加方便*/
	private String seatType;/*座位等级:头等、商务、经济*/
	private int seatNo;//座位号
	/*座位类放弃，只封装了两个属性，又不存入数据库，额外增加实体，没有意义*/
//    private Seat seat;


	@Override
	public String toString() {
		return "Ticket{" +
				"FIRST_CLASS_1='" + CLASS1_FIRST + '\'' +
				", BUSINESS_CLASS_2='" + CLASS2_BUSINESS + '\'' +
				", ECONOMY_CLASS_3='" + CLASS3_ECONOMY + '\'' +
				", ticketId=" + ticketId +
				", client=" + client +
				", flight=" + flight +
				", seatType='" + seatType + '\'' +
				", seatNo=" + seatNo +
				'}';
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
}
