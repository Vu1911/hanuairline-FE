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
    private String description;

    public TravelClassPayload(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 1000) String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
