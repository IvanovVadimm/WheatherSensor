package com.example.WheatherSensor.utils;

import com.example.WheatherSensor.utilsInterfaces.IMakeUUID;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Class generating random uuid for sensor
 **/
@Component
public class MakerUUIDForSensor implements IMakeUUID {
    public UUID makeUUID() {
        return UUID.randomUUID();
    }
}