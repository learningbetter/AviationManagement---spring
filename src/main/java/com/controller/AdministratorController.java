package com.controller;

import com.entity.Airplane;
import com.entity.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.AdministratorService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping("/Administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService admin;

    @RequestMapping("/addAirplane")
    public ModelAndView addAirplane(HttpServletRequest request , HttpServletResponse response) throws IOException {
        Airplane airplane = new Airplane();
        //获取前端输入的相关的信息并设置给airplane对象
        airplane.setAirplaneType(request.getParameter("airplaneType"));
        airplane.setFirstClassLimit(Integer.parseInt(request.getParameter("firstClassLimit")));
        airplane.setBusinessClassLimit(Integer.parseInt(request.getParameter("businessClassLimit")));
        airplane.setEconomyClassLimit(Integer.parseInt(request.getParameter("economyClassLimit")));
        ModelAndView mv = new ModelAndView();
//        调用service层方法添加飞机
        if (admin.addAirplane(airplane)){
            mv.addObject("message","飞机增加成功");
        }else{
            mv.addObject("message","飞机增加失败");
        }
        mv.setViewName("administrator");
        return mv;
    }

    public ModelAndView showAllAirplane(HttpServletRequest request , HttpServletResponse response){
        //显示所有飞机，放着看后面要不要做
        return null;
    }


    @RequestMapping("/createFlight")
    public ModelAndView createFight(HttpServletRequest request , HttpServletResponse response) throws IOException {

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

        ModelAndView mv = new ModelAndView();
        if (admin.createFlight(flight,request.getParameter("airplaneType")))
            mv.addObject("message","航班创建成功");
        else
            mv.addObject("message","航班创建失败");
        return mv;
    }

    @RequestMapping("/deleteFlight")
    public ModelAndView deleteFlight(HttpServletRequest request , HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        if (admin.deleteFlight(Integer.parseInt(request.getParameter("deleteFlight"))))
            mv.addObject("message","航班删除成功");
        else
            mv.addObject("message","航班删除失败");
        return mv;
    }

    @RequestMapping("/showFlight")
    public ModelAndView showFlight(HttpServletRequest request , HttpServletResponse response){
        List<Flight> flights= admin.showFlight();
        HttpSession session = request.getSession();
        session.setAttribute("flightsList",flights);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("administrator");
        return mv;
    }
}
