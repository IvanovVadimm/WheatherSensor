package com.example.WheatherSensor.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class is intended for recording weather data from the sensor
 **/
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class MeasurementsRegistration {
    private Double value;
    private Boolean isRaining;

    public Double getValue() {
        return value;
    }

    public Boolean getIsRaining() {
        return isRaining;
    }
}