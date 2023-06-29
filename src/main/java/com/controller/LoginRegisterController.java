package com.controller;

import com.dao.AirplaneDaoImpl;
import com.entity.Airplane;
import com.entity.Flight;
import com.entity.Ticket;
import com.service.AdministratorService;
import com.service.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.ClientService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class LoginRegisterController {
	@Autowired
	ClientService clientService;

	@Autowired
	AdministratorService admin;

	@RequestMapping("")
	public String indexToLogin(){
		/*直接以地址栏访问+链接跳转+jsp访问数据读取*/
		return "loginRegister.jsp";
	}

	@RequestMapping("/loginRegister")
	public ModelAndView loginRegister(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();   //模型视图

		int result = clientService.loginRegister(request.getParameter("username"),request.getParameter("password"), session);
		switch (result) {
			case (0): {   //密码错误
				//addObject在此处用于将提示密码错误信息给到loginError，前端通过属性名获取该消息并显示，key-value形式
				//和session会话传属性有点像
				mv.addObject("loginError", "密码错误，请重新输入");
				//setViewName用于设置跳转页面路径
				mv.setViewName("loginRegister.jsp");
				break;
			}
			case (1): {   //登录成功，身份为管理员
				//此处定向到AdminController中渲染方法。
				mv.setViewName("/Administrator");
				break;
			}
			case (2): {   //登录成功，身份为用户
			//此处暂时改动，先登陆后跳到我的机票界面
				// 此处定向到ClientController中对应渲染方法
				mv.setViewName("/Client/buyTicket");
				break;
			}
		}
		return mv;
	}

}
