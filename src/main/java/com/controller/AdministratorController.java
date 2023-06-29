package com.controller;

import com.entity.Airplane;
import com.entity.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.AdministratorService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping("/Administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService admin;


    @RequestMapping("")
    public ModelAndView administrator(){
        /*直接以地址栏访问+链接跳转+jsp访问数据读取*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("flightList",admin.showFlight());
        modelAndView.addObject("flightNumber",admin.showFlightNumber());
        modelAndView.setViewName("administrator.jsp");
        return modelAndView;
    }
    /*以上为url跳转访问，以下为act实际行动*/
    @RequestMapping("/addAirplane")
    public ModelAndView addAirplane(@RequestParam String airplaneType, @RequestParam Integer firstClassLimit
            , @RequestParam Integer businessClassLimit, @RequestParam Integer economyClassLimit) throws IOException {
        Airplane airplane = new Airplane();
        //获取前端输入的相关的信息并设置给airplane对象
        airplane.setAirplaneType(airplaneType);
        airplane.setFirstClassLimit(firstClassLimit);
        airplane.setBusinessClassLimit(businessClassLimit);
        airplane.setEconomyClassLimit(economyClassLimit);
        ModelAndView mv = new ModelAndView();
//        调用service层方法添加飞机
        if (admin.addAirplane(airplane)){
            mv.addObject("message","飞机增加成功");
        }else{
            mv.addObject("message","飞机增加失败");
        }
        mv.setViewName("administrator.jsp");
        return mv;
    }

    @RequestMapping("/createFlight")
    public ModelAndView createFight(@RequestParam String fromData,@RequestParam String toData,@RequestParam String from
            ,@RequestParam String to,@RequestParam Float firstClassPrice,@RequestParam Float businessClassPrice
            ,@RequestParam Float economyClassPrice,@RequestParam String airplaneType) throws IOException {

        Flight flight = new Flight();
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        /*SimpleDateFormat记录，转换前端形如：2022-12-14T17:24的数据时，应该构造如上"yyyy-MM-dd'T'HH:mm"字符串创建SimpleDateFormat记录对象*/
        try {
            flight.setFromData(formatter.parse(fromData));
            flight.setToData(formatter.parse(toData));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        flight.setFrom(from);
        flight.setTo(to);
        flight.setFirstClassPrice(firstClassPrice);
        flight.setBusinessClassPrice(businessClassPrice);
        flight.setEconomyClassPrice(economyClassPrice);

        ModelAndView mv = new ModelAndView();
        if (admin.createFlight(flight,airplaneType))
            mv.addObject("message","航班创建成功");
        else
            mv.addObject("message","航班创建失败");
        return mv;
    }

    @RequestMapping("/deleteFlight")
    public ModelAndView deleteFlight(@RequestParam Integer deleteFlight) throws IOException {
        ModelAndView mv = new ModelAndView();
        if (admin.deleteFlight(deleteFlight))
            mv.addObject("message","航班删除成功");
        else
            mv.addObject("message","航班删除失败");
        mv.setViewName("administrator.jsp");
        return mv;
    }

    public ModelAndView showAllAirplane(){
        //显示所有飞机，放着看后面要不要做
        return null;
    }

}
