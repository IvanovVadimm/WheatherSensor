package com.example.WheatherSensor.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The class is intended for registering a sensor on the server
 **/
@Data
@Component
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SensorRegistration {
    private String name;
}