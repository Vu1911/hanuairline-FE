package com.se2.hanuairline.model;

import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.airport.AirportStatus;
import com.se2.hanuairline.model.airport.Airway;
import com.se2.hanuairline.model.airport.Gate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "flight")
public class Flight implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "airway_id")
    private Airway airway;

    @NotBlank
    @NotNull
    private Instant departure_time;

    @ManyToOne
    @JoinColumn(name = "departure_gate_id")
    private Gate departure_gate;

    @NotBlank
    @NotNull
    private Instant arrival_time;

    @ManyToOne
    @JoinColumn(name = "arrival_gate_id")
    private Gate arrival_gate;

    @NotBlank
    @NotNull
    private String status; ;

    @OneToMany(mappedBy = "flight")
    private Set<Ticket> ticket;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountEvent discount;

    public Flight(Long id, Aircraft aircraft, Airway airway, @NotBlank @NotNull Instant departure_time, Gate departure_gate, @NotBlank @NotNull Instant arrival_time, Gate arrival_gate, @NotBlank @NotNull FlightStatus status, Set<Ticket> ticket, DiscountEvent discount) {
        this.id = id;
        this.aircraft = aircraft;
        this.airway = airway;
        this.departure_time = departure_time;
        this.departure_gate = departure_gate;
        this.arrival_time = arrival_time;
        this.arrival_gate = arrival_gate;
        this.status = status.toString();
        this.ticket = ticket;
        this.discount = discount;
    }

    public DiscountEvent getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountEvent discount) {
        this.discount = discount;
    }

    public Flight() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Airway getAirway() {
        return airway;
    }

    public void setAirway(Airway airway) {
        this.airway = airway;
    }

    public Instant getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Instant departure_time) {
        this.departure_time = departure_time;
    }

    public Gate getDeparture_gate() {
        return departure_gate;
    }

    public void setDeparture_gate(Gate departure_gate) {
        this.departure_gate = departure_gate;
    }

    public Instant getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Instant arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Gate getArrival_gate() {
        return arrival_gate;
    }

    public void setArrival_gate(Gate arrival_gate) {
        this.arrival_gate = arrival_gate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
