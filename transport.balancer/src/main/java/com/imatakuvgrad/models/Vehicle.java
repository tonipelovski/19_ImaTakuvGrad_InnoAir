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

}
