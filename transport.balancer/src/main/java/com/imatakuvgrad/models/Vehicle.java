package com.imatakuvgrad.models;


import com.imatakuvgrad.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Vehicle {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private VehicleType type;

    @NotNull
    private Integer line;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
}
