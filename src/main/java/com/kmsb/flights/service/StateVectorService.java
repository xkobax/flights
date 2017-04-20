package com.kmsb.flights.service;

import com.kmsb.flights.persistence.entity.StateVector;

import java.util.List;

public interface StateVectorService {

    StateVector findByIcao24(String icao24);

    void saveStateVector(StateVector stateVector);

    void updateStateVector(StateVector stateVector);

    void deleteStateVector(StateVector stateVector);

    List findAllStateVectors();

}
