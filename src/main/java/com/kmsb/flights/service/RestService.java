package com.kmsb.flights.service;

import com.kmsb.flights.persistence.entity.FlightStates;

public interface RestService {

    FlightStates retrieveFlightStates();
    void refreshAllStateVectors();

}
