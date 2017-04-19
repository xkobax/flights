package com.kmsb.flights.persistence.dao;

import com.kmsb.flights.persistence.entity.StateVector;

import java.util.List;

public interface StateVectorDAO {

    StateVector findByIcao24(String icao24);

    void saveStateVector(StateVector stateVector);

    void deleteStateVector(StateVector stateVector);

    List findAllStateVectors();
}

