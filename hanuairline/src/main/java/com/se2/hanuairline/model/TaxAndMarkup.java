package com.se2.hanuairline.model;

import com.se2.hanuairline.model.aircraft.Aircraft;
import com.se2.hanuairline.model.airport.Airway;
import com.se2.hanuairline.model.airport.Gate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "tax_markup")
public class TaxAndMarkup implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private int fare_percentage;

    public TaxAndMarkup(Long id, @NotBlank @NotNull String name, @NotBlank @NotNull int fare_percentage) {
        this.id = id;
        this.name = name;
        this.fare_percentage = fare_percentage;
    }

    public TaxAndMarkup() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFare_percentage() {
        return fare_percentage;
    }

    public void setFare_percentage(int fare_percentage) {
        this.fare_percentage = fare_percentage;
    }
}