package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/Client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	@RequestMapping("/buyTicket")
	public String buyTicket(HttpServletRequest request , HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		/*object转换可能会出错,后面注意*/
		if (clientService.buyTicket(Integer.parseInt((String) session.getAttribute("flightId")),
				Integer.parseInt(request.getParameter("purchasedFlight")),
				(int)session.getAttribute("clientId")))
			response.getWriter().print("<script>alert('购票成功');window.location.href('buyTicket');</script>");
		else
			response.getWriter().print("<script>alert('购票失败');window.location.href('buyTicket');</script>");
		return "";
	}

	@RequestMapping("/refundTicket")
	public String refundTicket(HttpServletRequest request , HttpServletResponse response) throws IOException {
		if(clientService.refundTheTicket(Integer.parseInt(request.getParameter("refundTheTicket"))))
			response.getWriter().print("<script>alert('退票成功');window.location.href('myTicket');</script>");
		else
			response.getWriter().print("<script>alert('退票失败');window.location.href('myTicket');</script>");
		return "";
	}
}
