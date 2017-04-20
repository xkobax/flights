package com.kmsb.flights.web.controller;

import com.kmsb.flights.service.RestService;
import com.kmsb.flights.service.StateVectorService;
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

    @Autowired
    RestService restService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("flights", stateVectorService.findAllStateVectors());
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listFlights(ModelMap model){
        populateModel(model);
        return "currentFlights";
    }
    @RequestMapping(value = "/alwaysFreshList", method = RequestMethod.GET)
    public String listRefreshedFlights(ModelMap model){
        restService.refreshAllStateVectors();
        populateModel(model);
        return "currentFlights";
    }

    @RequestMapping(value = "/persistFlight", method = RequestMethod.POST)
    public String persistFlights(@ModelAttribute("state") StateVector stateVector){
        stateVectorService.saveStateVector(stateVector);
        return "redirect:/list";
    }

    private void populateModel(ModelMap model){
        FlightStates flightStates = restService.retrieveFlightStates();
        if (flightStates != null) {
            LocalDateTime date =
                    LocalDateTime.ofInstant(Instant.ofEpochSecond(flightStates.getTime()), ZoneId.systemDefault());
            model.addAttribute("time", date.toString());
            model.addAttribute("number", flightStates.getStates().size());
            int cutOff = flightStates.getStates().size() > 40 ? 39 : flightStates.getStates().size();
            model.addAttribute("flights", flightStates.getStates().subList(0,cutOff));
        }
    }
}
