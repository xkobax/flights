package com.kmsb.flights.web.controller;

import com.kmsb.flights.persistence.entity.User;
import com.kmsb.flights.service.RestService;
import com.kmsb.flights.service.StateVectorService;
import com.kmsb.flights.persistence.entity.FlightStates;
import com.kmsb.flights.persistence.entity.StateVector;
import com.kmsb.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {

    public final static String LOGGED_IN = "loggedIn";

    @Autowired
    StateVectorService stateVectorService;

    @Autowired
    RestService restService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        if (session.getAttribute(LOGGED_IN) == null) {
            session.setAttribute(LOGGED_IN, false);
        }
        List flights = stateVectorService.findAllStateVectors();
        ModelAndView model = new ModelAndView("index", "flights", flights);
        return model;
    }

    @RequestMapping(value = "/pdf/{type}", method = RequestMethod.GET)
    public ModelAndView downloadPdf(@PathVariable("type") String type) {
        List flights = stateVectorService.findAllStateVectors();
        ModelAndView model = new ModelAndView("pdfView", "flights", flights);
        return model;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listFlights(ModelMap model){
        populateModel(model);
        return "currentFlights";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage(ModelMap model){
        //TODO: add if statement
//        model.addAttribute("errorMsg", "There is no user with such credentials");

        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute("user") User user){
        //TODO: impl login functionality
        User userFromDB = userService.findByName(user.getName());
        if(userFromDB != null) {
            session.setAttribute(LOGGED_IN, true);
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session, @ModelAttribute("user") User user){
        session.setAttribute(LOGGED_IN, false);
        //TODO: impl logout functionality
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
