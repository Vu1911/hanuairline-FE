package com.se2.hanuairline.payload.aircraft;

import com.se2.hanuairline.model.aircraft.AircraftType;
import com.se2.hanuairline.model.aircraft.TravelClass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SeatsByClassPayLoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long travelClass_id;

    private Long aircraftType_id;

    @NotBlank
    @NotNull
    private int quantity;

    @NotBlank
    @NotNull
    private int seats_per_row;

    @NotBlank
    @NotNull
    private int rows_quantity;

    public SeatsByClassPayLoad(Long id, Long travelClass_id, Long aircraftType_id, @NotBlank @NotNull int quantity, @NotBlank @NotNull int seats_per_row, @NotBlank @NotNull int rows_quantity) {
        this.id = id;
        this.travelClass_id = travelClass_id;
        this.aircraftType_id = aircraftType_id;
        this.quantity = quantity;
        this.seats_per_row = seats_per_row;
        this.rows_quantity = rows_quantity;
    }

    public SeatsByClassPayLoad(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelClass_id() {
        return travelClass_id;
    }

    public void setTravelClass_id(Long travelClass_id) {
        this.travelClass_id = travelClass_id;
    }

    public Long getAircraftType_id() {
        return aircraftType_id;
    }

    public void setAircraftType_id(Long aircraftType_id) {
        this.aircraftType_id = aircraftType_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
