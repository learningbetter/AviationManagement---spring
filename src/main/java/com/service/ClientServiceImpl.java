package com.service;

import com.dao.ClientDao;
import com.dao.FlightDao;
import com.dao.TicketDao;
import com.entity.Client;
import com.entity.Flight;
import com.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private FlightDao flightDao;

	@Override
	public int loginRegister(String username, String password, HttpSession session) {
		/*id统一使用自增*/
		Client client = clientDao.findClient(username, password);
		if (null != client) {
			if (client.getPassword().equals(password)) {
				session.setAttribute("clientId", client.getClientId());
				System.out.println("LoginSuccess,ID is:" + client.getClientId());
				return client.getIsAdmin(); //返回是否是管理员,1代表是，二代表否
			}
			return 0;   //代表密码错误登陆失败
		} else {

			client = new Client();
			client.setName(username);
			client.setPassword(password);
			client.setIsAdmin(2);

			if (clientDao.addClient(client)) {
				session.setAttribute("clientId", client.getClientId());
				System.out.println("RegisterSuccess,ID is:" + client.getClientId());
				return client.getIsAdmin();
			} else
				return 0;
		}
	}


	@Override
	public boolean buyTicket(int flightId, int purchasedFlight, int clientId) {
		/*id统一使用自增，mybatis自动获取*/
		Ticket ticket = new Ticket();
		Client client = new Client();
		client.setClientId(clientId);
		Flight flight = new Flight();
		flight.setFlightId(flightId);
		ticket.setClient(client);
		ticket.setFlight(flight);
		if (purchasedFlight == 1)
			ticket.setSeatType(Ticket.CLASS1_FIRST);
		else if (purchasedFlight == 2)
			ticket.setSeatType(Ticket.CLASS2_BUSINESS);
		else if (purchasedFlight == 3)
			ticket.setSeatType(Ticket.CLASS3_ECONOMY);
		ticket.setSeatNo(1 + ticketDao.findSeatNo(flightId, ticket.getSeatType()));
		;
		return ticketDao.addTicket(ticket);
	}

	@Override
	public boolean refundTheTicket(int ticketId) {
		return ticketDao.deleteTicket(ticketId);
	}

	@Override
	public List<Flight> showFlight() {
		return flightDao.findAllFlight();
	}

	@Override
	public List<Ticket> showTicket() {
		return ticketDao.findAllTicket();
	}
}
