package com.kmsb.flights.web.controller;

import com.kmsb.flights.persistence.entity.User;
import com.kmsb.flights.service.RestService;
import com.kmsb.flights.service.StateVectorService;
import com.kmsb.flights.persistence.entity.FlightStates;
import com.kmsb.flights.persistence.entity.StateVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {

    @Autowired
    StateVectorService stateVectorService;

    @Autowired
    RestService restService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        //TODO: check if logged in
        model.addAttribute("loggedIn", true);

        model.addAttribute("flights", stateVectorService.findAllStateVectors());
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listFlights(ModelMap model){
        populateModel(model);
        return "currentFlights";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user){
        //TODO: impl login functionality

        return "redirect:/";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(@ModelAttribute("user") User user){
        //TODO: impl logou functionality

        return "redirect:/";
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

    @RequestMapping(value = "/showByIcao24", method = RequestMethod.GET)
    public String showByIcao24(@RequestParam("icao24") String param, ModelMap model){
        populateModel(model);
        List<StateVector> vectors =
        restService.retrieveFlightStates().getStates()
                .stream()
                .filter(p -> p.getIcao24().equals(param))
                .collect(Collectors.toList());

        model.remove("flights");
        model.addAttribute("flights", vectors);
        return "currentFlights";
    }

    @RequestMapping(value = "/showByOriginCountry", method = RequestMethod.GET)
    public String showByOriginCountry(@RequestParam("originCountry") String param, ModelMap model){
        populateModel(model);
        List<StateVector> vectors =
        restService.retrieveFlightStates().getStates()
                .stream()
                .filter(p -> p.getOriginCountry().equals(param))
                .collect(Collectors.toList());

        model.remove("flights");
        model.addAttribute("flights", vectors);
        return "currentFlights";
    }

    @RequestMapping(value = "/showByCallsign", method = RequestMethod.GET)
    public String showByCallsign(@RequestParam("callsign") String param, ModelMap model){
        populateModel(model);
        List<StateVector> vectors =
        restService.retrieveFlightStates().getStates()
                .stream()
                .filter(p -> p.getCallsign().equals(param))
                .collect(Collectors.toList());

        model.remove("flights");
        model.addAttribute("flights", vectors);
        return "currentFlights";
    }

    @RequestMapping(value = "/showByOnGround", method = RequestMethod.GET)
    public String showByOnGround(@RequestParam("onGround") String param, ModelMap model){
        populateModel(model);
        boolean groundParam = Boolean.valueOf(param);
        List<StateVector> vectors =
        restService.retrieveFlightStates().getStates()
                .stream()
                .filter(p -> Boolean.valueOf(groundParam).equals(Boolean.valueOf(p.isOnGround())))
                .collect(Collectors.toList());

        model.remove("flights");
        model.addAttribute("flights", vectors);
        return "currentFlights";
    }
}
