package com.kmsb.flights.persistence.dao;

import com.kmsb.flights.persistence.entity.StateVector;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stateVectorDAO")
public class StateVectorDAOImpl extends AbstractDAO<String, StateVector> implements StateVectorDAO {


    @Override
    public StateVector findByIcao24(String icao24) {
        return getByKey(icao24);
    }

    @Override
    public void saveStateVector(StateVector stateVector) {
        persist(stateVector);
    }

    @Override
    public void deleteStateVector(StateVector stateVector) {
        delete(stateVector);
    }

    @Override
    public List findAllStateVectors() {
        Criteria criteria = createEntityCriteria();
        return (List<StateVector>) criteria.list();
    }
}
