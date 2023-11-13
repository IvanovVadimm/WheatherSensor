package com.example.WheatherSensor.utils;

import com.example.WheatherSensor.utilsInterfaces.IMakeRandomIsRainingOfMeasurement;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Class generating random values "true" or "false" for raining
 **/
@Component
public class MakeRandomIsRainingOfMeasurement implements IMakeRandomIsRainingOfMeasurement {
    public boolean getRandomIsRaining() {
        Random random = new Random();
        return random.nextBoolean();
    }
}