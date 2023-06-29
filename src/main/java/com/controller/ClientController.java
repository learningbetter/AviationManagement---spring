package com.controller;


import com.service.ClientServiceImpl;
import com.entity.Flight;
import com.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.ClientService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.util.List;

@Controller
@RequestMapping("/Client")
public class ClientController {


	@Autowired
	private ClientService clientService;
	@RequestMapping("/myTicket")
	public ModelAndView myTicket(HttpServletRequest request,HttpServletResponse response){
		/*直接以地址栏访问+链接跳转+jsp访问数据读取*/
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		int clientId = (int) session.getAttribute("clientId");
		System.out.println("-----------"+clientId);
		modelAndView.addObject("ticketList",clientService.findAllTicketByClientId(clientId));
		modelAndView.setViewName("myTicket.jsp");
		return modelAndView;
	}
	@RequestMapping("/buyTicket")
	public ModelAndView buyTicket(){
		/*直接以地址栏访问+链接跳转+jsp访问数据读取*/
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("flightList",clientService.showFlight());
		modelAndView.setViewName("buyTicket.jsp");
		return modelAndView;
	}


	/*以上为url跳转访问，以下为act实际行动*/
	@RequestMapping("/buy")/*点击买票按钮*/
	@ResponseBody
	public String buy(@SessionAttribute Integer flightId, @RequestParam Integer purchasedFlight
			, @SessionAttribute Integer clientId) throws IOException {/*object转换可能会出错,后面注意*/
		System.out.println(flightId+" "+purchasedFlight+" "+clientId);
		if (clientService.buyTicket(flightId,purchasedFlight,clientId))
			return "<script>alert('购票成功');window.location.href=\"/Client/myTicket\";</script>";
		else
			return "<script>alert('购票失败');window.location.href=\"/Client/myTicket\";</script>";
	}

	@RequestMapping("/refundTicket")/*点击退票按钮*/
	@ResponseBody
	public String refundTicket(@RequestParam Integer refundTheTicket) throws IOException {
		if(clientService.refundTheTicket(refundTheTicket))
			return "<script>alert('退票成功');window.location.href=\"/Client/myTicket\";</script>";
		else
			return "<script>alert('退票失败');window.location.href=\"/Client/myTicket\";</script>";
	}

	@RequestMapping("/showFlight")
	public ModelAndView showFlight(HttpServletRequest request , HttpServletResponse response){

		ModelAndView mv = new ModelAndView();
		mv.setViewName("administrator");
		return mv;
	}
}
