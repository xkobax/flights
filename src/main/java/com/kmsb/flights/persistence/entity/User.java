package com.kmsb.flights.persistence.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;
    private String name;
    private String email;
    private String password;
    private String salt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    List<AssignedFlight> assignedFlights;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<AssignedFlight> getAssignedFlights() {
        return assignedFlights;
    }

    public void setAssignedFlights(List<AssignedFlight> assignedFlights) {
        this.assignedFlights = assignedFlights;
    }
}
