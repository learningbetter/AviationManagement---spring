package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.ClientService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/LoginRegister")
public class LoginRegisterController {
	@Autowired
	ClientService clientService;

	@RequestMapping("/loginRegister")
	public ModelAndView loginRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ModelAndView mv = new ModelAndView();   //模型视图

		HttpSession session = request.getSession();
		int result = clientService.loginRegister(request.getParameter("username"),
				request.getParameter("password"), session);
		switch (result) {
			case (0): {   //密码错误
				//addObject在此处用于将提示密码错误信息给到loginError，前端通过属性名获取该消息并显示，key-value形式
				//和session会话传属性有点像
				mv.addObject("loginError", "密码错误，请重新输入");
				//setViewName用于设置跳转页面路径
				mv.setViewName("loginRegister");
				break;
			}
			case (1): {   //登录成功，身份为管理员
				mv.setViewName("administrator");
				break;
			}
			case (2): {   //登录成功，身份为用户
				mv.setViewName("buyTicket");
				break;
			}
		}
		return mv;
	}

}
