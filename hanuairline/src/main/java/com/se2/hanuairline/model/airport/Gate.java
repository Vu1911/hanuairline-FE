package com.se2.hanuairline.model.airport;

import com.se2.hanuairline.model.Flight;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "gate")
public class Gate implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @NotBlank
    @NotNull
    private String name;

    @OneToMany(mappedBy = "departure_gate")
    private Set<Flight> flight1;

    @OneToMany(mappedBy = "arrival_gate")
    private Set<Flight> flight2;

    public Gate(Long id, Airport airport, @NotBlank @NotNull String name) {
        this.id = id;
        this.airport = airport;
        this.name = name;
    }

    public Gate() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}