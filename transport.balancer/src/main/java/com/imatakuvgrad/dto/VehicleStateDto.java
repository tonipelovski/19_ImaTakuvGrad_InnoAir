package com.imatakuvgrad.dto;

import com.imatakuvgrad.VehicleType;
import com.imatakuvgrad.models.Image;
import com.imatakuvgrad.models.Vehicle;

public class VehicleStateDto {
    public Integer peopleCount;
    public String src;
    public Integer line;
    public VehicleType type;

    public VehicleStateDto(Vehicle vehicle, Image image) {
        peopleCount = image.getPeopleCount();
        src = image.getData();
        line = vehicle.getLine();
        type = vehicle.getType();
    }
}
