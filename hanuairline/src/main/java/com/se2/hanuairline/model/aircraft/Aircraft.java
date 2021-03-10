package com.se2.hanuairline.model.aircraft;

import com.se2.hanuairline.model.Flight;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "aircraft", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        }),
})
public class Aircraft implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "aircraftType_id")
    private AircraftType aircraftType;

    @NotBlank
    @NotNull
    private String status;

    @OneToMany(mappedBy = "aircraft")
    private Set<AircraftSeat> aircraftSeatSet;

    @OneToMany(mappedBy = "aircraft")
    private Set<Flight> flights;

    public Aircraft(Long id, @NotBlank @Size(max = 40) String name, @NotBlank @NotNull AircraftType aircrafttype, @NotBlank @NotNull AircraftStatus status) {
        this.id = id;
        this.name = name;
        this.aircraftType = aircrafttype;
        this.status = status.toString();
    }

    public Aircraft() {

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

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircrafttype) {
        this.aircraftType = aircrafttype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(AircraftStatus status) {
        this.status = status.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
