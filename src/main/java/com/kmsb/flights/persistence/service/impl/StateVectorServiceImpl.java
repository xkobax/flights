package com.kmsb.flights.persistence.service.impl;

import com.kmsb.flights.persistence.dao.StateVectorDAOImpl;
import com.kmsb.flights.persistence.entity.StateVector;
import com.kmsb.flights.persistence.service.StateVectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("stateVectorService")
@Transactional
public class StateVectorServiceImpl implements StateVectorService {

    @Autowired
    StateVectorDAOImpl stateVectorDAO;

    @Override
    public StateVector findByIcao24(String icao24) {
        return stateVectorDAO.findByIcao24(icao24);
    }

    @Override
    public void saveStateVector(StateVector stateVector) {
        stateVectorDAO.saveStateVector(stateVector);
    }

    @Override
    public void updateStateVector(StateVector stateVector) {
        StateVector entity = stateVectorDAO.findByIcao24(stateVector.getIcao24());
        entity.setIcao24(stateVector.getIcao24());
        entity.setCallsign(stateVector.getCallsign());
        entity.setOriginCountry(stateVector.getOriginCountry());
        entity.setOnGround(stateVector.isOnGround());
        stateVectorDAO.saveStateVector(entity);

    }

    @Override
    public void deleteStateVector(StateVector stateVector) {
        stateVectorDAO.deleteStateVector(stateVector);
    }

    @Override
    public List findAllStateVectors() {
        return stateVectorDAO.findAllStateVectors();
    }
}
