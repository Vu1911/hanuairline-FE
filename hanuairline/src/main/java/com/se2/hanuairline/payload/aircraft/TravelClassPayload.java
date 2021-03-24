package com.se2.hanuairline.payload.aircraft;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TravelClassPayload {
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 1000)
    @Column(length = 1000)
    private String desciption;

    public TravelClassPayload(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 1000) String desciption) {
        this.name = name;
        this.desciption = desciption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
