package com.se2.hanuairline.model.airport;

import com.se2.hanuairline.model.Flight;
import com.se2.hanuairline.model.PriceByClass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "airway")
public class Airway implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departure_airport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrival_airport;

    @NotBlank
    @NotNull
    private int distance_km;

    @OneToMany(mappedBy = "airway")
    private Set<Flight> flight;

    @OneToMany(mappedBy = "airway")
    private Set<PriceByClass> priceByClasses;

    public Airway(Long id, Airport departure_airport, Airport arrival_airport, @NotBlank @NotNull int distance_km) {
        this.id = id;
        this.departure_airport = departure_airport;
        this.arrival_airport = arrival_airport;
        this.distance_km = distance_km;
    }

    public Airway() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(Airport departure_airport) {
        this.departure_airport = departure_airport;
    }

    public Airport getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(Airport arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public int getDistance_km() {
        return distance_km;
    }

    public void setDistance_km(int distance_km) {
        this.distance_km = distance_km;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
