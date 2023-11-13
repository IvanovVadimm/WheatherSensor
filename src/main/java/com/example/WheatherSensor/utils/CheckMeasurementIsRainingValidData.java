package com.example.WheatherSensor.utils;

import com.example.WheatherSensor.utilsInterfaces.ICheckMeasurementsIsRainingOnValidData;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A class that helps determine whether rain information was transmitted when sending a request
 **/
@Component
public class CheckMeasurementIsRainingValidData implements ICheckMeasurementsIsRainingOnValidData {
    @Override
    public boolean rightIsRainingOfMeasurement(Boolean isRaining) {
        Optional<Boolean> isRainingOptional = Optional.ofNullable(isRaining);
        return isRainingOptional.isPresent();
    }
}