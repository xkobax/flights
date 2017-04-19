package com.kmsb.flights.web.controller;

import com.kmsb.flights.persistence.service.StateVectorService;
import com.kmsb.flights.restful.RestConsumer;
import com.kmsb.flights.persistence.entity.FlightStates;
import com.kmsb.flights.persistence.entity.StateVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Controller
public class MainController {

    @Autowired
    StateVectorService stateVectorService;

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

        stateVectorService.saveStateVector(stateVector);

        return "redirect:/";
    }
}
