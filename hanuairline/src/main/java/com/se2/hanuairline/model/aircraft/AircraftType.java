package com.se2.hanuairline.model.aircraft;

import javax.persistence.*;
import javax.validation.constraints.Min;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "aircraft_type", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        }),
})
public class AircraftType implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "aircraftType")
    private Set<Aircraft> aircraft;

    @Size(max = 40)
    @NotNull(message = "provide aircraft type name")
    private String name;

    @NotNull(message = "provide aircraft type name")
    private int seat_capacity;

    @NotNull
    private int luggage_capacity_kg;

    @NotNull
    private int average_velocity;

    @OneToMany(mappedBy = "aircraftType")
    private Set<SeatsByClass> seatsByClassSet;

    public AircraftType(Long id, @NotBlank @Size(max = 40) String name, @NotBlank @NotNull int seat_capacity, @NotBlank @NotNull int lugage_capacity_kg, @NotBlank @NotNull int average_velocity) {
        this.id = id;
        this.name = name;
        this.seat_capacity = seat_capacity;
        this.luggage_capacity_kg = lugage_capacity_kg;
        this.average_velocity = average_velocity;
    }

    public AircraftType() {

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

    public int getSeat_capacity() {
        return seat_capacity;
    }

    public void setSeat_capacity(int seat_capacity) {
        this.seat_capacity = seat_capacity;
    }

    public int getLuggage_capacity_kg() {
        return luggage_capacity_kg;
    }

    public void setLuggage_capacity_kg(int luggage_capacity_kg) {
        this.luggage_capacity_kg = luggage_capacity_kg;
    }

    public int getAverage_velocity() {
        return average_velocity;
    }

    public void setAverage_velocity(int average_velocity) {
        this.average_velocity = average_velocity;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
