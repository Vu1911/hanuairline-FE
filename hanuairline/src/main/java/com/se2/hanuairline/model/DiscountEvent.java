package com.se2.hanuairline.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "discount_event")
public class DiscountEvent implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private int discount_rate_percentage;

    @OneToMany(mappedBy = "discount")
    private Set<Flight> flight;

    public DiscountEvent(Long id, @NotBlank @NotNull int discount_rate_percentage, Set<Flight> flight) {
        this.id = id;
        this.discount_rate_percentage = discount_rate_percentage;
        this.flight = flight;
    }

    public DiscountEvent() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiscount_rate_percentage() {
        return discount_rate_percentage;
    }

    public void setDiscount_rate_percentage(int discount_rate_percentage) {
        this.discount_rate_percentage = discount_rate_percentage;
    }

    public Set<Flight> getFlight() {
        return flight;
    }

    public void setFlight(Set<Flight> flight) {
        this.flight = flight;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
