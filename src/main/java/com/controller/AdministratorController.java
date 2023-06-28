package com.controller;

import com.entity.Airplane;
import com.entity.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.AdministratorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/Administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService admin;
//撒旦法

    @RequestMapping("/addAirplane")
    public String addAirplane(HttpServletRequest request , HttpServletResponse response) throws IOException {
//        Airplane airplane = new Airplane();
        return "index";
//
//        //获取前端输入的相关的信息并设置给airplane对象
//        airplane.setAirplaneType(request.getParameter("airplaneType"));
//        airplane.setFirstClassLimit(Integer.parseInt(request.getParameter("firstClassLimit")));
//        airplane.setBusinessClassLimit(Integer.parseInt(request.getParameter("businessClassLimit")));
//        airplane.setEconomyClassLimit(Integer.parseInt(request.getParameter("economyClassLimit")));
//        response.setContentType("text/html;charset=UTF-8");
//        return "index";
        //调用service层方法添加飞机
//        if (admin.addAirplane(airplane)){
////            response.getWriter().print("<script>alert('飞机增加成功'); window.location.href='administrator';</script>");
//            response.getWriter().print("飞机增加成功");
//            return "";
//        }else{
////            response.getWriter().print("<script>alert('飞机增加失败'); window.location.href='administrator';</script>");
//            response.getWriter().print("飞机增加失败");
//        }

//        返回到哪个界面
//        return "";
    }

    @RequestMapping("/createFlight")
    public String createFight(HttpServletRequest request , HttpServletResponse response) throws IOException {

        Flight flight = new Flight();
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        /*SimpleDateFormat记录，转换前端形如：2022-12-14T17:24的数据时，应该构造如上"yyyy-MM-dd'T'HH:mm"字符串创建SimpleDateFormat记录对象*/
        try {
            flight.setFromData(formatter.parse(request.getParameter("fromData")));
            flight.setToData(formatter.parse(request.getParameter("toData")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        flight.setFrom(request.getParameter("from"));
        flight.setTo(request.getParameter("to"));
        flight.setFirstClassPrice(Float.parseFloat(request.getParameter("firstClassPrice")));
        flight.setBusinessClassPrice(Float.parseFloat(request.getParameter("businessClassPrice")));
        flight.setEconomyClassPrice(Float.parseFloat(request.getParameter("economyClassPrice")));
        if (admin.createFlight(flight,request.getParameter("airplaneType")))
            response.getWriter().print("<script>alert('航班创建成功');window.location.href='administrator';</script>");
        else
            response.getWriter().print("<script>alert('航班创建失败');window.location.href='administartor';</script>");
//      返回到哪个界面
        return "";
    }

    @RequestMapping("/deleteFlight")
    public String deleteFlight(HttpServletRequest request , HttpServletResponse response) throws IOException {
        if (admin.deleteFlight(Integer.parseInt(request.getParameter("deleteFlight"))))
            response.getWriter().print("<script>alert('删除航班成功');window.location.href('administrator');</script>");
        else
            response.getWriter().print("<script>alert('删除航班失败');window.location.href('administrator');</script>");
        return "";
    }
}
