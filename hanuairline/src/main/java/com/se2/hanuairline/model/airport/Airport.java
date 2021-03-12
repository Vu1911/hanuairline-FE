package com.se2.hanuairline.model.airport;

import com.se2.hanuairline.model.aircraft.AircraftSeat;
import com.se2.hanuairline.model.aircraft.AircraftStatus;
import com.se2.hanuairline.model.aircraft.AircraftType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "airport", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        }),
})
public class Airport implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 40)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 40)
    private String country;

    @NotBlank
    @Size(max = 40)
    private String city;

    @NotNull
    private int capacity;

    @NotBlank
    @NotNull
    private String status; ;

    @OneToMany(mappedBy = "airport")
    private Set<Gate> gates;

    @OneToMany(mappedBy = "departureAirport")
    private Set<Airway> airway1;

    @OneToMany(mappedBy = "arrivalAirport")
    private Set<Airway> airway2;

    public Airport(Long id, @NotBlank @NotNull @Size(max = 40) String name, @NotBlank @NotNull @Size(max = 40) String country, @NotBlank @Size(max = 40) String city, @NotBlank @NotNull int capacity, @NotBlank @NotNull String status) {
        AirportStatus st = AirportStatus.valueOf(status);
        System.out.println(status);
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
        this.status = st.toString();
    }

    public Airport() {

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(AirportStatus status) {
        this.status = status.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", capacity=" + capacity +
                ", status='" + status + '\'' +
                ", gates=" + gates +
                ", airway1=" + airway1 +
                ", airway2=" + airway2 +
                '}';
    }
}
