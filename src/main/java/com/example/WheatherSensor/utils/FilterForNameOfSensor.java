package com.example.WheatherSensor.utils;

import com.example.WheatherSensor.utilsInterfaces.IFilterForNameOfSensor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A class that helps validate the sensor name
 **/
@Component
public class FilterForNameOfSensor implements IFilterForNameOfSensor {
    private final int MIN_SYMBOLS = 3;
    private final int MAX_SYMBOLS = 30;

    @Override
    public boolean rightNameOfSensor(String name) {
        Optional<String> nameOfSensorOptional = Optional.ofNullable(name);
        if (nameOfSensorOptional.isPresent()) {
            if (nameOfSensorOptional.get().length() >= MIN_SYMBOLS && nameOfSensorOptional.get().length() <= MAX_SYMBOLS) {
                return true;
            }
        }
        return false;
    }
}