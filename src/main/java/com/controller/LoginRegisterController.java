package com.controller;

import com.dao.AirplaneDaoImpl;
import com.entity.Airplane;
import com.service.AdministratorService;
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

@Controller
public class LoginRegisterController {
	@Autowired
	ClientService clientService;
	@RequestMapping("")
	public String indexToLogin(){
		/*直接以地址栏访问+链接跳转+jsp访问数据读取*/
		return "loginRegister.jsp";
	}
	@RequestMapping("/loginRegister")
	public ModelAndView loginRegister(@RequestParam String username,@RequestParam String password) throws IOException {

		ModelAndView mv = new ModelAndView();   //模型视图

		int result = clientService.loginRegister(username,password, mv);
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
				mv.setViewName("administrator.jsp");
				break;
			}
			case (2): {   //登录成功，身份为用户
				mv.setViewName("buyTicket.jsp");
				break;
			}
		}
		return mv;
	}

}
