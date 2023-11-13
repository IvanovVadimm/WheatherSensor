package com.example.WheatherSensor.utils;

import com.example.WheatherSensor.utilsInterfaces.ICheckMeasurementsValueOnValidData;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A class that helps determine whether the correct temperature information was transmitted when sending the request
 **/
@Component
public class CheckMeasurementValueOnValidData implements ICheckMeasurementsValueOnValidData {
    private final byte MIN_VALUE = -100;
    private final byte MAX_VALUE = 100;

    @Override
    public boolean rightValueOfMeasurement(Double value) {
        Optional<Double> valueOptional = Optional.ofNullable(value);
        if (valueOptional.isPresent()) {
            if (valueOptional.get() >= MIN_VALUE && valueOptional.get() <= MAX_VALUE) {
                return true;
            }
        }
        return false;
    }
}