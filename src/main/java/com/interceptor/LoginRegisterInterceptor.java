//package com.interceptor;
//
//import com.entity.Flight;
//import com.service.AdministratorService;
//import com.service.AdministratorServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.awt.*;
//import java.util.List;
//
//
////SpringMVC拦截器，此处用于登陆界面前后拦截
//public class LoginRegisterInterceptor implements HandlerInterceptor {
//
//	//前端提交表单后，先到preHandle方法，若返回true,则进入controller处理，若返回false直接拦截拒绝
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
////		if(null == request.getParameter("username")){
////			//如果用户名输入是空，拦截 (测试过条件这样设置拦不到)
//////			response.sendRedirect("");
////			return false;
////		}
//
//		System.out.println("beforeController");
//		return true;
//	}
//
//	//Controller执行完后，视图渲染前执行postHandle方法，设置session给到前端可以写在这里
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//		System.out.println("afterController");
//	}
//
//	//视图渲染后执行该方法
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		System.out.println("end");
//	}
//}
