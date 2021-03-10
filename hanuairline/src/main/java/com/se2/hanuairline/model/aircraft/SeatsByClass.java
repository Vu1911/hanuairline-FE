package com.se2.hanuairline.model.aircraft;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seats_by_class")
public class SeatsByClass implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="travelclass_id", nullable = false )
    private TravelClass travelClass;

    @ManyToOne
    @JoinColumn(name="aircrafttype_id", nullable = false)
    private AircraftType aircraftType;

    @NotBlank
    @NotNull
    private int quantity;

    @NotBlank
    @NotNull
    private int seats_per_row;

    @NotBlank
    @NotNull
    private int rows_quantity;


    public SeatsByClass(Long id, TravelClass travelClass, AircraftType aircraftType, @NotBlank @NotNull int quantity, @NotBlank @NotNull int seats_per_row, @NotBlank @NotNull int rows_quantity) {
        this.id = id;
        this.travelClass = travelClass;
        this.aircraftType = aircraftType;
        this.quantity = quantity;
        this.seats_per_row = seats_per_row;
        this.rows_quantity = rows_quantity;
    }

    public SeatsByClass() {

    }

    public int getSeats_per_row() {
        return seats_per_row;
    }

    public void setSeats_per_row(int seats_per_row) {
        this.seats_per_row = seats_per_row;
    }

    public int getRows_quantity() {
        return rows_quantity;
    }

    public void setRows_quantity(int rows_quantity) {
        this.rows_quantity = rows_quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}