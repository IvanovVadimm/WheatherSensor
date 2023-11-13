package com.example.WheatherSensor.exceptions.sensorsException;

/**
 * The class describes an exception if no sensor with this key was found
 **/
public class SensorIsNotExistWithThatKeyExc extends Exception {
    @Override
    public String toString() {
        return "That Sensor is not exist!";
    }
}