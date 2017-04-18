package com.kmsb.flights.web.controller;

import com.kmsb.flights.restful.RestConsumer;
import com.kmsb.flights.restful.model.FlightStates;
import com.kmsb.flights.restful.model.StateVector;
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

    @RequestMapping(value = "/persistFlight", method = RequestMethod.POST)
    public String persistFlights(@ModelAttribute("state") StateVector stateVector){

        //TODO: add hibernate dependency, save simplified vector to DB

        System.out.println("PERSISTED STATE VECTOR" + stateVector.getIcao24());

        return "redirect:/";
    }
}
