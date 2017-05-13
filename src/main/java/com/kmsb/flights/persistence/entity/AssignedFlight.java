package com.kmsb.flights.persistence.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="assigned_flights")
public class AssignedFlight implements Serializable {

    private static final long serialVersionUID = -2400603650969231893L;

    @Id
    @GeneratedValue
    @Column(name="id")
    private BigDecimal id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(targetEntity = StateVector.class)
    @JoinColumn(name="icao24")
    private StateVector stateVector;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StateVector getStateVector() {
        return stateVector;
    }

    public void setStateVector(StateVector stateVector) {
        this.stateVector = stateVector;
    }
}
