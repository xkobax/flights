package com.kmsb.flights.persistence.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize(using = FlightStatesDeserializer.class)
public class FlightStates implements Serializable {

    private static final long serialVersionUID = 3444079697798922551L;

    private int time;
    private List<StateVector> flightStates;

    /**
     * The point in time for which states are stored
     */
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Actual states for this point in time
     */
    public List<StateVector> getStates() {
        if (flightStates == null || flightStates.isEmpty()) return null;
        return this.flightStates;
    }

    public void setStates(List<StateVector> states) {
        this.flightStates = states;
    }
}