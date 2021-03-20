package com.se2.hanuairline.model;

import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.aircraft.AircraftSeat;
import com.se2.hanuairline.model.airport.Airway;
import com.se2.hanuairline.model.airport.Gate;
import com.se2.hanuairline.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "ticket")
public class Ticket implements Cloneable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "aircraftseat_id")
    private AircraftSeat aircraftSeat;

    @NotBlank
    @NotNull
    private String status;

    @NotBlank
    @NotNull
    private int total_price;

    public Ticket(Long id, User user, Flight flight, AircraftSeat aircraftSeat, @NotBlank @NotNull TicketStatus status, @NotBlank @NotNull int total_price) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.aircraftSeat = aircraftSeat;
        this.status = status.toString();
        this.total_price = total_price;
    }

    public Ticket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public AircraftSeat getAircraftSeat() {
        return aircraftSeat;
    }

    public void setAircraftSeat(AircraftSeat aircraftSeat) {
        this.aircraftSeat = aircraftSeat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status.toString();
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}