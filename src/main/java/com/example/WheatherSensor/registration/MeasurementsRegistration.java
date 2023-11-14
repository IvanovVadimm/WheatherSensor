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
    private double value;
    private boolean isRaining;

    public void setValue(double value) {
        this.value = value;
    }

    public void setIsRaining(boolean raining) {
        this.isRaining = raining;
    }

    public double getValue() {
        return value;
    }

    public boolean getIsRaining() {
        return isRaining;
    }
}