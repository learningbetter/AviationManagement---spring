package com.dao;

import com.entity.Ticket;
import com.mapper.TicketRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao{
	@Autowired
	private JdbcTemplate template;
	@Override
	public boolean addTicket(Ticket ticket) {
		String sql = "insert into ticket(client_id,flight_id,seat_type,seat_no)" +
				"values(?,?,?,?)";
		int row = template.update(sql,ticket.getClient().getClientId(),ticket.getFlight().getFlightId(),ticket.getSeatType(),
				ticket.getSeatNo());
		return 1 == row;
	}


	@Override
	public int getSeatNo(int flightId, String type){
//		/*查找表中当航班id=fightId，且ticket表座位类型=type的时候,ticket中有几行*/
//		返回座位号的意思
		String sql = "select COUNT(*) from ticket where flight_id=? and seat_type=?";
		int result=0;
		try {
			 result = template.queryForObject(sql, Integer.class, flightId,type);
		}
		catch (Exception e){
			return 0;
		}
		return result;
	}

	@Override
	public boolean deleteTicket(int ticketId) {
		/*根据对象在表中删除一行航班数据(是退票的意思？)*/

		return true;
	}

	@Override
	public int countFlightTicket(int flightId) {
		/*统计当前航班id在ticket表中有几行，也就是有几个客户购买了该航班*/
		return 0;
	}

	@Override
	public List<Ticket> findAllTicketByClientId(int clientId) {
		/*根据用户ID取出所有购票数据*/
		String sql = "select ticket.id,client_id,flight_id,seat_type,seat_no, " +
				"  name,password,isAdmin," +
				"  from_Data,to_data,from_position,to_position,first_class_price," +
				"           business_class_price,economy_class_price,airplane_id," +
				"  airplaneType,firstClassLimit,businessClassLimit,economyClassLimit   " +
				"  from ticket,client,flight,airplane " +
				"  where client_id=? and flight_id = flight.id and client_id=client.id and airplane_id=airplane.id";

		List<Ticket> tickets = template.query(sql,new TicketRowMapper(),clientId);
		System.out.println("ticket is "+tickets);
		return tickets;
	}

}