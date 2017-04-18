package com.kmsb.flights.web.controller;

import com.kmsb.flights.restful.RestConsumer;
import com.kmsb.flights.restful.model.FlightStates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.kmsb.flights.web.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;


@Controller
public class MainController {

    /*First method on start application*/
//    /*Попадаем сюда на старте приложения (см. параметры аннтоции и настройки пути после деплоя) */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String main(ModelMap model) {
//        return "redirect:/cars";
//    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(ModelMap model) {
        FlightStates flightStates = RestConsumer.retrieveFlightStates();
        if (flightStates != null) {
            LocalDateTime date =
                    LocalDateTime.ofInstant(Instant.ofEpochSecond(flightStates.getTime()), ZoneId.systemDefault());

            model.addAttribute("time", date.toString());
            model.addAttribute("number", flightStates.getStates().size());
            model.addAttribute("flights", flightStates.getStates().subList(0,39));
        }
        return "index";
    }

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-user">,
    то попадем вот сюда
     */
//    @RequestMapping(value = "/check-user")
//    public ModelAndView checkUser(@ModelAttribute("userJSP") User user) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        //имя представления, куда нужно будет перейти
//        modelAndView.setViewName("secondPage");
//
//        //записываем в атрибут userJSP (используется на странице *.jsp объект user
//        modelAndView.addObject("userJSP", user);
//
//        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
//    }
}
