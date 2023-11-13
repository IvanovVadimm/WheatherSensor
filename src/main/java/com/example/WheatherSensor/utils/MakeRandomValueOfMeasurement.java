package com.example.WheatherSensor.utils;

import com.example.WheatherSensor.utilsInterfaces.IMakeRandomValueOfMeasurement;
import org.springframework.stereotype.Component;

/**
 * Class generating random values for temperature values
 **/
@Component
public class MakeRandomValueOfMeasurement implements IMakeRandomValueOfMeasurement {
    private final byte MAX_VALUE = 100;
    private final byte MIN_VALUE = -100;

    public double randomValue() {
        return Math.floor(Math.random() * (MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
    }
}